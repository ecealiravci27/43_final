public class PropertyPlayerController {

    private Player player;
    private int playerAmount;
    private Player[] playerArray;

    public PropertyPlayerController(int playerAmount) {

        this.playerArray = setupPlayer(playerAmount);

    }

    public void movePiece(int eyeSum, int ID) {

        playerArray[ID-1].movePiece(eyeSum);
    }

    public int getPlayerPosition(int ID) {
        return playerArray[ID-1].getPlayerPosition();
    }

    public Player[] setupPlayer(int playerAmount) {

        playerArray = new Player[playerAmount];

        for (int i = 0; i < playerAmount; i++) {

            playerArray[i] = new Player(i+1);

        }
        return playerArray;
    }

}
