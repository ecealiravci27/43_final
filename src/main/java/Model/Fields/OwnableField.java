package Model.Fields;

public abstract class OwnableField extends SuperField{

    protected int price;
    protected int rent;

    public OwnableField(String name, String description, int ID, int price, int rent) {
        super(name, description, ID);
        this.price = price;
        this.rent = rent;
    }
    public int getFieldPrice(){
        return price;
    }

    abstract public int getRent(int eyesum, int owned_entities);
    public int getFieldRent(){
        return rent;
    }
}

