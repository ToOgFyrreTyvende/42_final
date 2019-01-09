package stub;

import Model.Dice;

/**
 * terningStub
 */
public class DiceStub extends Dice {
    private int testRes;

    public DiceStub(int testRes) {
        this.testRes = testRes;
    }

    @Override
    public int setAndGetResult() {
        return testRes;
    }
}