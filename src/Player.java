public class Player {

    private int playerPosition;
    private int playerID;
    private Piece playerPiece;
    private Account playerAccount;

    public Player(int playerNumber) {
        this.playerID = playerNumber;
        this.playerPiece = new Piece(playerNumber);
        this.playerPosition = 0;
        this.playerAccount = new Account(30000);
    }

    public void movePiece(int addMove) {
        playerPiece.movePiece(addMove);
    }

    public int getMoves() {

        return playerPiece.getMoves();
    }

}
