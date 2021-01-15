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
    private final int minPlayers = 2;
    private final int maxPlayers = 6;
    SuperField[] board = new Board().getField();
    private int playerTurn;

    public Controller(){
        this.cardPile = new CardPile();
        this.propertyPlayerController = new PropertyPlayerController(playerAmount, board);
    }

   /* public void movePiece() {
        propertyPlayerController.movePiece(dice.fetchEyeSum(), );
    }*/


    public void startGame() {
        endGame = false;
        PropertyPlayerController propertyPlayerController = new PropertyPlayerController(2, board);
        GUIController guiController = new GUIController(board);

        guiController = new GUIController(board);
        totalPlayers = guiController.totalplayers(minPlayers, maxPlayers);
        propertyPlayerController = new PropertyPlayerController(totalPlayers, board);
        guiController.GUIPlayers(propertyPlayerController.getPlayerArray());
        System.out.println("totalplayers: " + totalPlayers);
        dice = new Dice();
        guiController.wantToBuy("test");
//        for (int i = 0; i < 5; i++) {
//
//            for (int j = 1; j < 3; j++) {
//                int roll1 = dice.rollDice();
//                int roll2 = dice.rollDice();
//
//                propertyPlayerController.movePiece(roll1 + roll2, j);
//                guiController.showDice(roll1, roll2);
//                System.out.println("Model.Player " + j + " eye sum " + dice.getRememberDice());
//                propertyPlayerController.getPlayerPosition(j);
//                System.out.println("Model.Player " + j + " position : " + propertyPlayerController.getPlayerPosition(j));
//            }
//        }
        play();
    }

    private void endGame() {
        endGame = true;
    }

    private void play() {

        while (!endGame) {
            for (int i = 0; i < totalPlayers; i++) {
                doTurn(i);
            }
        }
    }

    private void doTurn(int playerID) {
            SuperField landedField = board[propertyPlayerController.getPlayerPosition(playerID)];
            movePlayer(playerID);
            doField(landedField, playerID);
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
}


