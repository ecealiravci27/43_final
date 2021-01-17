package Model.Property;

public class HouseOwnable extends Ownable {
    private int numberOfHouses;

    public HouseOwnable(int ID, int type) {
        super(ID, type);
        this.numberOfHouses = 0;
    }
    public int getNumberOfHouses() {
        return numberOfHouses;
    }


}
