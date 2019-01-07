package Model;

/**
 * ------------------------------------------------------------/
 * Denne klasse bruges til at kaste 'x' antal terninger
 * med 'x' antal sider på hver
 *
 * Klassen kaldes uden nogle parametre, eller med to parametre
 * fx. Model.Terning() eller Model.Terning(antalSider, antal)
 * hvor 'antalSider' er antallet af sider på terningen
 * og 'antal' er antallet af terninger der kastes.
 * ------------------------------------------------------------/
 */

public class Terning {
    private int resultat;
    private int antalSider;


    // #----------Constructor----------#
    public Terning() {
        this.antalSider = 6;
    }

    // #--------------Get--------------#
    public int getResultat(){ // Returner en værdi af terningen.
        int sum = 0;
        float _random1 = (float) Math.random();         // 0-1 float
        int _random2 = (int) (_random1 * antalSider);   // 0-5 integer
        resultat = _random2 + 1;                        // 1-6 integer

        return resultat;
    }
}
