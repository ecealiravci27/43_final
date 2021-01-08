public abstract class PropertyField extends SuperField{

    protected int price;
    protected String Color;
    protected int rent;
    String[] Colors = new String[]{ "Blue","Orange", "light-yellow", "purple", "gray", "red", "white", "yellow"};

    public PropertyField(String name, String description, int ID, int price, int typeIndex, int rent) {
        super(name, description, ID);
        this.price = price;
        this.Color = Colors[typeIndex];
        this.rent = rent;
    }
    public int getFieldPrice(){
        return price;
    }
}

