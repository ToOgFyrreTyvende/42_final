package Model;

import Model.ChanceCards.MoveToCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChanceCardTest {

    Game testGame;
    Player player1;

    MoveToCard moveToCard;

    @BeforeEach
    void setUp(){
        testGame = new Game(new GameBoard(), new String[]{"player1"});
        this.player1 = testGame.getPlayers()[0];

        moveToCard = new MoveToCard("gå 1 frem", "gå 1 frem", 1);
    }

    @Test
    void moveToCardTest(){
        moveToCard.cardAction(player1, testGame);
        assertEquals(1, player1.getField());
    }

    @Test
    void moveToCardBackwardsTest(){
        moveToCard = new MoveToCard("gå 1 tilbage", "gå 1 tilbage", -1);
        moveToCard.cardAction(player1, testGame);
        // Spilleren har her gået tilbage til felt nr. 40 elelr indec 39!
        assertEquals(39, player1.getField());
    }
}
