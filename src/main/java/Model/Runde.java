package Model;

import java.util.ArrayList;
/**
 * ------------------------------------------------------------/
 * Denne klasse bruges til evt. at kunne se enhver runde
 * fra spil-start til spil-slut og indeholder antallet af
 * ture for den givne runde og hvad der skete i den specifikke
 * tur
 * ------------------------------------------------------------/
 */

public class Runde {
    private ArrayList<int[]> ture;

    // #----------Constructor----------#
    public Runde(){
        ture = new ArrayList<>();
    }

    // #--------------Get--------------#
    public ArrayList<int[]> getTure() {
        return ture;
    }

    // #-------------Other-------------#
    public void tilfoejTur(int[] tur ){
        this.ture.add(tur);
    }
}
