package Model.Controller;

import Model.Board;
import Model.Fields.OwnableField;
import Model.Fields.SuperField;
import Model.Fields.VacantField;
import Model.Property.HouseOwnable;
import Model.Property.Ownable;

public class PropertyManager {

    Ownable[] properties;

    public PropertyManager(SuperField[] board) {
        this.properties = setupProperty(board);
    }


    public Ownable getOwnable(int fieldID) {

        int i;

        for (i = 0; i < properties.length; i++) {
            if (properties[i].getID() == fieldID) {
                break;
            }
        }
        return properties[i];
    }

    public int getTotalPropertyFields(SuperField[] board) {
        int counter = 0;

        for (SuperField f : board)
            if (f instanceof OwnableField)
                counter++;

        return counter;
    }

    public Ownable[] setupProperty(SuperField[] board) {
        Ownable[] properties = new Ownable[getTotalPropertyFields(board)];
        SuperField field;
        int curPos = 0;

        for (int i = 0; i < board.length; i++) {

            field = board[i];

            if (field instanceof Model.Fields.VacantField) {
                properties[curPos] = new HouseOwnable(field.getID(), Ownable.VACANT_TYPE, ((VacantField) field).getTypeIndex());
                curPos++;
            } else if ((field instanceof Model.Fields.CoorporationField)) {
                properties[curPos] = new Ownable(field.getID(), Ownable.COORP_TYPE);
                curPos++;
            } else if ((field instanceof Model.Fields.ShippingField)) {
                properties[curPos] = new Ownable(field.getID(), Ownable.SHIPPING_TYPE);
                curPos++;
            }
        }

        return properties;
    }

    public boolean isGroupOwned(int owner, int groupID) {
        boolean owned = true;

        for (int i = 0; i < properties.length; i++) {
            if (properties[i] instanceof HouseOwnable) {
                if (properties[i].getOwner() != owner && ((HouseOwnable) properties[i]).getGroup() == groupID) {
                    owned = false;
                    break;
                }
            }
        }
        return owned;
    }

    public int numberOfOwned(int owner, int fieldID) {
        int type = 0;
        int entities = 0;

        for (int i = 0; i < properties.length; i++) {

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

    public void setOwnerShip(int owner, int fieldID) {
        int i;
        for (i = 0; i < properties.length; i++) {
            if (properties[i].getID() == fieldID) {
                properties[i].setOwner(owner);
            }
        }
    }

    public void removeOwnership(int fieldID) {
        int owner = 10;
        int i;
        for (i = 0; i < properties.length; i++) {
            if (properties[i].getID() == fieldID) {
                properties[i].setOwner(owner);
            }
        }
    }

    public void addHouse(int fieldID) {
        Ownable house = getOwnable(fieldID);
        ((HouseOwnable) house).addHouse();
    }

    public int getHouses(int fieldID) {
        Ownable house = getOwnable(fieldID);
        return ((HouseOwnable) house).getNumberOfHouses();
    }


    public int[] getOwnedHouseOwnables(int playerID) {

        int counter = 0;
        int index = 0;

        for (int i = 0; i < properties.length; i++) {

            if (properties[i] instanceof HouseOwnable && playerID == properties[i].getOwner()) {
                counter++;
            }
        }

        int[] ownAblesID = new int[counter];

        for (int j = 0; j < properties.length; j++) {
            if (properties[j] instanceof HouseOwnable && playerID == properties[j].getOwner()) {

                ownAblesID[index] = properties[j].getID();
                if (index < counter - 1) {
                    index++;
                }
            }
        }
        return ownAblesID;
    }

    public int[] getOwnedOwnables(int playerID) {

        int counter = 0;
        int index = 0;

        for (int i = 0; i < properties.length; i++) {

            if (playerID == properties[i].getOwner()) {
                counter++;
            }
        }
        int[] ownAblesID = new int[counter];

        for (int j = 0; j < properties.length; j++) {
            if (playerID == properties[j].getOwner()) {

                ownAblesID[index] = properties[j].getID();
                if (index < counter - 1) {
                    index++;
                }
            }
        }
        return ownAblesID;
    }
}


