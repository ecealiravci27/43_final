public class Controller {

    private Dice dice;
    private PropertyPlayerController propertyPlayerController;

    public void movePiece() {

        propertyPlayerController.movePiece(dice.grabEyeSum());
    }
