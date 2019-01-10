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
    private String[] testNames;
    private GameView testView;
    private GameBoard testBoard;
    DiceStub testDice;
    private GameController testGameCtrl;


    @BeforeEach
    void setUp() {
        testBoard = new GameBoard();

        testView = new ViewStub();
        testGameCtrl = new GameController(testBoard, testView);

    }

    @Test
    void spilAktivtTest() {
        assertTrue(testGameCtrl.getGame().isEnded());
    }
}