package Model.Property;

import Model.Board;
import Model.Fields.OwnableField;
import Model.Fields.SuperField;
import Model.Fields.VacantField;

public class PropertyManager {
    public static int getTotalPropertyFields(SuperField[] board) {
        int counter = 0;

        for (SuperField f: board)
            if(f instanceof OwnableField)
                counter++;

        return counter;
    }

    public static Property[] setupProperty(SuperField[] board) {
        Property[] properties = new Property[getTotalPropertyFields(board)];
        SuperField field;
        int curPos = 0;

        for (int i = 0; i < board.length; i++) {

            field = board[i];

            if(field instanceof Model.Fields.VacantField) {
                properties[curPos] = new HouseProperty(field.getID(), ((VacantField) field).getTypeIndex());
                curPos++;
            } else if((field instanceof Model.Fields.ShippingField) || (field instanceof Model.Fields.CoorporationField)) {
                properties[curPos] = new Property(field.getID());
                curPos++;
            }
        }

        return properties;
    }

    public static boolean isGroupOwned(int owner, int groupID, Property[] properties) {
        boolean owned = true;

        for (int i = 0; i < properties.length; i++) {
            if(properties[i] instanceof HouseProperty) {
                if(properties[i].getOwner() != owner && ((HouseProperty) properties[i]).getGroup() == groupID) {
                    owned = false;
                    break;
                }
            }
        }

        System.out.println(owned);
        return owned;
    }

//    public static void main(String[] args) {
//        SuperField[] board = new Board().setupField();
//        //System.out.println(setupBoard(board));
//        Property[] properties = new PropertyManager().setupProperty(board);
//        //System.out.println(properties);
//        isGroupOwned(1, 2, properties);
//    }

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
