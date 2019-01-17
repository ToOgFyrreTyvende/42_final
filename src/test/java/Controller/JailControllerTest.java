package Controller;

import Model.ChanceCards.ChanceCard;
import Model.ChanceCards.OutOfJailCard;
import Model.GameBoard;
import Model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JailControllerTest {

    //private GameBoard gb = new GameBoard();
    //private JailController jc = new JailController();

    //@Test
    void payBail(){
        Player dummy = new Player("dummy");
        dummy.addMoney(1000);
        //jc.handleActions(jc.JailActions[0]);
        int Currrentmoney = dummy.getMoney();
        assertEquals(1, Currrentmoney);
    }

    //@Test
    void feelingLucky(){
        Player dummy2 = new Player("twommy");
        int outOfJail = 0;
        int inJail = 0;
        for (int z = 0; z < 50000; z++){
            dummy2.setInJail(true);
            //jc.handleActions(jc.JailActions[1]);

            if (!dummy2.isInJail()){
                outOfJail += 1;
            } else if (dummy2.isInJail()){
                inJail += 1;
            }
        }
        assertTrue(outOfJail >= 6000 && outOfJail <= 10000 && inJail >= 40000 && inJail <= 44000);
    }

    @Test
    void bailCard(){
        /*gb.setChanceCards();
        Player dummy3 = new Player("threemmy");
        dummy3.setInJail(true);
        jc.handleActions(jc.JailActions[2],dummy3);
        assertTrue(dummy3.isInJail());
        dummy3.isOutOfJailFree(); */
    }
}