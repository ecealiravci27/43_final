public class VacantField extends PropertyField {

    private final int[] house_rent;
    private final int hotel_rent;
    private final int house_price;
    private final int hotel_price;

    public VacantField(String name, String description, int ID, int price, int typeIndex, int rent, int one_house_rent, int two_house_rent,int three_house_rent, int four_house_rent, int hotel_rent, int house_price, int hotel_price) {
        super(name, description, ID, price, typeIndex, rent);
        this.house_rent = new int[]{one_house_rent, two_house_rent, three_house_rent, four_house_rent};
        this.hotel_rent = hotel_rent;
        this.house_price = house_price;
        this.hotel_price = hotel_price;
        }

    public int getRent(int tier) {
        if (tier == 0) {
            return rent;
        }
        if (tier > 0 && tier < 5) {
            return house_rent[tier-1];
        }
        else {
            return hotel_rent;
        }
    }

    public int getHouse_price(){
        return house_price;
    }

    public int getHotel_price() {
        return hotel_price;
    }

}
