package Model.Cards;

public class MoveCard extends SuperCard {
    private int movePiece;
    private int moveToField;
    private int type;

    public MoveCard(String desc, int moveType, int amt) {
        super(desc);
        this.type = moveType;

        if(moveType == 1) {
            this.movePiece = amt;
        } else {
            this.moveToField = amt;
        }
    }

    public int getMovePiece() {
        return movePiece;
    }

    public int getMoveToField() {
        return moveToField;
    }

    public int getType() {
        return type;
    }
}
