package Model.Controller;
import Model.Fields.*;
import Model.Board;
import Model.Player;
import Model.Property.HouseOwnable;
import Model.Property.Ownable;
import Model.Property.PropertyManager;

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

    public int getOwnedEntities(int playerID, int fieldID){
        return propertyManager.numberOfOwned(playerID, fieldID);
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

    public void doSpecialField(SpecialField landedField, int playerID, int fieldID){
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
    public void bankruptPlayer(int playerID){
        getPlayer(playerID).bankrupt();
    }

    public int getPlayerMoney (int ID){
        return playerArray[ID].getBalance();
    }

    public Player[] getPlayerArray() {
        return playerArray;
    }

//    public boolean canPurchaseHouse(int playerID, VacantField field) {
//        boolean canBuild =  true;
//
//        //Checks if player is the owner of the property
//        if (playerID == propertyManager.getPropertyObject(field.getID()).getOwner()) {
//            canBuild = false;
//        }
//
//        //Checks if the player owns all of the fields of that indexType
//        if (!propertyManager.isGroupOwned(playerID, field.getTypeIndex())) {
//
//        }
//            canBuild = false;
//        }
//
//        if (!isAffordable(playerID,field.getHouse_price())) {
//            canBuild = false;
//        }
//        return canBuild;
//    }

    public VacantField[] getCanBuildArray(int playerID) {

        int counter = 0;

        int[] ownablesID = new int[propertyManager.getOwnedHouseOwnables(playerID).length];

        VacantField[] canBuyHousesOn;

        if (!(ownablesID.length == 0)) {

            for (int i = 0; i < ownablesID.length; i++) {

                for (int j = 0; j < field.length; j++) {

                    if (ownablesID[i] == field[j].getID()) {
                        System.out.println("Printing IDs for comparison : " + ownablesID[i] + " " + field[j].getID());

                        if (isAffordable(playerID,((VacantField) field[j]).getHouse_price())) {
                            if (propertyManager.isGroupOwned(playerID,((VacantField) field[j]).getTypeIndex())) {
                                counter++;
                            }
                        }
                    }
                }
            }

            canBuyHousesOn = new VacantField[counter];

            for (int i = 0; i < ownablesID.length; i++) {

                for (int j = 0; j < field.length; j++) {

                    if (ownablesID[i] == field[j].getID()) {

                        if (isAffordable(playerID,((VacantField) field[j]).getHouse_price())) {

                            if (propertyManager.isGroupOwned(playerID,((VacantField) field[j]).getTypeIndex())) {

                                canBuyHousesOn[i] = ((VacantField) field[j]);
                            }
                        }
                    }
                }
            }
        } else {

            canBuyHousesOn = new VacantField[0];
        }

        return canBuyHousesOn;
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
        else if ((field instanceof CoorporationField)){
            rent = field.getRent(eyeSum,propertyManager.numberOfOwned(playerID,field.getID()));
        }
        return rent;
    }
    public void payPlayerRent(OwnableField field, int owner, int playerID, int eyeSum) {
        int rent;
        rent = getRent(field, playerID, eyeSum);
        Ownable propertyObject = propertyManager.getOwnable(field.getID());
        if (playerArray[playerID].getBalance() < rent) {
            playerArray[owner].addBalance(playerArray[playerID].getBalance());
            playerArray[playerID].reduceBalance(rent);
            playerArray[playerID].bankrupt();
        } else {
            playerArray[owner].addBalance(rent);
            playerArray[playerID].reduceBalance(rent);
        }
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
        ((HouseOwnable) propertyManager.getOwnable(field.getID())).removeHouse();
        playerArray[playerID].addBalance(field.getHouse_price()/2);
    }

    public void setFree(int playerID) {
        playerArray[playerID].setFree();
    }

    public void buyHouse(int playerID, VacantField field) {

        playerArray[playerID].reduceBalance(field.getHouse_price());

        propertyManager.setOwnerShip(playerID, field.getID());
    }
}