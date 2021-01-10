public class SpecialField extends SuperField{

    private final boolean goToJail;
    private final boolean payTaxes;
    private final boolean goPark;
    private final int taxes;

    public SpecialField(String name, String description, int ID,boolean goToJail, boolean payTaxes, boolean goPark, int taxes ) {
        super(name, description, ID);
        this.goToJail = goToJail;
        this.payTaxes = payTaxes;
        this.goPark = goPark;
        this.taxes = taxes;
    }

    public boolean isGOtoJail(){
        return goToJail;
    }
    public boolean isPayTaxes(){
        return payTaxes;
    }
    public boolean isGoPark(){
        return goPark;
    }
    public int getTaxes(){
        return taxes;
    }
}
