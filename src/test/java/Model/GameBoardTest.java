package Model;

import Model.Fields.Field;
import Model.Fields.PropertyField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    //Variables
    private GameBoard gameBoardTest = new GameBoard();
    private Player dummyPlayer = new Player("dummyPlayer");

    @Test
    void getPlayerProperties() {
        PropertyField field1 = (PropertyField) gameBoardTest.getFieldModel(1);
        PropertyField field2 = (PropertyField) gameBoardTest.getFieldModel(3);

        field1.fieldAction(dummyPlayer,0);
        field2.fieldAction(dummyPlayer,0);

        Field[] ownedProps = gameBoardTest.getPlayerProperties(dummyPlayer);

        assertSame(field1,ownedProps[0]);
        assertSame(field2,ownedProps[1]);

    }
}