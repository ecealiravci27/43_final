package Model.Controller;

import GUI.GUIController;
import Model.Board;
import Model.Cards.CardPile;
import Model.Cards.MoneyCard;
import Model.Cards.MoveCard;
import Model.Cards.SuperCard;
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
        this.propertyPlayerController = setupPropertyPlayerCrontroller(board);
        this.guiController = new GUIController(field);
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
            int i;
            int turn = 0;
            for (i = 0; i < 1000; i++) {
                for (i = 0; i < totalPlayers; i++) {
                    doTurn(i, turn);
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
            movePlayer(playerID);
            SuperField landedField = field[propertyPlayerController.getPlayerPosition(playerID)];
            doField(landedField, playerID, playerTurn);
        }
    }

    private void movePlayer(int ID) {
        int dice_1 = dice.rollDice();
        int dice_2 = dice.rollDice();
        System.out.println(dice_1);
        System.out.println(dice_2);
        int eyesum = dice_1 + dice_2;
        dice.setDice(eyesum);
        int pos_1 = propertyPlayerController.getPlayerPosition(ID);
        propertyPlayerController.movePiece(eyesum, ID);
        int pos_2 = propertyPlayerController.getPlayerPosition(ID);
        guiController.changePlayerGUIPos(ID, pos_2, pos_1);
    }

    private void doField(SuperField landedField, int playerID, int turn){
        if(passStart(playerID) && turn > propertyPlayerController.getPlayerArray().length){
            propertyPlayerController.changeAccount(4000, playerID);
        }
        int fieldID = landedField.getID();
        int EyeSum = dice.getRememberDice();
        if (landedField instanceof OwnableField) {
            doPropertyField((OwnableField) landedField,playerID,fieldID);
        }
        if (landedField instanceof ChanceField) {
            doCard(playerID);
        }
        if (landedField instanceof SpecialField){

        }
    }

    private void doSpecialField(SpecialField landedField, int playerID, int fieldID){
        propertyPlayerController.doSpecialField(landedField, playerID, fieldID);
    }

    private boolean passStart(int playerID){
        boolean passedStart = false;
        if(propertyPlayerController.getPlayerPosition(playerID) <= 12){
            passedStart = true;
        }
        return true;
    }

    private  void doPropertyField(OwnableField landedField, int playerID, int fieldID){
        if (!propertyPlayerController.isOwned(landedField.getID())) {
            if(propertyPlayerController.isAffordable(playerID, landedField.getFieldPrice())){
                if (guiController.wantToBuy(landedField.getFieldName())) {
                    propertyPlayerController.purchaseProperty(playerID,landedField);
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
    }

    private PropertyPlayerController setupPropertyPlayerCrontroller (Board board){
        int maxPlayers = 6;
        int minPlayers = 2;
        totalPlayers = guiController.totalplayers(minPlayers, maxPlayers);
        return new PropertyPlayerController(totalPlayers, board);
    }
}


