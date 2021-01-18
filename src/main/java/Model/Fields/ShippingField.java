package Model.Fields;

public class ShippingField extends OwnableField {

    public ShippingField(String name, String description, int ID, int price, int rent) {
        super(name, description, ID, price, rent);
    }

    @Override
    public int getRent(int eyesum, int owned_ShippingFields) {

        int temp = (int) (rent*Math.pow(2,(owned_ShippingFields-1)));

        return temp;
    }

}
