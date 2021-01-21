package Model.Controller;
import Model.Fields.*;
import Model.Board;
import Model.Player;
import Model.Property.HouseOwnable;
import Model.Property.Ownable;
import Model.Controller.PropertyManager;

public class PropertyPlayerController {

    private SuperField[] field;
    private Player[] playerArray;
    private final PropertyManager propertyManager;

    public PropertyPlayerController(int playerAmount, Board board) {
        this.playerArray = setupPlayer(playerAmount);
        this.field = setupSuperFieldArray(board);
        this.propertyManager = new PropertyManager(field);
    }

    public void movePiece(int eyeSum, int playerID) {
        getPlayer(playerID).movePiece(eyeSum);
    }

    public void changeAccount(int money, int ID){
        playerArray[ID].addBalance(money);
    }

    public void testReducebalance(int money, int ID) {
        playerArray[ID].reduceBalance(money);
    }

    public void setPiece(int position, int ID){
        playerArray[ID].setPiece(position);
    }

    public int getPlayerPosition(int playerID) {
        return getPlayer(playerID).getPlayerPosition();
    }

    public int getOldPlayerPosition(int playerID) {
        return getPlayer(playerID).getOldPlayerPosition();
    }

    public void doSpecialField(SpecialField landedField, int playerID){
        String type = landedField.getType();

        //parking and visit fields do nothing

        //tax
        if(type.equals("tax")){
            int tax = landedField.getTaxes();
            if(isAffordable(playerID, tax)){
                playerArray[playerID].reduceBalance(tax);
            }
            else{
                playerArray[playerID].reduceBalance(tax);
                playerArray[playerID].bankrupt();
            }
        }

        //goJail
        if(type.equals("prison")){
            playerArray[playerID].setPiece(10);
            playerArray[playerID].jail();
        }
    }


    public Player[] setupPlayer(int playerAmount) {

        playerArray = new Player[playerAmount];

        for (int i = 0; i < playerAmount; i++) {
            playerArray[i] = new Player(i);
        }
        return playerArray;
    }

    public SuperField[] setupSuperFieldArray(Board board) {
        return board.getField();
    }

    public boolean isOwned(int fieldID){
        int bank = 10;
        int owner = getOwnership(fieldID);
        return owner != bank;
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

    public int getPlayerMoney (int ID){
        return playerArray[ID].getBalance();
    }

    public Player[] getPlayerArray() {
        return playerArray;
    }

    public void removeOwnerShip(int playerID){
        propertyManager.removeOwnership(playerID);
    }

    public VacantField[] getCanBuildArray(int playerID) {
        int[] ownableID = propertyManager.getOwnedHouseOwnables(playerID);
        VacantField[] houses;
        int index = 0;

        for (int j : ownableID) {
            for (SuperField superField : field) {
                if (j == superField.getID()) {
                    if (isAffordable(playerID, ((VacantField) superField).getHouse_price())) {
                        if (propertyManager.isGroupOwned(playerID, ((VacantField) superField).getTypeIndex())) {
                            if (propertyManager.getHouses(superField.getID()) < 4) {
                                index++;
                            }
                        }
                    }
                }
            }
        }

        int counter = 0;

        houses = new VacantField[index];

        for (int j : ownableID) {
            for (SuperField superField : field) {
                if (j == superField.getID()) {
                    if (isAffordable(playerID, ((VacantField) superField).getHouse_price())) {
                        if (propertyManager.isGroupOwned(playerID, ((VacantField) superField).getTypeIndex())) {
                            if (propertyManager.getHouses(superField.getID()) < 4) {
                                houses[counter] = ((VacantField) superField);
                                counter++;
                            }
                        }
                    }
                }
            }
        }

        return houses;
    }

    public int getRent(OwnableField field, int playerID, int eyeSum){
        int rent = 0;
        Ownable propertyObject = propertyManager.getOwnable(field.getID());
        //Rent for a Vacant field
        if (field instanceof VacantField) {
            int numberOfHouses = ((HouseOwnable) propertyObject).getNumberOfHouses();
            rent = field.getRent(eyeSum,numberOfHouses);
        }
        //Rent for a Shipping or Corperation field
        else if ((field instanceof CoorporationField) || (field instanceof ShippingField)){
            rent = field.getRent(eyeSum,propertyManager.numberOfOwned(playerID,field.getID()));
        }
        return rent;
    }
    public int payPlayerRent(OwnableField field, int owner, int playerID, int eyeSum) {
        int rent;
        rent = getRent(field, owner, eyeSum);
        System.out.println("rent = " + rent);
        Ownable propertyObject = propertyManager.getOwnable(field.getID());
        if (playerArray[playerID].getBalance() <= rent) {
            playerArray[owner].addBalance(playerArray[playerID].getBalance());
            playerArray[playerID].reduceBalance(rent);
            playerArray[playerID].bankrupt();
        } else {
            playerArray[owner].addBalance(rent);
            playerArray[playerID].reduceBalance(rent);
        }
        return rent;
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

    public int[] getOwned(int playerID){
        int[] owned = propertyManager.getOwnedOwnables(playerID);
        return owned;
    }

    public void buyHouse(int playerID, VacantField field) {

        playerArray[playerID].reduceBalance(field.getHouse_price());

        propertyManager.addHouse(field.getID());
    }

    public int getHouses(int fieldID) {
        return propertyManager.getHouses(fieldID);
    }
}