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
        if (playerID == propertyObject.getOwner()) {
        }
        //When the owner of the property is the bank
        else if (bank == propertyObject.getOwner()) {

            canBuy = isAffordable(playerID,field.getFieldPrice());
        }
        //When the owner of the property is another player
        else if (propertyObject.getOwner() != playerID && propertyObject.getOwner() != 0 )

        {
            payRent(field,propertyObject.getOwner(),playerID,eyeSum);
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
}


