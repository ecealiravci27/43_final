public class PropertyPlayerController {

    private Player player;

    public PropertyPlayerController() {
        this.player = new Player(1);
    }

    public void movePiece(int addMove) {

        player.movePiece(addMove);
    }

    public int getPlayerPosition() {

        return player.fetchPlayerPosition();
    }
}
