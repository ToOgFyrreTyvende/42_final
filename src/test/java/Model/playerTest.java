package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class playerTest {

    private Player player = new Player("Test");

    @Test
    void playerInitTest(){
        String testText = "kontrolNavn";
        player = new Player(testText);
        assertEquals(testText, player.getName());
    }

    @Test
    void playerFieldTest(){
        int testField = 3;
        player.setFelt(testField);
        assertEquals(testField, player.getField());
    }

    @Test
    void playerAccountTest(){
        int testExtraMoney = 100;
        player.addMoney(testExtraMoney);
        // Vi tilføjer 1 til ekstra pengene, siden en player starter med kun at have 1 M
        // - før spillet tjekker hvor mange spillere der er og sætter deres pengeværdi udfra dette
        assertEquals(testExtraMoney + 1, player.getMoney());
    }

    @Test
    void LastResultTest() {
        int testVal = 1;
        player.setLastDiceResult(testVal);
        // Sætter spilleren's "sidstSlaaet" til 1 og tjekker at både get og set metoden virker
        assertEquals(testVal, player.getLastDiceResult());
    }
}
