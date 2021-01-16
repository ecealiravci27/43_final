package Model.Controller;

import GUI.GUIController;
import Model.Board;
import Model.Cards.*;
import Model.Dice;
import Model.Fields.*;

public class Controller {
    private Dice dice;
    private CardPile cardPile;
    private int playerAmount;
    private PropertyPlayerController propertyPlayerController;
    private GUIController guiController;
    private boolean endGame;
    private int totalPlayers = 0;
    Board board;
    SuperField[] field;
    private int playerTurn;

    public Controller(){
        this.cardPile = new CardPile();
        this.board = new Board();
        this.field = board.getField();
        this.guiController = new GUIController(field);
        this.propertyPlayerController = setupPropertyPlayerCrontroller(board);
        this.dice = new Dice();
    }



    public void startGame() {
        guiController.GUIPlayers(propertyPlayerController.getPlayerArray());
        play();
    }

    private void play() {
        endGame = false;
        while (!endGame) {
            //number of rounds
            int turn = 0;
            for (int i = 0; i < 1000; i++) {
                for (int k = 0; k < totalPlayers; k++) {
                    doTurn(k, turn);
                    turn++;
                }
                for (int j = 0; j< totalPlayers ; j++) {
                    int counter = 0;
                    if(propertyPlayerController.isBankrupt(j)){
                        counter++;
                    }
                    if (counter == (totalPlayers - 1)) {
                        endGame = true;
                        break;
                    }
                }
            }
        }
    }

    private void doTurn(int playerID, int playerTurn) {
        if (!propertyPlayerController.isBankrupt(playerID)) {
            if (!propertyPlayerController.getPlayerArray()[playerID].isJailed()) {
                guiController.wantToRoll("test" + playerID);
                movePlayer(playerID);
                int pos1 = propertyPlayerController.getPlayerPosition(playerID);
                SuperField landedField = field[pos1];
                doField(landedField, playerID, playerTurn);

                // In some cases the player pos gets changed by doField
                int pos2 = propertyPlayerController.getPlayerPosition(playerID);
                guiController.changePlayerGUIPos(playerID, pos2, pos1);

                // update every players balance
                for (int i = 0; i < totalPlayers; i++) {
                    guiController.updateBalance(i, propertyPlayerController.getPlayerMoney(i));
                }
            }
            if (propertyPlayerController.getPlayerArray()[playerID].isJailed()){
                //message to jailed player
                //guiController.
                System.out.println("Du er fængslet. vent en tur!");
                propertyPlayerController.getPlayerArray()[playerID].setFree();
            }
        }
    }

    private void passStart(int oldpos, int newpos, int playerID){
        if(newpos <= 12 && oldpos > field.length - 12){
            propertyPlayerController.changeAccount(4000, playerID);
        }
    }

    private void movePlayer(int ID) {
        int dice_1 = dice.rollDice();
        int dice_2 = dice.rollDice();
        System.out.println(dice_1);
        System.out.println(dice_2);
        int eyesum = dice_1 + dice_2;
        dice.setDice(eyesum);
        guiController.showDice(dice_1, dice_2);
        propertyPlayerController.movePiece(eyesum, ID);
        int pos_1 = propertyPlayerController.getOldPlayerPosition(ID);
        int pos_2 = propertyPlayerController.getPlayerPosition(ID);
        guiController.changePlayerGUIPos(ID, pos_2, pos_1);
        passStart(pos_1, pos_2, ID);
    }

    private void doField(SuperField landedField, int playerID, int turn){
//        if(passStart(playerID) && turn > propertyPlayerController.getPlayerArray().length){
//            propertyPlayerController.changeAccount(4000, playerID);
//        }
        int fieldID = landedField.getID();
        int EyeSum = dice.getRememberDice();
        if (landedField instanceof OwnableField) {
            doPropertyField((OwnableField) landedField,playerID,fieldID);
        }
        if (landedField instanceof ChanceField) {
            doCard(playerID);
        }
        if (landedField instanceof SpecialField){
            doSpecialField((SpecialField) landedField,playerID,fieldID);
        }
    }

    private void doSpecialField(SpecialField landedField, int playerID, int fieldID){
        propertyPlayerController.doSpecialField(landedField, playerID, fieldID);
    }

    private  void doPropertyField(OwnableField landedField, int playerID, int fieldID){
        if (!propertyPlayerController.isOwned(landedField.getID())) {
            if(propertyPlayerController.isAffordable(playerID, landedField.getFieldPrice())){
                if (guiController.wantToBuy(landedField.getFieldName())) {
                    propertyPlayerController.purchaseProperty(playerID,landedField);
                    guiController.setPropertyBorder(playerID, fieldID);
                }
            }
        }
        else if (!(propertyPlayerController.getOwnership(landedField.getID()) == playerID)) {
            int owner = propertyPlayerController.getOwnership(fieldID);
            propertyPlayerController.payPlayerRent((OwnableField) landedField,owner, playerID, dice.getRememberDice());
        }
    }

    private void doCard(int playerID){
        SuperCard card = cardPile.drawCard();
        if(card instanceof MoveCard){
            if ((((MoveCard) card).getType()) == 1){
            propertyPlayerController.movePiece(((MoveCard) card).getMovePiece(), playerID);
        }
            if ((((MoveCard) card).getType()) == 2) {
                propertyPlayerController.setPiece(((MoveCard) card).getMovePiece(), playerID);
            }
        }
        if(card instanceof MoneyCard){
            propertyPlayerController.changeAccount(((MoneyCard) card).getChangeMoney(), playerID);
        }
        if(card instanceof FreeCard) {
            propertyPlayerController.setFree(playerID);
        }
        guiController.readCard(card.getCardDescription());
    }

    private PropertyPlayerController setupPropertyPlayerCrontroller (Board board){
        int maxPlayers = 6;
        int minPlayers = 2;
        totalPlayers = guiController.totalplayers(minPlayers, maxPlayers);
        return new PropertyPlayerController(totalPlayers, board);
    }
}