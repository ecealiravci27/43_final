public class CanBuildHouse extends Property{
    private int numberOfHouses;

    public CanBuildHouse(int ID) {
        super(ID);
        this.numberOfHouses = 0;
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
}
