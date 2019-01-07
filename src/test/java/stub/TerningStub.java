package stub;

import Model.Terning;

/**
 * terningStub
 */
public class TerningStub extends Terning {
    int testRes;

    public TerningStub(int testRes) {
        this.testRes = testRes;
    }

    @Override
    public int getResultat() {
        return testRes;
    }
}