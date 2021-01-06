public abstract class SuperField {
    protected String fieldName;
    protected String fieldDescription;
    protected int ID;

    public SuperField(String name, String description, int ID){
        this.fieldName = "";
        this.fieldDescription = "";
        this.ID = ID;
    }
    public String getFieldDescription(){
        return fieldDescription;
    }
}
