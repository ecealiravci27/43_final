package Model.Property;

public class Property {
    public boolean available;
    public int propertyID;
    public int owner;

    public Property(int ID){
        this.available = isAvailable();
        this.owner = 0;
        this.propertyID = ID;
    }

    public void seizeProperty(int playerNumber){
        owner = playerNumber;
    }

    public void setOwner(int ID){
        owner = ID;
    }

    public int getOwner(){
        return owner;
    }

    public int getID() {
        return propertyID;
    }

    public boolean isAvailable(){
        return owner == 0;
    }

    public boolean getAvailability() {
        return available;
    }

}