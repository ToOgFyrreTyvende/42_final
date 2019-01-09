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
    private int sides;


    // #----------Constructor----------#
    protected Dice() {
        this.sides = 6;
    }

    // #--------------Get--------------#
    public int getResult(){ // Returner en værdi af terningen.
        int sum = 0;
        float _random1 = (float) Math.random();         // 0-1 float
        int _random2 = (int) (_random1 * sides);   // 0-5 integer
        result = _random2 + 1;                        // 1-6 integer

        return result;
    }
}
