public class Property {
    public boolean availability;
    public int price;
    public int propertyID;
    public int owner;

    public Property(int cost, int ID){
        this.availability = true;
        this.owner = 1000;
        this.price = cost;
        this.propertyID = ID;
    }

    public void seizeProperty(int playerNumber){
        availability = false;
        owner = playerNumber;
    }
    public int getOwner(){
        return owner;
    }
}