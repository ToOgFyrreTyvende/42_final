import Controller.GameController;
import Model.*;
import View.GameGUIView;

/**
 * The main class that starts the game by instantiating GameBoard, GameGUIView and GameController classes.
 */

public class Main {
    public static void main(String[] args){
        GameBoard board = new GameBoard();
        GameGUIView view = new GameGUIView();
        GameController game = new GameController(board, view);
    }
}
