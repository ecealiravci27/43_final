public class Player {

    private int playerPosition;
    private int playerID;
    private Piece playerPiece;
    private Account playerAccount;

    public Player(int ID) {
        this.playerID = ID;
        this.playerPiece = new Piece(ID);
        this.playerPosition = 0;
        this.playerAccount = new Account(30000);
    }

    public void movePiece(int addMove) {
        playerPiece.movePiece(addMove);
    }

    public int getMoves() {

        return playerPiece.getMoves();
    }

    public void setPlayerPosition(int playerPosition) {

        this.playerPosition = playerPosition;
    }

    public void calculatePlayerPosition() {

        setPlayerPosition(getMoves()%40+1);
    }

    public int fetchPlayerPosition() {

        calculatePlayerPosition();

        return getPlayerPosition();
    }

    public int getPlayerPosition() {

        return playerPosition;
    }




}
