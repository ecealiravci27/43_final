import Fields.PropertyField;
import Fields.SuperField;
import Fields.VacantField;
import gui_fields.GUI_Field;
import gui_main.GUI;

public class PropertyCollection {
    public static int getTotalPropertyFields(SuperField[] board) {
        int counter = 0;

        for (SuperField f: board)
            if(f instanceof PropertyField)
                counter++;

        return counter;
    }

    public static Property[] setupProperty(SuperField[] board) {
        Property[] properties = new Property[getTotalPropertyFields(board)];
        SuperField field;
        int curPos = 0;

        for (int i = 0; i < board.length; i++) {

            field = board[i];

            if(field instanceof Fields.VacantField) {
                properties[curPos] = new CanBuildHouse(field.getID(), ((VacantField) field).getTypeIndex());
                curPos++;
            } else if((field instanceof Fields.ShippingField) || (field instanceof Fields.CoorporationField)) {
                properties[curPos] = new Property(field.getID());
                curPos++;
            }
        }

        return properties;
    }

    public static boolean isGroupOwned(int owner, int groupID, Property[] properties) {
        boolean owned = true;

        for (int i = 0; i < properties.length; i++) {
            if(properties[i] instanceof CanBuildHouse) {
                if(properties[i].getOwner() != owner && ((CanBuildHouse) properties[i]).getGroup() == groupID) {
                    owned = false;
                    break;
                }
            }
        }

        System.out.println(owned);
        return owned;
    }

    public static void main(String[] args) {
        SuperField[] board = new Board().setupField();
        //System.out.println(setupBoard(board));
        Property[] properties = new PropertyCollection().setupProperty(board);
        //System.out.println(properties);
        isGroupOwned(1, 2, properties);
    }

    public void gainOwnership(int owner, int fieldID,  Property[] properties) {
        int i;
        for (i = 0; i < properties.length; i++) {
            if(properties[i].getID() == fieldID) {
                properties[i].setOwner(owner);
            }
        }
    }

    public void removeOwnership(int fieldID,  Property[] properties) {
        int owner = 0;
        int i;
        for (i = 0; i < properties.length; i++) {
            if(properties[i].getID() == fieldID) {
                properties[i].setOwner(owner);
            }
        }
    }

}
