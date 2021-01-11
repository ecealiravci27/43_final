import java.*;
public class PropertyCollection {

    public Property[] properties;

    public Property[] setupProperty() {
        properties = new Property[27];
        properties[0] = new CanBuildHouse(2);
        properties[1] = new CanBuildHouse(4);
        properties[2] = new Property(6);
        properties[3] = new CanBuildHouse(7);
        properties[4] = new CanBuildHouse(9);
        properties[5] = new CanBuildHouse(10);
        properties[6] = new CanBuildHouse(12);
        properties[7] = new Property(13);
        properties[8] = new CanBuildHouse(14);
        properties[9] = new CanBuildHouse(15);
        properties[10] = new Property(16);
        properties[11] = new CanBuildHouse(17);
        properties[12] = new CanBuildHouse(19);
        properties[13] = new CanBuildHouse(20);
        properties[14] = new CanBuildHouse(22);
        properties[15] = new CanBuildHouse(24);
        properties[16] = new CanBuildHouse(25);
        properties[17] = new Property(26);
        properties[18] = new CanBuildHouse(27);
        properties[19] = new CanBuildHouse(28);
        properties[20] = new Property(29);
        properties[21] = new CanBuildHouse(30);
        properties[22] = new CanBuildHouse(32);
        properties[23] = new CanBuildHouse(33);
        properties[24] = new CanBuildHouse(35);
        properties[25] = new Property(36);
        properties[26] = new CanBuildHouse(38);

        return properties;
    }

    public void gainOwnership(int owner, int fieldID) {
        int i;
        for (i = 0; i < properties.length; i++) {
            if(properties[i].getID() == fieldID) {
                properties[i].setOwner(owner);
            }
        }
    }

    public void removeOwnership(int fieldID) {
        int owner = 0;
        int i;
        for (i = 0; i < properties.length; i++) {
            if(properties[i].getID() == fieldID) {
                properties[i].setOwner(owner);
            }
        }
    }

}
