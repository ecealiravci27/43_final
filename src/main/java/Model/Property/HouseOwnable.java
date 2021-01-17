package Model.Property;

public class HouseOwnable extends Ownable {
    private int numberOfHouses;
    private final int group;

    public HouseOwnable(int ID, int type, int group) {
        super(ID, type);
        this.numberOfHouses = 0;
        this.group = group;
    }


    public int getNumberOfHouses() {
        return numberOfHouses;
    }


    public void removeHouse() {
        numberOfHouses--;
    }

    public int getGroup() {
        return group;
    }
}
