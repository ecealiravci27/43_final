package Model.Fields;

//all fields that arent ownable, such as chance fields
public class SpecialField extends SuperField{
    private int taxes;
    private String Type;

    public SpecialField(String name, String description, int ID, int taxes, String type) {
        super(name, description, ID);
        this.taxes = taxes;
        this.Type = type;
    }

    //non ownable fields have different types, and this returns the type
    public String getType() {
        return Type;
    }

    //returns the taxes (taxed fields)
    public int getTaxes(){
        return taxes;
    }


}
