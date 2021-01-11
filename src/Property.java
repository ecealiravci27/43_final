public class Property {
    public boolean available;
    public int propertyID;
    public int owner;

    public Property(int cost, int ID){
        this.available = true;
        this.owner = 1000;
        this.propertyID = ID;
    }

    public void seizeProperty(int playerNumber){
        available = false;
        owner = playerNumber;
    }
    public int getOwner(){
        return owner;
    }
}