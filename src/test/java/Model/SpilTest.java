package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stub.TerningStub;

import static org.junit.jupiter.api.Assertions.*;

class SpilTest {
    // Variables
    Spil testSpil;
    String[] testNavne;
    Spiller[] testSpillere;
    GameBoard testBoard;
    TerningStub testTerning;

    @BeforeEach
    void setUp() {
        testNavne = new String[2];
        testNavne[0] = "dummyOne";
        testNavne[1] = "dummyTwo";

        testBoard = new GameBoard();

        testTerning = new TerningStub(1);

        testSpil = new Spil(testBoard, testNavne);
    }

    @Test
    void SpillereTest() {
        testSpil.setSpillere(testSpillere);
        assertTrue(testSpil.getSpillere() == testSpillere);
    }

    @Test
    void spilTurTest() {
        testSpil.setAfsluttet(false);
        testSpil.spilTur();
        assertTrue(testSpil.getAktivSpiller() == testSpil.getSpillere()[1]);

        testSpil.setAfsluttet(true);
        assertTrue(testSpil.spilTur() == null);
    }

    @Test
    void spilReglerFaengselTest() {
        // Sætter aktiv spiller i fængsel
        testSpil.getAktivSpiller().setiFaengsel(true);
        // Sætter aktiv spiller på fængsel feltet
        testSpil.getAktivSpiller().setFelt(testBoard.getFaengsel());
        // Sætter aktiv spiller's pengeværdi til 42
        // - (For at undgå spilleren går falit og afslutter spillet)
        testSpil.getAktivSpiller().setPenge(42);
        // Kører spilRegler metoden
        testSpil.spilRegler(testSpil.getAktivSpiller().getFelt());
        // Tjekker at aktiv spiller ikke længere er i fængsel efter metodekaldet
        assertTrue(!testSpil.getAktivSpiller().isiFaengsel());
        // Tjekker at aktiv spiller har fået trukket 1 fra spillerens pengeværdi (ud af fængsel pris)
        assertTrue(testSpil.getAktivSpiller().getPenge() == 41);
    }

    @Test
    void spilReglerStartTest() {
        // Sætter spillet til at bruge vores TerningStub som kun ruller værdien 1
        testSpil.setTerning(testTerning);
        int testVal = 42;
        // Sætter spiller 1's pengeværdi til 42
        testSpil.getSpillere()[0].setPenge(testVal);
        // Sætter spiller 1 på brættets sidste felt (23)
        testSpil.getSpillere()[0].setFelt(23);
        // Kører spiller 1's tur færdig
        testSpil.spilTur();
        // Tjækker spiller 1's pengeværdi er steget med 2
        assertTrue(testSpil.getSpillere()[0].getPenge() == testVal + testSpil.getRUNDE_PENGE());
    }
}