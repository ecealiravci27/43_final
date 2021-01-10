public class ShippingField extends PropertyField {

    private int rent_for_one;
    private int rent_for_two;
    private int rent_for_three;


    public ShippingField(String name, String description, int ID, int price, int rent) {
        super(name, description, ID, price, rent);
    }

    public int getRent(int owned_ShippinFields){
        if (owned_ShippinFields == 2){
            rent = rent*2;
        }
        if (owned_ShippinFields == 3){
            rent = rent*3;
        }
        if (owned_ShippinFields == 4){
            rent = rent*4;
        }
        return rent;
    }

}
