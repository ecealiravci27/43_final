package Model;

public class Piece {
    private int totalMoves;

    public Piece(){
        this.totalMoves = 0;
    }

    public void movePiece(int addMove) {
        totalMoves = totalMoves + addMove;
    }

    public void setTotalMoves(int position){
        totalMoves = position;
    }

    public int getMoves() {
        return totalMoves;
    }
}