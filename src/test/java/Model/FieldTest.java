package Model;

import Model.Fields.PropertyField;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {


    @Test
    void PropertyFieldTest() {
        //Vi opretter 2 spillere
        Player player1 = new Player("Markus");
        Player player2 = new Player("Sebastian");
        player1.setMoney(10);
        player2.setMoney(10);

        PropertyField field = new PropertyField("Skaterparken", "","",new int[]{1,2,3,4,5,6,7,8}, Color.green);

        field.fieldAction(player1);
        field.fieldAction(player2);

        assertEquals(8, player2.getMoney());
        assertEquals(10, player1.getMoney());
    }
}
