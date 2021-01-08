package Fields;

public abstract class PropertyField extends SuperField{

    protected int price;
    protected int rent;

    public PropertyField(String name, String description, int ID, int price, int rent) {
        super(name, description, ID);
        this.price = price;
        this.rent = rent;
    }
    public int getFieldPrice(){
        return price;
    }
    public int getFieldRent(){
        return rent;
    }
}

