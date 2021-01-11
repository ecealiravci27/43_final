public class Controller {

    private Dice dice;
    private PropertyPlayerController propertyPlayerController;
    private GUIController guiController;

   /* public void movePiece() {

        propertyPlayerController.movePiece(dice.fetchEyeSum(), );
    }*/


    public static void main(String[] args) {

        PropertyPlayerController propertyPlayerController = new PropertyPlayerController(2);
        GUIController guiController = new GUIController();

        Dice dice = new Dice();

        for (int i = 0; i < 5; i++) {

            for (int j = 1; j < 3; j++) {
                int roll1 = dice.rollDice();
                int roll2 = dice.rollDice();

                propertyPlayerController.movePiece(roll1 + roll2, j);
                guiController.showDice(roll1, roll2);
                System.out.println("Player " + j + " eye sum " + dice.getDiceOutcome());
                propertyPlayerController.getPlayerPosition(j);
                System.out.println("Player " + j + " position : " + propertyPlayerController.getPlayerPosition(j));
            }
        }
    }
}