package Model.Property;

import Model.Board;
import Model.Fields.OwnableField;
import Model.Fields.SuperField;
import Model.Fields.VacantField;

public class PropertyManager {

    Ownable[] properties;

    public PropertyManager(SuperField[] board) {

        this.properties = setupProperty(board);
    }

    public int getTotalPropertyFields(SuperField[] board) {
        int counter = 0;

        for (SuperField f: board)
            if(f instanceof OwnableField)
                counter++;

        return counter;
    }

    public Ownable[] setupProperty(SuperField[] board) {
        Ownable[] properties = new Ownable[getTotalPropertyFields(board)];
        SuperField field;
        int curPos = 0;

        for (int i = 0; i < board.length; i++) {

            field = board[i];

            if(field instanceof Model.Fields.VacantField) {
                properties[curPos] = new HouseOwnable(field.getID(), Ownable.VACANT_TYPE,((VacantField) field).getTypeIndex());
                curPos++;
            } else if((field instanceof Model.Fields.CoorporationField)) {
                properties[curPos] = new Ownable(field.getID(), Ownable.COORP_TYPE);
                curPos++;
            } else if((field instanceof Model.Fields.ShippingField)) {
                properties[curPos] = new Ownable(field.getID(), Ownable.SHIPPING_TYPE);
                curPos++;
            }
        }

        return properties;
    }

    public boolean isGroupOwned(int owner, int groupID) {
        boolean owned = true;

        for (int i = 0; i < properties.length; i++) {
            if(properties[i] instanceof HouseOwnable) {
                if(properties[i].getOwner() != owner && ((HouseOwnable) properties[i]).getGroup() == groupID) {
                    owned = false;
                    break;
                }
            }
        }

        System.out.println(owned);
        return owned;
    }

    public int numberOfOwned(int owner, int fieldID) {

        int type = 0;
        int entities = 0;

        for (int i = 0; i < properties.length ; i++) {

            if (properties[i].getID() == fieldID) {
                type = properties[i].getType();
                break;
            }
        }

        for (int j = 0; j < properties.length; j++) {

            if (owner == properties[j].getOwner() && type == properties[j].getType()) {
                entities++;
            }
        }

        return entities;
        }


//    public static void main(String[] args) {
//        SuperField[] board = new Board().setupField();
//        //System.out.println(setupBoard(board));
//        Property[] properties = new PropertyManager().setupProperty(board);
//        //System.out.println(properties);
//        isGroupOwned(1, 2, properties);
//    }

    public void gainOwnership(int owner, int fieldID) {
        int i;
        for (i = 0; i < properties.length; i++) {
            if(properties[i].getID() == fieldID) {
                properties[i].setOwner(owner);
            }
        }
    }

    public void removeOwnership(int fieldID,  Ownable[] properties) {
        int owner = 0;
        int i;
        for (i = 0; i < properties.length; i++) {
            if(properties[i].getID() == fieldID) {
                properties[i].setOwner(owner);
            }
        }
    }

//Temporary main method for testing

    public static void main(String[] args) {

        Board board = new Board();

        PropertyManager propertyManager = new PropertyManager(board.getField());

        propertyManager.numberOfOwned(0,6);


    }
}


