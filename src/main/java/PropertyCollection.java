public class PropertyCollection {

    public Property[] properties;

    public Property[] setupProperty() {
        properties = new Property[27];
        properties[0] = new CanBuildHouse(2, 0);
        properties[1] = new CanBuildHouse(4, 0);
        properties[2] = new Property(6);
        properties[3] = new CanBuildHouse(7,1);
        properties[4] = new CanBuildHouse(9,1);
        properties[5] = new CanBuildHouse(10,1);
        properties[6] = new CanBuildHouse(12,2);
        properties[7] = new Property(13);
        properties[8] = new CanBuildHouse(14,2);
        properties[9] = new CanBuildHouse(15,2);
        properties[10] = new Property(16);
        properties[11] = new CanBuildHouse(17,3);
        properties[12] = new CanBuildHouse(19,3);
        properties[13] = new CanBuildHouse(20,3);
        properties[14] = new CanBuildHouse(22, 4);
        properties[15] = new CanBuildHouse(24,4);
        properties[16] = new CanBuildHouse(25,4);
        properties[17] = new Property(26);
        properties[18] = new CanBuildHouse(27,5);
        properties[19] = new CanBuildHouse(28,5);
        properties[20] = new Property(29);
        properties[21] = new CanBuildHouse(30,5);
        properties[22] = new CanBuildHouse(32,6);
        properties[23] = new CanBuildHouse(33,6);
        properties[24] = new CanBuildHouse(35,6);
        properties[25] = new Property(36);
        properties[26] = new CanBuildHouse(38,7);
        properties[27] = new CanBuildHouse(40,7);


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

    /*

    public boolean isGroupOwned(int owner) {
        for (int i = 0; i < properties.length; i++) {
            if(properties[i] instanceof CanBuildHouse) {
                for (int j = 0; j < 7; j++) {
                    if (((CanBuildHouse) properties[i]).getGroup() == j && properties[i].getOwner() == owner) {

                    }
                }
            }
        }
    }
*/

}
