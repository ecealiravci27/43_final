package Model;

public class Piece {
    private String pieceColor;
    private int totalMoves;
    private String[] Color = new String[]{"Red", "Blue", "Green", "Yellow", "Orange", "Purple"};

    public Piece(int typeIndex){
        this.pieceColor = Color[typeIndex];
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

    public String getColor(){
        return pieceColor;
    }

}