import Controller.SpilController;
import Model.*;
import View.GameGUIView;
import View.GameView;

/**
 * ------------------------------------------------------------/ 
 * Dette er hovedklassen som starter spillet.
 * ------------------------------------------------------------/
 */

public class Main {
    public static void main(String[] args) {
        GameBoard braet = new GameBoard();
        GameGUIView view = new GameGUIView();
        SpilController spil = new SpilController(braet, view);

    }

}
