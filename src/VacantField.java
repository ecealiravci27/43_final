public class VacantField extends PropertyField {

    int[] house_rent;
    int hotel_rent;
    int house_price;
    int hotel_price;

    public VacantField(String name, String description, int price, int typeIndex, int Rent, int one_house_rent, int two_house_rent, int three_house_rent, int four_house_rent, int hotel_rent, int house_price, int hotel_price) {
        super(name, description, price, typeIndex, Rent);
        this.house_rent = new int[]{one_house_rent, two_house_rent, three_house_rent, four_house_rent};
        this.hotel_rent = hotel_rent;
        this.house_price = house_price;
        this.hotel_price = hotel_price;
    }

    public int getrent(int value) {
        if (value == 0) {
            return Rent;
        }
        if (value > 0 && value < 5) {
            return house_rent[value];
        }
        else {
            return hotel_rent;
        }
    }
}
