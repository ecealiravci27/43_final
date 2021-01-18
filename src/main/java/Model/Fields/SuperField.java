package Model.Fields;

//all fields on the board, all other field classes inherit from this class
public abstract class SuperField {
    protected String fieldName;
    protected String fieldDescription;
    protected int ID;

    public SuperField(String name, String description, int ID){
        this.fieldName = name;
        this.fieldDescription = description;
        this.ID = ID;
    }

    //returns the descriptions of each field
    public String getFieldDescription(){
        return fieldDescription;
    }

    //returns the name of each field
    public String getFieldName() {
        return fieldName;
    }

    //returns the ID of each field, relevant for the field positons on the board
    public int getID() {
        return ID;
    }
}
