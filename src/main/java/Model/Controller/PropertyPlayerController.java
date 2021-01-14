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

            playerArray[i] = new Player(i);

        }
        return playerArray;
    }


    public Board setupBoard() {
        return new Board();
    }


    public boolean doPropertyField(OwnableField field, int playerID, int eyeSum) {

        boolean canBuy = false;

        Property propertyObject = propertyManager.getPropertyObject(field.getID());

        //When the owner of the property is the current player
        if (playerID == propertyObject.getOwner()) {

        }
        //When the owner of the property is the bank
        else if (0 == propertyObject.getOwner()) {

            canBuy = isAffordable(playerID,field.getFieldPrice());
        }
        //When the owner of the property is another player
        else if (propertyObject.getOwner() != playerID && propertyObject.getOwner() != 0 )

        {
            payRent(field,propertyObject.getOwner(),playerID,eyeSum);
        }
        return canBuy;
    }


    private Player getPlayer(int playerID) {

        return playerArray[playerID];
    }

    public Player[] getPlayerArray() {

        return playerArray;
    }


    public void payRent(OwnableField field, int owner, int playerID, int eyeSum) {

        int rent;

        Property propertyObject = propertyManager.getPropertyObject(field.getID());

        //Rent for a Vacant field
        if (field instanceof VacantField) {

            int numberOfHouses = ((HouseProperty) propertyObject).getNumberOfHouses();

            rent = field.getRent(eyeSum,numberOfHouses);

            //Rent for a Shipping or Corperation field
        } else {

            rent = field.getRent(eyeSum,propertyManager.numberOfOwned(owner,field.getID()));
        }

        playerArray[playerID].reduceBalance(rent);

        playerArray[owner].addBalance(rent);
    }


    public void purchaseProperty(int playerID, OwnableField propertyField) {

        playerArray[playerID].reduceBalance(propertyField.getFieldPrice());

        propertyManager.gainOwnership(playerID,propertyField.getID());
    }

    public boolean canPurchaseHouse(int playerID, VacantField field) {

        boolean canBuild =  true;

        //Checks if player is the owner of the property
        if (playerID == propertyManager.getPropertyObject(field.getID()).getOwner()) {

            canBuild = false;
        }

        //Checks if the player owns all of the fields of that indexType
        if (!propertyManager.isGroupOwned(playerID, field.getTypeIndex())) {

            canBuild = false;
        }

        if (!isAffordable(playerID,field.getHouse_price())) {

            canBuild = false;
        }
        return canBuild;
    }


    private void purchaseHouse(int fieldID, int playerID) {

        ((HouseProperty) propertyManager.getPropertyObject(fieldID)).addHouse();

    }


    //Method for checking if a player can afford something
    private boolean isAffordable(int playerID, int change) {

        boolean canAfford = true;

        if (getPlayer(playerID).getBalance() < change) {

            canAfford = false;
        }

        return canAfford;
    }
}


