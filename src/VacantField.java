public class VacantField extends PropertyField{

    int one_house_rent;
    int two_house_rent;
    int three_house_rent;
    int four_house_rent;
    int house_price;
    int hotel_price;

    public VacantField(String name, String description, int price, int typeIndex, int Rent, int one_house_rent,int two_house_rent, int three_house_rent, int four_house_rent, int house_price, int hotel_price) {
        super(name, description, price, typeIndex, Rent);
        this.one_house_rent = one_house_rent;
        this.two_house_rent = two_house_rent;
        this.three_house_rent = three_house_rent;
        this.four_house_rent = four_house_rent;
        this.house_price = house_price;
        this.hotel_price = hotel_price;
    }
}
