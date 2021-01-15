package Model.Controller;

import GUI.GUIController;
import Model.Board;
import Model.Cards.CardPile;
import Model.Cards.MoneyCard;
import Model.Cards.MoveCard;
import Model.Cards.SuperCard;
import Model.Dice;
import Model.Fields.ChanceField;
import Model.Fields.OwnableField;
import Model.Fields.SuperField;
import Model.Fields.VacantField;

public class Controller {

    private Dice dice;
    private CardPile cardPile;
    private int playerAmount;
    private PropertyPlayerController propertyPlayerController;
    private GUIController guiController;
    private boolean endGame;
    private int totalPlayers = 0;
    SuperField[] board = new Board().getField();
    private int playerTurn;

    public Controller(){
        this.cardPile = new CardPile();
        this.propertyPlayerController = setupPropertyPlayerCrontroller();
        this.guiController = new GUIController(board);
        this.dice = new Dice();
    }

    public void startGame() {
        guiController.GUIPlayers(propertyPlayerController.getPlayerArray());
        play();
    }

    private void endGame() {
        endGame = true;
    }

    private void play() {
        while (!endGame) {
            //number of rounds
            int i;
            for (i = 0; i < 1000; i++) {
                for (i = 0; i < totalPlayers; i++) {
                    doTurn(i);
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

    private void doTurn(int playerID) {
        if (!propertyPlayerController.isBankrupt(playerID)) {
            movePlayer(playerID);
            SuperField landedField = board[propertyPlayerController.getPlayerPosition(playerID)];
            doField(landedField, playerID);
        }
    }

    private void movePlayer(int ID){ ;
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

    private void doField(SuperField landedField, int playerID){
        int fieldID = landedField.getID();
        int EyeSum = dice.getRememberDice();
        if (landedField instanceof OwnableField){
            if( guiController.wantToBuy("roll")){
                propertyPlayerController.doPropertyField((OwnableField) landedField, playerID, dice.getRememberDice());
            }
        }
        if (landedField instanceof ChanceField){
            doCard(playerID);
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

    private PropertyPlayerController setupPropertyPlayerCrontroller (){
        int maxPlayers = 6;
        int minPlayers = 2;
        totalPlayers = guiController.totalplayers(minPlayers, maxPlayers);
        PropertyPlayerController pController = new PropertyPlayerController(totalPlayers, board);
        return pController;
    }
}


