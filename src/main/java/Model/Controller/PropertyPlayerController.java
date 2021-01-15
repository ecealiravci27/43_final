package Model.Controller;
import Model.Fields.*;
import Model.Board;
import Model.Player;
import Model.Property.HouseOwnable;
import Model.Property.Ownable;
import Model.Property.PropertyManager;

public class PropertyPlayerController {

    private SuperField[] field;
    private final PropertyManager propertyManager;
    private Player player;
    private int playerAmount;
    private Player[] playerArray;
    private int playerID;

    public PropertyPlayerController(int playerAmount, Board board) {

        this.playerArray = setupPlayer(playerAmount);
        this.field = setupBoard(board);
        this.propertyManager =setupPropertyManager(field);
    }

    public void movePiece(int eyeSum, int playerID) {
        getPlayer(playerID).movePiece(eyeSum);
    }
    public PropertyManager setupPropertyManager(SuperField[] board){
        return new PropertyManager(board);
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


    public SuperField[] setupBoard(Board board) {
        return board.getField();
    }


    public void doPropertyField(OwnableField field, int playerID, int eyeSum) {
        //denne metode antager at man vil købe feltet
        int bank = 10;
        boolean canBuy = isAffordable(playerID,field.getFieldPrice());
        Ownable propertyObject = propertyManager.getPropertyObject(field.getID());
        int owner = propertyObject.getOwner();
        int fieldID = field.getID();
        int rent = getRent(field, playerID, eyeSum);
        //When the owner of the property is not the current player
        if (playerID != owner) {
            //when the bank is the owner
            if (bank == owner) {
                propertyManager.setOwnerShip(playerID,fieldID);
                getPlayer(playerID).addBalance(rent);
            }
            //When the owner of the property is another player
            else {
                //Player can afford to pay rent
                if (rent <= playerArray[playerID].getBalance()) {
                    if (field instanceof ShippingField || field instanceof CoorporationField) {
                        payPlayerRent(field, propertyObject.getOwner(), playerID, eyeSum);
                    } else {
                        //Player can't afford rent, they go bankrupt
                        bankruptPlayer(playerID);
                    }
                }
            }
        }
    }

    public int getOwnership(int ID){
        return propertyManager.getOwnable(ID).getOwner();
    }

    public boolean isBankrupt(int playerID){
        return getPlayer(playerID).isBankrupt();
    }

    private Player getPlayer(int playerID) {
        return playerArray[playerID];
    }
    public void bankruptPlayer(int playerID){
        getPlayer(playerID).bancrupt();
    }

    public int getPlayerMoney (int ID){
        return playerArray[ID].getBalance();
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

    public int getRent(OwnableField field, int playerID, int eyeSum){
        int rent = 0;
        Ownable propertyObject = propertyManager.getPropertyObject(field.getID());
        //Rent for a Vacant field
        if (field instanceof VacantField) {
            int numberOfHouses = ((HouseOwnable) propertyObject).getNumberOfHouses();
            rent = field.getRent(eyeSum,numberOfHouses);
        }
        //Rent for a Shipping or Corperation field
        else if ((field instanceof CoorporationField)){
            rent = field.getRent(eyeSum,propertyManager.numberOfOwned(playerID,field.getID()));
        }
        return rent;
    }

    public void payPlayerRent(OwnableField field, int owner, int playerID, int eyeSum) {
        int rent;
        rent = getRent(field, playerID, eyeSum);
        Ownable propertyObject = propertyManager.getPropertyObject(field.getID());
        playerArray[playerID].addBalance(rent);
        playerArray[playerID].addBalance(-(rent));
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
        Board board = new Board();
        PropertyPlayerController controller = new PropertyPlayerController(3,board);
        controller.doPropertyField((OwnableField) board.getFielobject(13), 2, 6);
    }
}


