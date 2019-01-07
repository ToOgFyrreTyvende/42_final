package Model;

import Model.Runde;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class rundeTest {
    Runde runde = new Runde();
    @Test
    void rundeTest(){
        int[] p1tur = new int[]{1,1,2,1};
        int[] p2tur = new int[]{1,2,3,2};

        ArrayList<int[]> kontrolRunde = new ArrayList<>();
            kontrolRunde.add(p1tur);
            kontrolRunde.add(p2tur);

        runde.tilfoejTur(p1tur);
        runde.tilfoejTur(p2tur);

        assertEquals(kontrolRunde, runde.getTure());

    }
}
