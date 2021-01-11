public class Controller {

    private Dice dice;
    private PropertyPlayerController propertyPlayerController;

   /* public void movePiece() {

        propertyPlayerController.movePiece(dice.fetchEyeSum(), );
    }*/



    public static void main(String[] args) {

        PropertyPlayerController propertyPlayerController = new PropertyPlayerController(2);

        Dice dice = new Dice();

        for (int i = 0; i < 5; i++) {

            for (int j = 1; j < 3; j++) {

                propertyPlayerController.movePiece(dice.calculateEyeSum(), j);
                System.out.println("Player " + j + " eye sum " + dice.getDiceOutcome());
                propertyPlayerController.getPlayerPosition(j);
                System.out.println("Player " + j + " position : " + propertyPlayerController.getPlayerPosition(j));
            }
        }
    }


    }


