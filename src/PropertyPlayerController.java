public class PropertyPlayerController {

    private Player player;
    private int playerAmount;
    private Player[] playerArray;

    public PropertyPlayerController(int playerAmount) {

        this.playerArray = setupPlayer(playerAmount);

    }

    public void movePiece(int addMove) {

        player.movePiece(addMove);
    }

    public int getPlayerPosition() {

        return player.fetchPlayerPosition();
    }

    public Player[] setupPlayer(int playerAmount) {

        playerArray = new Player[playerAmount];

        for (int i = 0; i < playerAmount; i++) {

            playerArray[i] = new Player(i++);

        }
        return playerArray;
    }
}
