package Fields;

public abstract class SuperField {
    protected String fieldName;
    protected String fieldDescription;
    protected int ID;

    public SuperField(String name, String description, int ID){
        this.fieldName = name;
        this.fieldDescription = description;
        this.ID = ID;
    }
    public String getFieldDescription(){
        return fieldDescription;
    }
    public String getFieldName() {
        return fieldName;
    }
    public boolean isTypeOf(Class type) {
        return this.getClass().equals(type);
    }
}
