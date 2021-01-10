public class Controller {

    private Dice dice;
    private PropertyPlayerController propertyPlayerController;

    public void movePiece() {

        propertyPlayerController.movePiece(dice.fetchEyeSum());
    }



    public static void main(String[] args) {

        PropertyPlayerController propertyPlayerController = new PropertyPlayerController(2);

        Dice dice = new Dice();

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 4; j++) {

                propertyPlayerController.movePiece(dice.fetchEyeSum());

                System.out.println(dice.getGetEyesum());

                propertyPlayerController.getPlayerPosition();

                System.out.println(propertyPlayerController.getPlayerPosition());

            }

        }
    }


    }


