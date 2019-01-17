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
    void setUp(){
        testNames = new String[2];
        testNames[0] = "dummyOne";
        testNames[1] = "dummyTwo";

        testBoard = new GameBoard();

        testDice = new DiceStub(1, false);

        testGame = new Game(testBoard, testNames);

        testGame.setActivePlayer(testGame.getPlayers()[0]);
    }

    @Test
    void PlayersTest(){
        testGame.setPlayers(testPlayers);
        assertSame(testGame.getPlayers(), testPlayers);
    }

    //@Test
    void playTurnTest(){
        testGame.setEnded(false);
        //testGame.playTurn();
        assertSame(testGame.getActivePlayer(), testGame.getPlayers()[1]);

        testGame.setEnded(true);
        //assertNull(testGame.playTurn());
    }

    @Test
    void gameRulesJailTest(){
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
    void gameRulesStartTest(){
        // Sætter spillet til at bruge vores DiceStub som kun ruller værdien 1
        testGame.setDice(testDice);

        // Sætter player 1's pengeværdi til 2000
        int testVal = 2000;
        testGame.getPlayers()[0].setMoney(testVal);

        // Sætter player 1's forrige position til brættets sidste felt
        testGame.getPlayers()[0].setPreviousField(Global.FIELD_COUNT - 1);

        // Kører gamerules() med 0 som fieldId
        testGame.getGameLogic().gameRules(0);

        // Tjækker player 1's pengeværdi er steget med 4000
        assertEquals(testGame.getPlayers()[0].getMoney(), testVal + testGame.getROUND_MONEY());
    }

    @Test
    void buyFieldPlayerIsOnTest(){
        // vi sætter den aktive spiller's penge til 100000
        // og sætter spilleren på feltet 3 (Hvidovrevej)
        testGame.getActivePlayer().setMoney(100000);
        testGame.getActivePlayer().setField(3);

        // udfører buyFieldPlayerIsOn()
        testGame.buyFieldPlayerIsOn(testGame.getActivePlayer());

        // Vi tjekker at spilleren har mistet 1200,-
        assertEquals(98800, testGame.getActivePlayer().getMoney());

        // vi sætter aktiv spiller på et felt som ikke er et PropertyField
        testGame.getActivePlayer().setField(38);

        // udfører buyFieldPlayerIsOn()
        testGame.buyFieldPlayerIsOn(testGame.getActivePlayer());

        // vi tjekker at spilleren ikke har mistet penge
        assertEquals(98800, testGame.getActivePlayer().getMoney());
    }

    @Test
    void getPlayerFieldTypeTest(){
        // giver et PropertyField og et CompanyField til spiller 2
        testGame.getPlayers()[1].setMoney(42000);
        testGame.getPlayers()[1].setField(3);
        testGame.buyFieldPlayerIsOn(testGame.getPlayers()[1]);

        // vi sætter aktiv spiller på feltId 1
        testGame.getActivePlayer().setField(1);

        // vi tjekker at getPlayerFieldType returnerer "PropertyField"
        assertEquals("PropertyField", testGame.getPlayerFieldType(testGame.getActivePlayer()));

        // vi sætter aktiv spiller på feltId 3
        testGame.getActivePlayer().setField(3);

        // vi tjekker at getPlayerFieldType returnerer "PropertyFieldOwned"
        assertEquals("PropertyFieldOwned", testGame.getPlayerFieldType(testGame.getActivePlayer()));

        // vi sætter aktiv spiller på feltId 5
        testGame.getActivePlayer().setField(5);

        // vi tjekker at getPlayerFieldType returnerer "CompanyField"
        assertEquals("CompanyField", testGame.getPlayerFieldType(testGame.getActivePlayer()));

        // vi sætter aktiv spiller på feltId 38
        testGame.getActivePlayer().setField(38);

        // vi tjekker at getPlayerFieldType returnerer "TaxField"
        assertEquals("TaxField", testGame.getPlayerFieldType(testGame.getActivePlayer()));
    }
}