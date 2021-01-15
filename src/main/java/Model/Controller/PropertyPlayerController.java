package Model.Controller;
import Model.Fields.*;
import Model.Board;
import Model.Player;
import Model.Property.HouseOwnable;
import Model.Property.Ownable;
import Model.Property.PropertyManager;

public class PropertyPlayerController {

    private final Board board;
    private final PropertyManager propertyManager;
    private Player player;
    private int playerAmount;
    private Player[] playerArray;
    private int playerID;

    public PropertyPlayerController(int playerAmount, SuperField[] board) {

        this.playerArray = setupPlayer(playerAmount);
        this.board = setupBoard();
        this.propertyManager = new PropertyManager(board);
    }

    public void movePiece(int eyeSum, int playerID) {
        getPlayer(playerID).movePiece(eyeSum);
    }

    public void changeAccount(int money, int ID){
        playerArray[ID].addBalance(money);
    }

    public int getOwnedEntities(int playerID, int fieldID){
        return propertyManager.numberOfOwned(playerID, fieldID);
    }

    public void setPiece(int position, int ID){
        playerArray[ID].setPiece(position);
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
        int bank = 10;
        boolean canBuy = false;
        Ownable propertyObject = propertyManager.getPropertyObject(field.getID());

        //When the owner of the property is the current player
        if (playerID != propertyObject.getOwner()) {
            if (bank == propertyObject.getOwner()) {

                canBuy = isAffordable(playerID,field.getFieldPrice());
            }
            //When the owner of the property is another player
            else if (propertyObject.getOwner() != playerID && propertyObject.getOwner() != 10 ) {

                int change = 0;

                if (field instanceof VacantField) {
                    change = field.getRent(eyeSum,propertyManager.numberOfOwned(propertyManager.getPropertyObject(field.getID()).getOwner(),field.getID()));

                    } else if (field instanceof ShippingField || field instanceof CoorporationField) {
                    change = field.getRent(eyeSum,propertyManager.numberOfOwned(propertyManager.getPropertyObject(field.getID()).getOwner(),field.getID()));
                }

                //Player can afford to pay rent
                if (change <= playerArray[playerID].getBalance()) {
                    if (field instanceof ShippingField || field instanceof CoorporationField) {
                        payRent(field,propertyObject.getOwner(),playerID,eyeSum);
                    }
                } else {

                    //Player can't afford rent, something happens
                }
            }
        }
        return canBuy;
    }

    public int getOwnership(int ID){
        return propertyManager.getOwnable(ID).getOwner();
    }

    private Player getPlayer(int playerID) {

        return playerArray[playerID];
    }

    public Player[] getPlayerArray() {

        return playerArray;
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

    public void payRent(OwnableField field, int owner, int playerID, int eyeSum) {
        int rent;
        Ownable propertyObject = propertyManager.getPropertyObject(field.getID());

        //Rent for a Vacant field
        if (field instanceof VacantField) {

            int numberOfHouses = ((HouseOwnable) propertyObject).getNumberOfHouses();

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

        propertyManager.setOwnerShip(playerID,propertyField.getID());
    }

    //Method for checking if a player can afford something
    public boolean isAffordable(int playerID, int change) {
        boolean canAfford = true;
        if (getPlayer(playerID).getBalance() < change) {
            canAfford = false;
        }
        return canAfford;
    }

    public void sellHouse(int playerID, VacantField field) {

        ((HouseOwnable) propertyManager.getPropertyObject(field.getID())).removeHouse();

        playerArray[playerID].addBalance(field.getHouse_price()/2);
    }

    public static void main (String[] args) {


    }
}


