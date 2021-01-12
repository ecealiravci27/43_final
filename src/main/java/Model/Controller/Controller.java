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
    SuperField[] board = new Board().getField();

   /* public void movePiece() {

        propertyPlayerController.movePiece(dice.fetchEyeSum(), );
    }*/


    public void startGame() {

        endGame = false;

        PropertyPlayerController propertyPlayerController = new PropertyPlayerController(2, board);
        GUIController guiController = new GUIController();

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

    public void endGame() {

        endGame = true;
    }

    public void play() {

        while (!endGame) {

        }
    }

    public void turn() {

    }


}


