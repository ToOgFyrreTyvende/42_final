import Controller.GameController;
import Model.*;
import View.GameGUIView;

/**
 * ------------------------------------------------------------/
 * Dette er hovedklassen som starter spillet.
 * ------------------------------------------------------------/
 */

public class Main {
    public static void main(String[] args){
        GameBoard board = new GameBoard();
        GameGUIView view = new GameGUIView();
        GameController game = new GameController(board, view);
    }
}
