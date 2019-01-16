package stub;

import Model.Dice;

/**
 * terningStub
 */
public class DiceStub extends Dice {
    private int result;
    private int[] pair;
    private int[][] demoResults;
    private int demoIndex = 0;
    // Returns constant
    private int constant = 3;


    // #----------Constructor----------#

    /**
     * Set fixed to 0 for fixed results from the demoResults array
     * @param fixedDiceResult
     */
    public DiceStub(int fixedDiceResult, boolean useDemoResults) {
        if(useDemoResults){
            this.demoResults = new int[][]{
                    // Her sættes nogle terninge resultater i følgende ordre

                    // Spiller 1s 2 terninger
                    // Spiller 2s 2 terninger
                    // Spiller 3s 2 terninger
                    // forfra...
                    new int[]{6,6},
                    new int[]{1,1},
                    new int[]{1,1},

                    new int[]{6,6},
                    new int[]{5,5},
                    new int[]{5,5},

                    new int[]{3,3},
                    new int[]{5,5},
                    new int[]{5,5},

            };
            this.constant = fixedDiceResult;
        }else{
            this.demoResults = new int[][]{};
            this.constant = fixedDiceResult;
        }
    }


    @Override
    public int setAndGetResult() {
        if (demoIndex < demoResults.length) {
            this.result = demoResults[demoIndex][0] + demoResults[demoIndex][1];
            this.setPair(demoResults[demoIndex]);

            demoIndex++;

            return this.result;

        } else {
            return constant;

        }
    }

    @Override
    public int getResult() {
        return result;
    }

    @Override
    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public int[] getPair() {
        return pair;
    }

    @Override
    public void setPair(int[] pair) {
        this.pair = pair;
    }
}