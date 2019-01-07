package Model;

import Model.Spiller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class spillerTest {

    Spiller spiller = new Spiller("Test");

    @Test
    void spillerInitTest(){
        String testTekst = "kontrolNavn";
        spiller = new Spiller(testTekst);
        assertEquals(testTekst, spiller.getNavn());
    }

    @Test
    void spillerFeltTest(){
        int testFelt = 3;
        spiller.setFelt(testFelt);
        assertEquals(testFelt, spiller.getFelt());
    }

    @Test
    void spillerKontoTest(){
        int testEkstraPenge = 100;
        spiller.addPenge(testEkstraPenge);
        // Vi tilføjer 1 til ekstra pengene, siden en spiller starter med kun at have 1 M
        // - før spillet tjekker hvor mange spillere der er og sætter deres pengeværdi udfra dette
        assertEquals(testEkstraPenge + 1, spiller.getPenge());
    }

    @Test
    void setPengeTest() {
        int testPenge = 42;
        spiller.setPenge(testPenge);
        // Vi sætter spillerens pengeantal til 42 og tjekker spillerens pengeantal er lig 42
        assertEquals(testPenge, spiller.getPenge());
    }

    @Test
    void SidstSlaaetTest() {
        int testVal = 1;
        spiller.setSidstSlaaet(testVal);
        // Sætter spilleren's "sidstSlaaet" til 1 og tjekker at både get og set metoden virker
        assertEquals(testVal, spiller.getSidstSlaaet());
    }
}
