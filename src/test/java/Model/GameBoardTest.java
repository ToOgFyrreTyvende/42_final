package Model;

import Model.Fields.Field;
import Model.Fields.PropertyField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    //Variables
    GameBoard gameBoardTest;
    Player dummyPlayer;

    @BeforeEach
    void setUp() {
        gameBoardTest = new GameBoard();
        dummyPlayer = new Player("dummyPlayer");

    }

    @Test
    void testFieldAmount(){
        assertEquals(40, gameBoardTest.getFields().length);
    }

    @Test
    void testChanceCardAmount(){
        assertEquals(41, gameBoardTest.getChanceCards().length);
    }

}