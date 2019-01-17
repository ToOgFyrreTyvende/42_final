package Model;

import Model.Fields.PropertyField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    Player player1;
    Player player2;

    PropertyField field;

    //Måske også test andre fields en propertyfield?
    @BeforeEach
    void setUp(){
        //Vi opretter 2 spillere

        player1 = new Player("Markus");
        player2 = new Player("Sebastian");

        player1.setMoney(10);
        player2.setMoney(10);

        // Prisen på feltet vi opretter er 2, altså er price[0] = 2
        field = new PropertyField("Skaterparken", "", "", new int[]{2, 2, 3, 4, 5, 6, 7, 8}, Color.green);

    }


    @Test
    void PropertyFieldTest(){
        // spiller 1 køber feltet for 2 "penge"
        field.buyField(player1);

        // spiller 2 udfører felthandling på samme felt
        field.fieldAction(player2);

        // Vi forventer spiller 1 har 2 mindre penge
        assertEquals(8, player2.getMoney());
        // og at spiller 1 har den samme mængde penge som i starten, siden han har brugt 2 og fået 2
        assertEquals(10, player1.getMoney());
    }

    @Test
    void PropertyFieldBankruptTest(){
        // spiller 1 køber feltet for 2 "penge"
        field.buyField(player1);

        // vi sætter spiller 1 som fallit
        player1.setBankrupt(true);

        // spiller 2 udfører felthandling på samme felt
        field.fieldAction(player2);

        // Vi forventer spiller 1 har samme mængde penge
        assertEquals(10, player2.getMoney());
        // og at spiller 1 har 8 efter at have købt feltet, samt at spiller 1 er fallit
        assertEquals(8, player1.getMoney());
        assertEquals(true, player1.isBankrupt());
    }
}
