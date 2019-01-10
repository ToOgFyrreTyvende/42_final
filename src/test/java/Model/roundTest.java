package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class roundTest {
    private Round round = new Round();

    @Test
    void round_Test(){
        int[] p1Turn = new int[]{1,1,2,1};
        int[] p2Turn = new int[]{1,2,3,2};

        ArrayList<int[]> controlRound = new ArrayList<>();
            controlRound.add(p1Turn);
            controlRound.add(p2Turn);

        round.addTurn(p1Turn);
        round.addTurn(p2Turn);

        assertEquals(controlRound, round.getTurns());

    }
}
