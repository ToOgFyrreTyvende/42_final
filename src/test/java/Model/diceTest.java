package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class diceTest {
    private Dice testDice1;

    @BeforeEach
    void setUp() {
        testDice1 = new Dice();
    }

    @Test
    void getResultTest() {
        Random rng = new Random();
        int[] dist = new int[11];
        int[] Throw = new int[11];

        // Vi slår 50000 gange
        for (int i = 1; i <= 50000; i++) {
            int tempThrow = testDice1.setAndGetResult();
            Throw[tempThrow - 2]++;

            // Samtidig med de to terningekast, opretter
            // bi et normalfordelt tilfældigt tal i tempGaus arrayer med samme størrelse
            double gaus = (rng.nextGaussian() * 3) + 7;
            if (gaus < 12 && gaus > 1) {
                int tempGaus = (int) gaus;
                dist[tempGaus - 1]++;
            }
        }

        // Ud fra vores normalfordeling tjekkes hvert resultat
        for (int i = 0; i < Throw.length; i++) {
            int goal = dist[i];
            double pr = (double) Throw[i]/50000*100;
            int rounded = (int) pr;
            System.out.println((i+1) + " : "+ rounded + "%");
            assertTrue(Throw[i] >= goal-2000 && Throw[i] <= goal+2000);
        }
    }
}
