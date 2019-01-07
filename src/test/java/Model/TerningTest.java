package Model;

import Model.Terning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TerningTest {
    Terning testTerning1;

    @BeforeEach
    void setUp() {
        testTerning1 = new Terning();
    }

    @Test
    void getResultatTest() {
        int testRes = testTerning1.getResultat();
        // Tester at terningen kun giver værdier fra 1 til 6
        assertTrue(testRes > 0 && testRes < 7);
    }
}
