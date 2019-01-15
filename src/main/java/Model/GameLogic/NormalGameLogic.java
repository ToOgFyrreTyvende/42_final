package Model.GameLogic;

import Model.ChanceCards.ChanceCard;
import Model.Fields.Field;
import Model.Game;
import Model.GameBoard;
import Model.Global;
import Model.Player;

public class NormalGameLogic{

    private Game game;
    private Player[] players;
    private int nowIndex;
    private int newIndex;
    private int bankruptcies = 0;


    public NormalGameLogic(Game game) {
        this.game = game;
        this.players = game.getPlayers();
    }

    public void setupNextPlayer(){
        if (!game.isEnded()){
            this.nowIndex = java.util.Arrays.asList(players).indexOf(game.getActivePlayer());
            this.newIndex = (nowIndex + 1) % players.length;
            /*boolean newPlayerFound = false;
            while(!newPlayerFound){
                if(players[newIndex].isBankrupt()){
                    newIndex = (newIndex+1) % players.length;
                }else{
                    newPlayerFound = true;
                }
            }*/
        }
    }

    public void throwDice(){
        if (!game.isEnded() && !game.getActivePlayer().isBankrupt()){
            int diceThrow = game.setAndGetDiceResult();
            int fieldId = (game.getActivePlayer().getField() + diceThrow) % Global.FIELD_COUNT;
            game.getActivePlayer().setPreviousField(game.getActivePlayer().getField());

            fieldId = gameRules(fieldId);

            UpdateActivePlayerWithThrow(fieldId, diceThrow);


        }
        else{
            endPlayerTurn();
        }
    }

    public int gameRules(int fieldId) {
        if (game.getActivePlayer().isInJail()){
            if (!game.getActivePlayer().isOutOfJailFree()){
                System.out.println("[INFO] " + game.getActivePlayer().getName() + " Har betalt " +
                        Global.JAIL_PRICE + " For at komme ud af fængslet");
                game.getActivePlayer().setLastAction("\n - Har betalt for fængsel.");
                game.getActivePlayer().addMoney(-Global.JAIL_PRICE);
            }else{
                game.getActivePlayer().setLastAction("\n - Har brugt sit løsladelses chancekort.");
                System.out.println("[INFO] " + game.getActivePlayer().getName() + " Kom ud af fængslet med deres 'frikort'");
            }

            game.getActivePlayer().setInJail(false);
            checkRound();
        }

        if (!game.isEnded()){
            if (fieldId < game.getActivePlayer().getField()){
                System.out.println("[INFO] " + game.getActivePlayer().getName() + " Har passeret start og har modtaget " + Global.ROUND_MONEY + " kr.");
                game.getActivePlayer().setLastAction(game.getActivePlayer().getLastAction() + "\n - Har passeret start og har modtaget " + Global.ROUND_MONEY + " kr.");
                addStartMoney(game.getActivePlayer());
            }
            Field landedField = game.getGameBoard().getFieldModel(fieldId);
            landedField.fieldAction(game.getActivePlayer());

            if (game.getActivePlayer().isChanceField()){
                chanceFieldAction(game.getActivePlayer());
                return game.getActivePlayer().getField();
            }

        }

        return fieldId;
    }

    public void chanceFieldAction(Player activePlayer) {
        activePlayer.setChanceField(false);

        ChanceCard card = game.getGameBoard().randomChanceCard();
        activePlayer.setChanceCard(card);

        activePlayer.getChanceCard().cardAction(activePlayer, game);

    }

    public void UpdateActivePlayerWithThrow(int fieldId, int diceThrow) {
        if (game.getActivePlayer().isInJail()){
            game.getActivePlayer().setField(game.getGameBoard().getJail());
            game.getActivePlayer().setLastDiceResult(diceThrow);
            game.getActivePlayer().setLastDicePair(game.getDicePair());
        }else{
            game.getActivePlayer().setField(fieldId);
            game.getActivePlayer().setLastDiceResult(diceThrow);
            game.getActivePlayer().setLastDicePair(game.getDicePair());
        }
    }

    public void addStartMoney(Player player) {
        player.addMoney(Global.ROUND_MONEY);
    }

    public void endPlayerTurn(){
        if (!game.isEnded()){
            System.out.println("Sat new player to index " + this.newIndex);
            game.setActivePlayer(players[this.newIndex]);
            checkRound();
        }
    }

    public void checkRound(){

        for (Player player : players) {
            if (!player.isBankrupt()) {
                if (player.getMoney() <= 0) {

                    player.setBankrupt(true);
                    this.bankruptcies++;

                    if (bankruptcies == players.length - 1) {
                        game.setEnded(true);
                        game.setWinner(findWinner());
                    }
                }
            }
        }
        System.out.println("falitter " + bankruptcies);
    }

    public Player findWinner() {
        Player highest = null;
        if (game.isEnded()) {
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


}
