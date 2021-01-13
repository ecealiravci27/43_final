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

    public void movePiece(int eyeSum, int playerID) {

        getPlayer(playerID).movePiece(eyeSum);
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

    private Player getPlayer(int playerID) {

        return playerArray[playerID-1];
    }




    public void payRent(OwnableField propertyField, int owner, int playerID, int eyeSum) {

        int rent;

        rent = propertyField.getRent(eyeSum,propertyManager.numberOfOwned(owner,propertyField.getID()));

        playerArray[playerID].reduceBalance(rent);

        playerArray[owner].addBalance(rent);
    }





    public void purchaseProperty(int playerID, OwnableField propertyField) {

        playerArray[playerID].reduceBalance(propertyField.getFieldPrice());

        propertyManager.gainOwnership(playerID,propertyField.getID());
    }


    public boolean isAffordable(int playerID, int change) {

        boolean canAfford = true;

        if (getPlayer(playerID).getBalance() < change) {

            canAfford = false;
        }

        return canAfford;
    }
}


