package Model.Fields;

//all fields that is a coorporation, and inherits from OwnableField
public class CoorporationField extends OwnableField {

    private final int multiplier_for_one;
    private final int getMultiplier_for_two;

    public CoorporationField(String name, String description, int ID, int price, int rent) {
        super(name, description, ID, price, rent);
        this.multiplier_for_one = 100;
        this.getMultiplier_for_two = 200;
    }

    //conditions for how much the rent is multiplied (values specified in constructor), depending on how many of the coorporations are owned by the same player
    @Override
    public int getRent(int eyesum, int owned_coorporations) {
        int tempRent = 0;
        if (owned_coorporations == 1) {
            tempRent = eyesum * multiplier_for_one;
        }
        if (owned_coorporations == 2){
            tempRent = (2*rent)* getMultiplier_for_two;
        }
        return tempRent;
    }
}
