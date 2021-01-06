public class Piece {
    String pieceColor;
    int totalMoves;
    String[] Color = new String[]{"Red", "Blue", "Green", "Yellow", "Orange", "Purple"};

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
    public String getColor(){
        return pieceColor;
    }

    public String[] setColor(String[] pieceColor){
        Color = pieceColor;
        return Color;
    }
}