package Model;

import Model.Felter.EjendomFelt;
import Model.Spiller;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FeltTest {


    @Test
    void Ejendomsfelt() {
        //Vi opretter 2 spillere
        Spiller spiller1 = new Spiller("Markus");
        Spiller spiller2 = new Spiller("Sebastian");
        spiller1.setPenge(10);
        spiller2.setPenge(10);

        EjendomFelt felt = new EjendomFelt("Skaterparken", "","",2, Color.green);

        felt.feltHandling(spiller1);
        felt.feltHandling(spiller2);

        assertEquals(8,spiller2.getPenge());
        assertEquals(10,spiller1.getPenge());
    }
}
