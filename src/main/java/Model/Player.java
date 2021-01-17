package Model;

public class Player {

    private int playerPosition;
    private int playerID;
    private String name;
    private final Piece playerPiece;
    private Account playerAccount;
    private boolean bankrupt;
    private boolean jailed;
    private int oldPlayerPosition;
    private boolean freeCard;

    public Player(int ID) {
        this.playerID = ID;
        this.playerPiece = new Piece();
        this.playerPosition = 0;
        this.playerAccount = new Account(30000);
        this.bankrupt = false;
        this.jailed = false;
        this.freeCard = false;
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
        bankrupt = true;
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
        if (getBalance() == 0){
            bankrupt();
        }
    }

    public void reduceBalance(int change) {

        playerAccount.reduceBalance(change);
        if (getBalance() == 0){
            bankrupt();
        }
    }

    public void setBalance(int change) {

        playerAccount.setBalance(change);
    }
    public int getBalance() {

        return playerAccount.getBalance();

    }

    public boolean isBankrupt(){
        return bankrupt;
    }

    public void jail() {
        if (!freeCard) {
            this.jailed = true;
        }
        if (freeCard){
            freeCard = false;
        }
    }

    public void gainFreeCard(){
        freeCard = true;
    }

    public void spendFreeCard(){
        freeCard = false;
    }
    public boolean hasFreeCard(){
        return freeCard;
    }

    public boolean isJailed(){
        return jailed;
    }
}
