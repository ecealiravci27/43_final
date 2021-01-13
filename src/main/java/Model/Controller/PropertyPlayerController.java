package Model.Controller;
import Model.Fields.OwnableField;
import Model.Fields.SuperField;
import Model.Board;
import Model.Player;
import Model.Property.PropertyManager;

public class PropertyPlayerController {

    private final Board board;
    private final PropertyManager propertyManager;
    private Player player;
    private int playerAmount;
    private Player[] playerArray;

    public PropertyPlayerController(int playerAmount, SuperField[] board) {

        this.playerArray = setupPlayer(playerAmount);
        this.board = setupBoard();
        this.propertyManager = new PropertyManager(board);

    }

    public void movePiece(int eyeSum, int ID) {

        playerArray[ID-1].movePiece(eyeSum);
    }

    public int getPlayerPosition(int ID) {
        return playerArray[ID-1].getPlayerPosition();
    }

    public Player[] setupPlayer(int playerAmount) {

        playerArray = new Player[playerAmount];

        for (int i = 0; i < playerAmount; i++) {

            playerArray[i] = new Player(i+1);

        }
        return playerArray;
    }

    public Board setupBoard() {
        return new Board();
    }

    public void doPropertyField() {


    }




    public void payRent(OwnableField propertyField, int owner, int playerID, int eyeSum) {

        //propertyField.getRent(eyeSum, )
    }





    public void purchaseProperty(int playerID, OwnableField propertyField) {

        playerArray[playerID].reduceBalance(propertyField.getFieldPrice());

        propertyManager.gainOwnership(playerID,propertyField.getID());
    }

}


