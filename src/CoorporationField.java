public class CoorporationField extends PropertyField {

    int multiplier_for_one;
    int getMultiplier_for_two;

    public CoorporationField(String name, String description, int ID, int price, int typeIndex, int rent) {
        super(name, description, ID, price, typeIndex, rent);
        this.multiplier_for_one = 100;
        this.getMultiplier_for_two = 200;
    }

    @Override
    public int getRent() {
        return 0;
    }

    public int getRent(int eyesum, int owned_coorporations) {
        int rent;
        if (owned_coorporations == 1) {
            rent = eyesum * multiplier_for_one;
        } else {
            rent = eyesum * getMultiplier_for_two;
        }
        return rent;
    }
}
