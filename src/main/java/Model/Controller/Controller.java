package Model.Controller;

import GUI.GUIController;
import Model.Board;
import Model.Dice;
import Model.Fields.SuperField;

public class Controller {

    private Dice dice;
    private PropertyPlayerController propertyPlayerController;
    private GUIController guiController;
    private boolean endGame;
    private int totalPlayers = 0;
    private final int minPlayers = 2;
    private final int maxPlayers = 6;
    SuperField[] board = new Board().getField();
    private int totalPlayers;
    private int playerTurn;

   /* public void movePiece() {

        propertyPlayerController.movePiece(dice.fetchEyeSum(), );
    }*/


    public void startGame() {

        endGame = false;

        PropertyPlayerController propertyPlayerController = new PropertyPlayerController(2, board);
        GUIController guiController = new GUIController(board);
        totalPlayers = guiController.totalplayers(minPlayers, maxPlayers);
        System.out.println("totalplayers: " + totalPlayers);
        Dice dice = new Dice();

        for (int i = 0; i < 5; i++) {

            for (int j = 1; j < 3; j++) {
                int roll1 = dice.rollDice();
                int roll2 = dice.rollDice();

                propertyPlayerController.movePiece(roll1 + roll2, j);
                guiController.showDice(roll1, roll2);
                System.out.println("Model.Player " + j + " eye sum " + dice.getRememberDice());
                propertyPlayerController.getPlayerPosition(j);
                System.out.println("Model.Player " + j + " position : " + propertyPlayerController.getPlayerPosition(j));
            }
        }
    }

    private void endGame() {

        endGame = true;
    }

    private void play() {

        while (!endGame) {

        }
    }

    private void turn() {

    }
}


