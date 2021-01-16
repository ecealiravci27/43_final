package Model.Fields;

public class ShippingField extends OwnableField {

    private int rent_for_one;
    private int rent_for_two;
    private int rent_for_three;


    public ShippingField(String name, String description, int ID, int price, int rent) {
        super(name, description, ID, price, rent);
    }

    @Override
    public int getRent(int eyesum, int owned_ShippingFields) {

        rent = (int) (defaultRent*Math.pow(2,(owned_ShippingFields-1)));

        return rent;
    }

}
