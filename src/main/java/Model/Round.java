package Model;

import java.util.ArrayList;
/**
 * ------------------------------------------------------------/
 * Denne klasse bruges til evt. at kunne se enhver runde
 * fra spil-start til spil-slut og indeholder antallet af
 * turns for den givne runde og hvad der skete i den specifikke
 * tur
 * ------------------------------------------------------------/
 */

class Round {
    private ArrayList<int[]> turns;

    // #----------Constructor----------#
    Round(){
        turns = new ArrayList<>();
    }

    // #--------------Get--------------#
    ArrayList<int[]> getTurns() {
        return turns;
    }

    // #-------------Other-------------#
    void addTurn(int[] tur){
        this.turns.add(tur);
    }
}
