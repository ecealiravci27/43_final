package Model.Fields;

//all fields that can be built on
public class VacantField extends OwnableField {

    private final int[] house_rent;
    private final int house_price;
    private final int typeIndex;
    private final String Color;

    //an array containing the colors of all vacant fields
    String[] Colors = new String[]{ "Blue","Orange", "light-yellow", "purple", "gray", "red", "white", "yellow"};

    public VacantField(String name, String description, int ID, int price, int typeIndex, int rent, int one_house_rent, int two_house_rent,int three_house_rent, int four_house_rent, int house_price) {
        super(name, description, ID, price, rent);
        this.typeIndex = typeIndex;
        this.Color = Colors[typeIndex];
        this.house_rent = new int[]{one_house_rent, two_house_rent, three_house_rent, four_house_rent};
        this.house_price = house_price;
        }

    //returns the rent of the field depending on the amount of houses built on it
    @Override
    public int getRent(int eyesum, int owned_houses) {
        if (owned_houses == 0) {
            return rent;
        }
        else {
            return house_rent[owned_houses-1];
        }
    }

    //returns the price of each house that can be built on the fields
    public int getHouse_price(){
        return house_price;
    }

    //returns the color of each field
    public String getColor(){ return Color;}

    //returns the streets defined as typeIndex. If an entire row of colors is owned by the same player (one street) it will have a type index depending on which street is owned.
    public int getTypeIndex(){ return typeIndex;}
}
