package Model;

import Model.Fields.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    Player player1;
    Player player2;

    PropertyField propField;
    CompanyField shipField, brewField;

    //Måske også test andre fields end propertyfield?
    @BeforeEach
    void setUp(){
        //Vi opretter 2 spillere
        player1 = new Player("Markus");
        player2 = new Player("Sebastian");

        player1.setMoney(420);
        player2.setMoney(420);

        // Prisen på feltet vi opretter er 1, altså er price[0] = 1
        propField = new PropertyField("Skaterparken", "", "", new int[]{1, 2, 3, 4, 5, 6, 7, 8}, Color.green);
        shipField = new CompanyField("Oslo færgen", "", "", 20, Color.red, true);
        brewField = new CompanyField("Coca-Cola", "", "", 40, Color.yellow, false);
    }


    @Test
    void PropertyFieldTest(){
        // spiller 1 køber feltet for 1 "penge"
        propField.buyField(player1);

        // spiller 2 udfører felthandling på samme felt
        propField.fieldAction(player2);

        // Vi forventer spiller 2 har 2 mindre penge
        assertEquals(418, player2.getMoney());
        // og at spiller 1 har flere penge end i starten, siden han har brugt 1 og fået 2
        assertEquals(421, player1.getMoney());
    }

    @Test
    void PropertyFieldBankruptTest(){
        // spiller 1 køber feltet for 1 "penge"
        propField.buyField(player1);

        // vi sætter spiller 1 som fallit
        player1.setBankrupt(true);

        // spiller 2 udfører felthandling på samme felt
        propField.fieldAction(player2);

        // Vi forventer spiller 2 har samme mængde penge
        assertEquals(420, player2.getMoney());
        // og at spiller 1 har 419 efter at have købt feltet, samt at spiller 1 er fallit
        assertEquals(419, player1.getMoney());
        assertTrue(player1.isBankrupt());
    }

    @Test
    void CompanyFieldTest(){
        // spiller 1 køber Oslo færgen og tjekker spilleren har 20,- mindre
        shipField.buyField(player1);
        assertEquals(400, player1.getMoney());

        // spiller 2 udfører felthandling på samme felt
        shipField.fieldAction(player2);

        // Vi tjekker at spiller 2 har mistet 10,-
        // og         at spiller 1 har modtaget 10,-
        assertEquals(410, player2.getMoney());
        assertEquals(410, player1.getMoney());


        // spiller 2 køber Coca-Cola bryggeriet og tjekker at spilleren har 40,- mindre
        brewField.buyField(player2);
        assertEquals(370, player2.getMoney());

        // spiller 1 udfører felthandling på samme felt
        brewField.fieldAction(player1);

        // Vi tjekker at spiller 1 har mistet 20,-
        // og         at spiller 2 har modtaget 20,-
        assertEquals(390, player1.getMoney());
        assertEquals(390, player2.getMoney());
    }

    @Test
    void CompanyFieldBankruptTest(){
        // spiller 1 køber Oslo færgen for 20,-
        shipField.buyField(player1);

        // vi sætter spiller 1 som fallit
        player1.setBankrupt(true);

        // spiller 2 udfører felthandling på samme felt
        shipField.fieldAction(player2);

        // Vi tjekker at spiller 2 har samme mængde penge
        assertEquals(420, player2.getMoney());
        // Vi tjekker at spiller 1 har 400,- efter at have købt feltet, samt at spiller 1 er gået fallit
        assertEquals(400, player1.getMoney());
        assertTrue(player1.isBankrupt());
    }
}
