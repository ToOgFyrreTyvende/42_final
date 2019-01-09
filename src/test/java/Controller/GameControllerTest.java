package Controller;

import Model.GameBoard;
import Model.Game;
import View.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stub.DiceStub;
import stub.ViewStub;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    // Variables
    private Game testGame;
    private String[] testNavne;
    private GameView testView;
    private GameBoard testBoard;
    DiceStub testTerning;
    private GameController testSpilCtrl;


    @BeforeEach
    void setUp() {
        testBoard = new GameBoard();

        testView = new ViewStub();
        testSpilCtrl = new GameController(testBoard, testView);

    }

    //@Test
    void spilAktivtTest() {
        assertTrue(testSpilCtrl.getGame().isEnded());
    }
}