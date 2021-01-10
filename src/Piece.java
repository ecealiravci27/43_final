public class Piece {
    String pieceColor;
    int totalMoves;
    String[] Color = new String[]{"Red", "Blue", "Green", "Yellow", "Orange", "Purple"};

    public Piece(int typeIndex){
        this.pieceColor = Color[typeIndex];
        this.totalMoves = 0;

    }

    public int movePiece(int addMove) {
        totalMoves = totalMoves + addMove;
        return addMove;
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