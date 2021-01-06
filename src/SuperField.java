public abstract class SuperField {
    protected String fieldName;
    protected String fieldDescription;

    public SuperField(String name, String description){
        this.fieldName = "";
        this.fieldDescription = "";
    }

    public String getFieldName() {
        return fieldName;
    }
    public String getFieldDescription(){
        return fieldDescription;
    }
}
