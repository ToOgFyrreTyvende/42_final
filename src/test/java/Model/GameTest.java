package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stub.DiceStub;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    // Variables
    private Game testGame;
    private String[] testNames;
    private Player[] testPlayers;
    private GameBoard testBoard;
    private DiceStub testDice;

    @BeforeEach
    void setUp() {
        testNames = new String[2];
        testNames[0] = "dummyOne";
        testNames[1] = "dummyTwo";

        testBoard = new GameBoard();

        testDice = new DiceStub(1);

        testGame = new Game(testBoard, testNames);
    }

    @Test
    void PlayersTest() {
        testGame.setPlayers(testPlayers);
        assertSame(testGame.getPlayers(), testPlayers);
    }

    //@Test
    void playTurnTest() {
        testGame.setEnded(false);
        //testGame.playTurn();
        assertSame(testGame.getActivePlayer(), testGame.getPlayers()[1]);

        testGame.setEnded(true);
        //assertNull(testGame.playTurn());
    }

    @Test
    void gameRulesJailTest() {
        // Sætter aktiv player i fængsel
        testGame.getActivePlayer().setInJail(true);
        // Sætter aktiv player på fængsel feltet
        testGame.getActivePlayer().setField(testBoard.getJail());
        // Sætter aktiv player's pengeværdi til 2000
        // - (For at undgå spilleren går falit og afslutter spillet)
        testGame.getActivePlayer().setMoney(2000);
        // Kører gameRules metoden
        testGame.gameRules(testGame.getActivePlayer().getField());
        // Tjekker at aktiv player ikke længere er i fængsel efter metodekaldet
        assertTrue(!testGame.getActivePlayer().isInJail());
        // Tjekker at aktiv player har fået trukket 1 fra spillerens pengeværdi (ud af fængsel pris)
        assertEquals(1000, testGame.getActivePlayer().getMoney());
    }

    @Test
    void gameRulesStartTest() {
        // Sætter spillet til at bruge vores DiceStub som kun ruller værdien 1
        testGame.setDice(testDice);
        int testVal = 2000;
        // Sætter player 1's pengeværdi til 42
        testGame.getPlayers()[0].setMoney(testVal);
        // Sætter player 1 på brættets sidste felt
        testGame.getPlayers()[0].setField(Global.FIELD_COUNT-1);
        // Kører player 1's tur færdig
        //testGame.playTurn();
        // Tjækker player 1's pengeværdi er steget med 2
        assertEquals(testGame.getPlayers()[0].getMoney(), testVal + testGame.getROUND_MONEY());
    }
}