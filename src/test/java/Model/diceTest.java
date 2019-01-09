package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class diceTest {
    private Dice testDice1;

    @BeforeEach
    void setUp() {
        testDice1 = new Dice();
    }

    @Test
    void getResultatTest() {
        int testRes = testDice1.getResult();
        // Tester at terningen kun giver værdier fra 1 til 6
        assertTrue(testRes > 0 && testRes < 7);
    }
}
