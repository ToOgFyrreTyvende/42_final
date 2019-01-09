package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class playerTest {

    private Player player = new Player("Test");

    @Test
    void playerInitTest(){
        String testTekst = "kontrolNavn";
        player = new Player(testTekst);
        assertEquals(testTekst, player.getName());
    }

    @Test
    void playerFeltTest(){
        int testFelt = 3;
        player.setFelt(testFelt);
        assertEquals(testFelt, player.getField());
    }

    @Test
    void playerKontoTest(){
        int testEkstraPenge = 100;
        player.addMoney(testEkstraPenge);
        // Vi tilføjer 1 til ekstra pengene, siden en player starter med kun at have 1 M
        // - før spillet tjekker hvor mange spillere der er og sætter deres pengeværdi udfra dette
        assertEquals(testEkstraPenge + 1, player.getMoney());
    }

    @Test
    void LastResultTest() {
        int testVal = 1;
        player.setLastDiceResult(testVal);
        // Sætter spilleren's "sidstSlaaet" til 1 og tjekker at både get og set metoden virker
        assertEquals(testVal, player.getLastDiceResult());
    }
}
