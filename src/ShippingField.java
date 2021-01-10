public class ShippingField extends PropertyField {

    private int rent_for_one;
    private int rent_for_two;
    private int rent_for_three;


    public ShippingField(String name, String description, int ID, int price, int rent) {
        super(name, description, ID, price, rent);
    }

    @Override
    public int getRent(int eyesum, int owned_ShippingFields, int tier) {
        if (owned_ShippingFields == 2){
            rent = rent*2;
        }
        if (owned_ShippingFields == 3){
            rent = rent*3;
        }
        if (owned_ShippingFields == 4){
            rent = rent*4;
        }
        return rent;
    }

}
