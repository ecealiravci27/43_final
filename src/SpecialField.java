public class SpecialField extends SuperField{

    private final boolean goToJail;
    private final boolean payTaxes;

    public SpecialField(String name, String description, int ID,boolean goToJail, boolean payTaxes) {
        super(name, description, ID);
        this.goToJail = goToJail;
        this.payTaxes = payTaxes;
    }

    public boolean isGOtoJail(){
        return goToJail;
    }
    public boolean isPayTaxes(){
        return payTaxes;
    }

}
