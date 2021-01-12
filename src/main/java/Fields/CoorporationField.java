package Fields;

import Fields.PropertyField;

public class CoorporationField extends PropertyField {

    private final int multiplier_for_one;
    private final int getMultiplier_for_two;

    public CoorporationField(String name, String description, int ID, int price, int rent) {
        super(name, description, ID, price, rent);
        this.multiplier_for_one = 100;
        this.getMultiplier_for_two = 200;
    }

    @Override
    public int getRent(int eyesum, int owned_coorporations, int tier) {
        if (owned_coorporations == 1) {
            rent = eyesum * multiplier_for_one;
        }
        if (owned_coorporations == 2){
            rent = (2*rent)* getMultiplier_for_two;
        }
        return rent;
    }
}
