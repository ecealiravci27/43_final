// This class is meant to keep track of, and change variables concerning a players piece

package Model;

//Following method keeps track of the pieces totalmoves.
public class Piece {
    private int totalMoves;

    public Piece(){
        this.totalMoves = 0;
    }

    //method to change the pieces position by adding moves
    public void movePiece(int addMove) {
        totalMoves = totalMoves + addMove;
    }

    //method to set the position of the piece.
    public void setTotalMoves(int position){
        totalMoves = position;
    }
    // method to return the totalmoves
    public int getMoves() {
        return totalMoves;
    }
}