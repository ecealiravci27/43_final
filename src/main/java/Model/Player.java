package Model;

public class Player {

    private int playerPosition;
    private int playerID;
    private String name;
    private final Piece playerPiece;
    private Account playerAccount;
    private boolean bancrupt;
    private boolean jailed;
    private int oldPlayerPosition;

    public Player(int ID) {
        this.playerID = ID;
        this.playerPiece = new Piece();
        this.playerPosition = 0;
        this.playerAccount = new Account(30000);
        this.bancrupt = false;
        this.jailed = false;
    }

    public void movePiece(int addMove) {
        oldPlayerPosition = playerPosition;
        playerPiece.movePiece(addMove);
        playerPosition = (playerPiece.getMoves()%40);
    }

    public  void setFree (){
        jailed = false;
    }

    public void bankrupt(){
        bancrupt = true;
    }

    public void setPiece(int position){
        playerPiece.setTotalMoves(position);
        oldPlayerPosition = playerPosition;
        playerPosition = (playerPiece.getMoves()%40);
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public int getOldPlayerPosition() {
        return oldPlayerPosition;
    }

    public void addBalance(int change) {

        playerAccount.addBalance(change);
    }

    public void reduceBalance(int change) {

        playerAccount.reduceBalance(change);
    }

    public void setBalance(int change) {

        playerAccount.setBalance(change);
    }
    public int getBalance() {

        return playerAccount.getBalance();

    }

    public boolean isBankrupt(){
        return bancrupt;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName(int ID) {

        return name;
    }

    public void jail(){
        this.jailed = true;
    }

    public boolean isJailed(){
        return jailed;
    }
}
