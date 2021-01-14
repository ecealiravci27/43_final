package Model.Controller;
import Model.Fields.*;
import Model.Board;
import Model.Player;
import Model.Property.HouseProperty;
import Model.Property.Property;
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


    public int getPlayerPosition(int playerID) {

        return getPlayer(playerID).getPlayerPosition();
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


    public boolean doPropertyField(OwnableField field, int playerID, int eyeSum) {

        Property propertyObject = propertyManager.getPropertyObject(field.getID());

        int rent;

        //When the owner of the property is the current player
        if (playerID == propertyObject.getOwner()) {

        }
        //When the owner of the property is the bank
        else if (0 == propertyObject.getOwner()) {

        }
        //When the owner of the property is another player
        else if (propertyObject.getOwner() != playerID && propertyObject.getOwner() != 0 )

        {
            //Scenario in which field is a VacantField
            if (field instanceof VacantField) {

                int houses = ((HouseProperty) propertyObject).getNumberOfHouses();

                rent = field.getRent(eyeSum,houses);
            }

            //Scenario in which field a CorperationField or a ShippingField
            else if (field instanceof CoorporationField || field instanceof ShippingField) {


            }
        }
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


