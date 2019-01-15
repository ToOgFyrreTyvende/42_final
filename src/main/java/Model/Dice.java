package Model;

/**
 * ------------------------------------------------------------/
 * Denne klasse bruges til at kaste 'x' antal terninger
 * med 'x' antal sider på hver
 *
 * Klassen kaldes uden nogle parametre, eller med to parametre
 * fx. Model.Dice() eller Model.Dice(sides, antal)
 * hvor 'sides' er antallet af sider på terningen
 * og 'antal' er antallet af terninger der kastes.
 * ------------------------------------------------------------/
 */

public class Dice {
    private int result;
    private int[] pair;
    private int sides;
    private int amount;


    // #----------Constructor----------#
    protected Dice() {
        this.sides = Global.DICE_SIDES;
        this.amount = Global.DICE_AMOUNT;
    }

    public int setAndGetResult(){ // Returner en værdi af terningen.

        int[] diceThrow = new int[amount + 1];
        int sum = 0;
        for (int i = 0 ; i < amount ; i++){
            float _random1 = (float) Math.random();    // 0-1 float
            int _random2 = (int) (_random1 * sides);   // 0-5 integer
            int random = _random2 + 1;                 // 1-6 integer
            diceThrow[i] = random;
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
