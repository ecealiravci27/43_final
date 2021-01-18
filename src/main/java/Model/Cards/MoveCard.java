package Model.Cards;

//Card that informs a player to move to a specific field either by mentioning the fieldname or number of fields to move
public class MoveCard extends SuperCard {
    private int movePiece;
    private int moveType; //two types of movements: moving to a specific fieldname or moving a number of fields forward

    //inherits from SuperCard class
    public MoveCard(String desc, int moveType, int movePiece) {
        super(desc);
        this.moveType = moveType;
        this.movePiece = movePiece;
    }

    //returns the movement of player piece
    public int getMovePiece() {
        return movePiece;
    }

    //returns the type of movement
    public int getType() {
        return moveType;
    }
}
