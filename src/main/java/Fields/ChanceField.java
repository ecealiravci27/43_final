package Fields;

public class ChanceField extends SuperField{

    private boolean drawCard;

    public ChanceField(String name, String description, int ID,boolean drawCard) {
        super(name, description, ID);
        this.drawCard = drawCard;
    }

    public boolean isDrawCard() {
        return drawCard;
    }
}
