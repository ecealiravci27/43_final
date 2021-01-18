package Model.Cards;

public class MoveCard extends SuperCard {
    private int movePiece;
    private int moveType;

    public MoveCard(String desc, int moveType, int movePiece) {
        super(desc);
        this.moveType = moveType;
        this.movePiece = movePiece;
    }

    public int getMovePiece() {
        return movePiece;
    }

    public int getType() {
        return moveType;
    }
}
