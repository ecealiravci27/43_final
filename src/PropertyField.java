public abstract class PropertyField extends SuperField{

    public int price;
    public String Color;
    public int Rent;
    String[] Colors = new String[]{ "Blue","Orange", "light-yellow", "purple", "gray", "red", "white", "yellow"};

    public PropertyField(String name, String description, int price,int typeIndex, int Rent) {
        super(name, description);
        this.price = price;
        this.Color = Colors[typeIndex];
        this.Rent = Rent;
    }

}
