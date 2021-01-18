package Model.Fields;

//all fields that are ownable, including non-buildable and buildable fields
//a base for field subclasses
public abstract class OwnableField extends SuperField{

    protected int price;
    protected int rent;

    public OwnableField(String name, String description, int ID, int price, int rent) {
        super(name, description, ID);
        this.price = price;
        this.rent = rent;
    }

    //returns the price that will be charged when purchasing a field
    public int getFieldPrice(){
        return price;
    }

    //abstract method that needs information about the eyesum and number of owned entities
    abstract public int getRent(int eyesum, int owned_entities);

    //returns the rent of the field
    public int getFieldRent(){
        return rent;
    }
}

