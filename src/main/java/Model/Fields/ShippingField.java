package Model.Fields;

//all the shipping fields on the board, inherits from OwnableField
public class ShippingField extends OwnableField {

    public ShippingField(String name, String description, int ID, int price, int rent) {
        super(name, description, ID, price, rent);
    }

    //rent is multiplied depending on how many shipping fields are owned by the same player
    @Override
    public int getRent(int eyesum, int owned_ShippingFields) {

        int temp = (int) (rent*Math.pow(2,(owned_ShippingFields-1)));

        return temp;
    }

}
