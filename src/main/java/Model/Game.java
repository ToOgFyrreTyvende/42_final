package Model;

import Model.Fields.Field;
import Model.Fields.PropertyField;
import Model.ChanceCards.GetPaidCard;
import Model.ChanceCards.ChanceCard;
import Model.ChanceCards.FreePropertyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------------------------------/
 * Denne klasse er hoveddelen der kører hele spillet og diverse
 * klasser efter en bruger har åbnet programmet
 * ------------------------------------------------------------/
 */

public class Game {
    private final int ROUND_MONEY;
    private final int JAIL_PRICE;

    //private int[] muligeStartPenge = {20, 18, 16};
    private int startMoney;

    private Player[] players;
    private Player winner;
    private Player activePlayer;

    private Dice dice;
    private List<Round> round;
    private Round activeRound;
    private boolean ended;

    private GameBoard gameBoard;

    // #----------Constructor----------#

    public Game(GameBoard gameboard, String[] playerNames){
        this.startMoney = Global.START_PENGE;
        this.JAIL_PRICE = Global.JAIL_PRICE;
        this.ROUND_MONEY = Global.ROUND_MONEY;

        createPlayers(playerNames);

        //Kodedelen med round er taget fra vores forrige opgave: 42_del1
        round = new ArrayList<>();
        round.add(new Round());
        dice = new Dice();

        activePlayer = players[0];
        activeRound = round.get(round.size()-1);

        this.gameBoard = gameboard;

        ended = false;
    }

    private void createPlayers(String[] spillerNavne){
        Player[] players = new Player[spillerNavne.length];
        for (int i = 0; i < spillerNavne.length; i++) {
            players[i] = new Player(spillerNavne[i]);
            players[i].setMoney(startMoney);
        }

        this.players = players;
    }


    public Player playTurn(){
        if (!ended) {
            int nowIndex = java.util.Arrays.asList(players).indexOf(activePlayer);
            int newIndex = (nowIndex + 1) % players.length;
            int diceThrow = dice.setAndGetResult();
            int[] tempTurn = {diceThrow, nowIndex};

            Player _activePlayer = activePlayer;
            int fieldId = (activePlayer.getField() + diceThrow) % Global.FIELD_COUNT;

            fieldId = gameRules(fieldId);

            UpdateActivePlayerWithThrow(fieldId, diceThrow);

            activeRound.addTurn(tempTurn);
            activePlayer = players[newIndex];

            checkRound();

            return _activePlayer;
        }else{
            return null;
        }
    }

    int gameRules(int fieldId) {
        if (activePlayer.isInJail()){
            if (!activePlayer.isOutOfJailFree()){
                System.out.println("[INFO] " + activePlayer.getName() + " Har betalt " +
                        JAIL_PRICE + " For at komme ud af fængslet");
                activePlayer.setLastAction("\n - Har betalt for fængsel.");
                activePlayer.addMoney(-JAIL_PRICE);
            }else{
                activePlayer.setLastAction("\n - Har brugt sit løsladelses chancekort.");
                System.out.println("[INFO] " + activePlayer.getName() + " Kom ud af fængslet med deres 'frikort'");
            }

            activePlayer.setInJail(false);
            checkRound();
        }

        if (!ended){
            if (fieldId < activePlayer.getField()){
                System.out.println("[INFO] " + activePlayer.getName() + " Har passeret start og har modtaget " + Global.ROUND_MONEY + " kr.");
                activePlayer.setLastAction(activePlayer.getLastAction() + "\n - Har passeret start og har modtaget " + Global.ROUND_MONEY + " kr.");
                addStartMoney(activePlayer);
            }
            Field landetField = this.getGameBoard().getFeltModel(fieldId);
            landetField.fieldAction(activePlayer);
            //chancekort skal tilføjes...

            if (activePlayer.isChanceField()){
                chanceFieldAction(activePlayer);
                return activePlayer.getField();
            }

        }

        return fieldId;
    }

    private void chanceFieldAction(Player activePlayer) {
        activePlayer.setChanceField(false);

        ChanceCard card = this.getGameBoard().randomChanceCard();
        activePlayer.setChanceCard(card);

        activePlayer.getChanceCard().cardAction(activePlayer);

        if (card instanceof GetPaidCard){
            if (((GetPaidCard) card).isToOthers()){
                paidByOthers(((GetPaidCard) card).getMoney());
                activePlayer.setLastAction(activePlayer.getLastAction() + "\n - Har fået " + ((GetPaidCard) card).getMoney()
                        + " kr. fra hver af de andre players.");
            }else{
                activePlayer.setLastAction(activePlayer.getLastAction() + "\n - Har fået " + ((GetPaidCard) card).getMoney()
                        + " kr. fra banken.");
                activePlayer.addMoney(((GetPaidCard) card).getMoney());
            }
        }else if(card instanceof FreePropertyCard){
            int feltIndex = this.getGameBoard().closestColor(
                    activePlayer.getField(),
                    ((FreePropertyCard) card).getColor());
            Field tempField = this.getGameBoard().getFields()[feltIndex];

            if (tempField instanceof PropertyField){
                ((PropertyField) tempField).fieldAction(activePlayer, 0);
                activePlayer.setField(feltIndex);
            }

        }
    }

    private void paidByOthers(int money) {
        // Vi trækker penge fra alle players
        for (Player player : players) {
            player.addMoney(- money);
        }

        // SIden vi fjerner antallet af penge fra alle spilelre. skal det tilføjes mængden af penge
        // ganget med alle players til stede for at spilleren får den rigtige mængde
        int moneyToGet = money * players.length - 1;
        activePlayer.addMoney(moneyToGet);
    }

    private void addStartMoney(Player player) {
        player.addMoney(ROUND_MONEY);
    }

    private void UpdateActivePlayerWithThrow(int feltId, int slag) {
        if (activePlayer.isInJail()){
            activePlayer.setField(this.getGameBoard().getJail());
            activePlayer.setLastDiceResult(slag);
        }else{
            activePlayer.setField(feltId);
            activePlayer.setLastDiceResult(slag);
        }
    }

    //godt og grundigt Yoinked direkte fra vores 42_del1 af CDIO
    private void checkRound(){
        // Vi tjekker om den nuværende spiller er den sidste psiller i spiller listen. Dette gør, at
        // alle players har mulighed for at vinde i slutningen af en runde

        for (Player player : players) {
            if (player.getMoney() <= 0) {
                ended = true;
                this.setWinner(this.findWinner());
            }
        }
    }

    private Player findWinner() {

        Player highest = null;

        if (ended) {
            int max = 0;


            for (Player player : players) {
                if (player.getMoney() > max) {
                    max = player.getMoney();
                    highest = player;
                }
            }
        }
        return (highest);
    }

    // Getters & setters


    public Player[] getPlayers() {
        return players;
    }

    void setPlayers(Player[] players) {
        this.players = players;
    }

    public Player getWinner() {
        return winner;
    }

    private void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    void setDice(Dice dice) {
        this.dice = dice;
    }


    void setEnded(boolean ended) {
        this.ended = ended;
    }

    private GameBoard getGameBoard() {
        return gameBoard;
    }


    int getROUND_MONEY() {
        return ROUND_MONEY;
    }

    public boolean isEnded() {
        return ended;
    }
}
