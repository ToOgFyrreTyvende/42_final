package Model;

/**
 * This class can throw 'x' amount of dice with 'y' sides
 *
 * Random numbers are generated to simulate the amount/sides.
 */

public class Dice {
    private int result;
    private int[] pair;
    private int sides;
    private int amount;


    /**
     * This constructor is mainly used, since we load values from the static global
     * constants class.
     */
    protected Dice() {
        this.sides = Global.DICE_SIDES;
        this.amount = Global.DICE_AMOUNT;
    }

    /**
     * it is also a possibility to make a Dice class with given amount/sides
     * @param sides
     * @param amount
     */
    protected Dice(int sides, int amount) {
        this.sides = sides;
        this.amount = amount;
    }

    /**
     * When the dice are thrown, the values are updated in the attributes
     * as well as returned from the method
     * @return int sum of the dice result
     */
    public int setAndGetResult(){

        int[] diceThrow = new int[amount + 1];
        int sum = 0;
        for (int i = 0 ; i < amount ; i++){
            float _random1 = (float) Math.random();    // 0-1 float,
            int _random2 = (int) (_random1 * sides);   // 0-5 integer
            int random = _random2 + 1;                 // 1-6 integer in case of 6 sides. Final random number
            diceThrow[i] = random; // set index i of the dice throw to final random number
            sum += random;
        }

        this.result = sum;
        setPair(diceThrow);
        return sum;
    }

    public int[] getPair() {
        return pair;
    }

    public void setPair(int[] pair) {
        this.pair = pair;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
