package Model;

import Model.ChanceCards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChanceCardTest {

    Game testGame;
    Player player1, player2, player3;

    MoveToCard moveToCard;
    GetPaidCard getPaidByBankCard, getPaidByPlayersCard;

    @BeforeEach
    void setUp(){
        testGame = new Game(new GameBoard(), new String[]{"player1", "player2", "player3"});

        this.player1 = testGame.getPlayers()[0];
        this.player2 = testGame.getPlayers()[1];
        this.player3 = testGame.getPlayers()[2];

        moveToCard = new MoveToCard("gå 1 frem", "gå 1 frem", 1);
        getPaidByBankCard = new GetPaidCard("Modtag kr. 1000", "Aktier", 1000, false);
        getPaidByPlayersCard = new GetPaidCard("Stjæl kr. 500", "Fra spillere", 500, true);
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

    @Test
    void getPaidCardTest1(){
        int p1Money = player1.getMoney();

        // Vi udfører korthandlingen for getPaidByBankCard for spiller1
        getPaidByBankCard.cardAction(player1, testGame);

        // vi tjekker at spiller1 har modtaget 1000,-
        assertEquals((p1Money + 1000), player1.getMoney());
    }

    @Test
    void getPaidCardTest2(){
        // vi gemmer spillernes penge startværdier
        int p1Money = player1.getMoney();
        int p2Money = player2.getMoney();
        int p3Money = player3.getMoney();

        // vi udfører korthandlingen for getPaidByPlayersCard for spiller1
        getPaidByPlayersCard.cardAction(player1, testGame);

        // vi tjekker at spiller 1 har modtaget 500,- per spiller
        // og         at spiller 2 har mistet 500,-
        // og         at spiller 3 har mistet 500,-
        assertEquals(p1Money + 1000, player1.getMoney());
        assertEquals(p2Money - 500, player2.getMoney());
        assertEquals(p3Money - 500, player3.getMoney());
    }
}
