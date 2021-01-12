package Model;

public class Player {

    private int playerPosition;
    private int playerID;
    private final Piece playerPiece;
    private Account playerAccount;

    public Player(int ID) {
        this.playerID = ID;
        this.playerPiece = new Piece();
        this.playerPosition = 0;
        this.playerAccount = new Account(30000);
    }

    public void movePiece(int addMove) {
        playerPiece.movePiece(addMove);
        playerPosition = (playerPiece.getMoves()%40+1);

    }

    public int getPlayerPosition() {
        return playerPosition;
    }




}
