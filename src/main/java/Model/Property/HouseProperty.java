package Model.Property;

public class HouseProperty extends Property{
    private int numberOfHouses;
    private int group;

    public HouseProperty(int ID, int group) {
        super(ID);
        this.numberOfHouses = 0;
        this.group = group;
    }

    public void setNumberOfHouses(int numberOfHouses) {
        this.numberOfHouses = numberOfHouses;
    }

    public int getNumberOfHouses() {
        return numberOfHouses;
    }

    public void addHouse() {
        numberOfHouses++;
    }

    public int getGroup() {
        return group;
    }
}