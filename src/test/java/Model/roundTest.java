package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class roundTest {
    private Round round = new Round();

    @Test
    void round_Test(){
        int[] p1tur = new int[]{1,1,2,1};
        int[] p2tur = new int[]{1,2,3,2};

        ArrayList<int[]> kontrolRunde = new ArrayList<>();
            kontrolRunde.add(p1tur);
            kontrolRunde.add(p2tur);

        round.addTurn(p1tur);
        round.addTurn(p2tur);

        assertEquals(kontrolRunde, round.getTurns());

    }
}
