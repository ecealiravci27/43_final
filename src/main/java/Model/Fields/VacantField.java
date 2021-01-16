package Model.Fields;

public class VacantField extends OwnableField {

    private final int[] house_rent;
    private final int house_price;
    private final int typeIndex;
    private final String Color;
    String[] Colors = new String[]{ "Blue","Orange", "light-yellow", "purple", "gray", "red", "white", "yellow"};

    public VacantField(String name, String description, int ID, int price, int typeIndex, int rent, int one_house_rent, int two_house_rent,int three_house_rent, int four_house_rent, int five_house_rent, int house_price) {
        super(name, description, ID, price, rent);
        this.typeIndex = typeIndex;
        this.Color = Colors[typeIndex];
        this.house_rent = new int[]{one_house_rent, two_house_rent, three_house_rent, four_house_rent, five_house_rent};
        this.house_price = house_price;
        }

    @Override
    public int getRent(int eyesum, int owned_houses) {
        if (owned_houses == 0) {
            return rent;
        }
        else {
            return house_rent[owned_houses-1];
        }
    }

    public int getHouse_price(){
        return house_price;
    }

    public String getColor(){ return Color;}

    public int getTypeIndex(){ return typeIndex;}
}
