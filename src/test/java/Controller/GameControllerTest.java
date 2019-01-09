package Controller;

import Model.GameBoard;
import Model.Game;
import View.GameView;
import org.junit.jupiter.api.BeforeEach;
import stub.TerningStub;
import stub.ViewStub;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    // Variables
    private Game testGame;
    private String[] testNavne;
    private GameView testView;
    private GameBoard testBoard;
    TerningStub testTerning;
    private GameController testSpilCtrl;


    @BeforeEach
    void setUp() {
        testNavne = new String[2];
        testNavne[0] = "dummyOne";
        testNavne[1] = "dummyTwo";

        testBoard = new GameBoard();

        testView = new ViewStub();
        testSpilCtrl = new GameController(testBoard, testView);

    }

    //@Test
    void spilAktivtTest() {
        assertTrue(testSpilCtrl.getGame().isEnded());
    }
}