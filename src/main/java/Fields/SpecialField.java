package Fields;

public class SpecialField extends SuperField{
    private int taxes;
    private String Type;

    public SpecialField(String name, String description, int ID, int taxes, String type) {
        super(name, description, ID);
        this.taxes = taxes;
        this.Type = type;
    }

    public String getType() {
        return Type;
    }

    public int getTaxes(){
        return taxes;
    }
}
