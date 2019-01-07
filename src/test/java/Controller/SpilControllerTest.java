package Controller;

import Model.GameBoard;
import Model.Spil;
import Model.Spiller;
import View.GameGUIView;
import View.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stub.TerningStub;
import stub.ViewStub;

import static org.junit.jupiter.api.Assertions.*;

class SpilControllerTest {
    // Variables
    private Spil testSpil;
    private String[] testNavne;
    GameView testView;
    private GameBoard testBoard;
    TerningStub testTerning;
    SpilController testSpilCtrl;


    @BeforeEach
    void setUp() {
        testNavne = new String[2];
        testNavne[0] = "dummyOne";
        testNavne[1] = "dummyTwo";

        testBoard = new GameBoard();

        testView = new ViewStub();
        testSpilCtrl = new SpilController(testBoard, testView);

    }

    //@Test
    void spilAktivtTest() {
        assertTrue(testSpilCtrl.getSpil().isAfsluttet());
    }
}