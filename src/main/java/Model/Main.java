//class to run the Matador game.

package Model;
import Model.Controller.Controller;

public class Main {

    public static void main (String[] args) {

        Controller controller = new Controller();
        controller.startGame();
    }
}
