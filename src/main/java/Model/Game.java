package Model;

import Controller.PropertyController;
import Model.Fields.CompanyField;
import Model.Fields.Field;
import Model.ChanceCards.ChanceCard;
import Model.Fields.PropertyField;
import Model.Fields.TaxField;
import Model.GameLogic.*;

/**
 * ------------------------------------------------------------/
 * Denne klasse er hoveddelen der kører hele spillet og diverse
 * klasser efter en bruger har åbnet programmet
 * ------------------------------------------------------------/
 */

public class Game {
    private final int ROUND_MONEY;
    private final int JAIL_PRICE;

    private int startMoney;

    private Player[] players;
    private Player winner;
    private Player activePlayer;

    private Dice dice;
    private boolean ended;

    private GameBoard gameBoard;
    private NormalGameLogic gameLogic;

    // #----------Constructor----------#

    public Game(GameBoard gameboard, String[] playerNames){
        this.startMoney = Global.START_MONEY;
        this.JAIL_PRICE = Global.JAIL_PRICE;
        this.ROUND_MONEY = Global.ROUND_MONEY;

        createPlayers(playerNames);

        dice = new Dice();

        activePlayer = players[0];

        this.gameBoard = gameboard;
        ended = false;

        gameLogic = new NormalGameLogic(this);
    }

    private void createPlayers(String[] playerNames){
        Player[] players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]);
            players[i].setMoney(startMoney);
        }

        this.players = players;
    }

    public void buyFieldPlayerIsOn(Player player){
        Field fieldPlayerIsOn = gameBoard.getFieldModel(player.getField());
        if (fieldPlayerIsOn instanceof PropertyField){
            ((PropertyField) fieldPlayerIsOn).buyField(player);

        }

    }

    public void buyHouseOnPropertyField(PropertyField field){
        field.buyHouse();
    }

    public void buyHotelOnPropertyField(PropertyField field){
        field.buyHouse();
    }

    /*for (PropertyField playerOwnedField: gameBoard.getPlayerProperties(player)) {
        if (playerOwnedField.getColor() == ((PropertyField) fieldPlayerIsOn).getColor() &&
            playerOwnedField != fieldPlayerIsOn){
            // Vi ændrer prisen det koster at lande på feltet hvis det er af samme type
            playerOwnedField.increaseRentIndex();
        }
    }*/

    public String getPlayerFieldType(Player player){
        Field playerField = gameBoard.getFieldModel(player.getField());

        if (playerField instanceof PropertyField){
            if (((PropertyField) playerField).getOwner() == null){
                return "PropertyField";
            }else{
                if (((PropertyField) playerField).getOwner() == player){
                    return "PropertyFieldMe";
                }
                return "PropertyFieldOwned";
            }
        }

        if (playerField instanceof CompanyField){
            if (((CompanyField) playerField).getOwner() == null){
                return "CompanyField";
            }else{
                if (((CompanyField) playerField).getOwner() == player){
                    return "CompanyFieldMe";
                }
                return "CompanyFieldOwned";
            }
        }

        if (playerField instanceof TaxField){
            if (((TaxField) playerField).isChoice()){
                return "TaxFieldChoice";
            }else{
                return "TaxField";
            }
        }

        return playerField.getClass().getSimpleName();
    }

    public int setAndGetDiceResult() {
        return dice.setAndGetResult();
    }

    public int getDiceResult() {
        return dice.getResult();
    }

    public void setDice(int[] pair, int result) {
        dice.setPair(pair);
        dice.setResult(result);
    }

    public int[] getDicePair() {
        return dice.getPair();
    }

    public Player[] getPlayers() {
        return players;
    }

    void setPlayers(Player[] players) {
        this.players = players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    void setDice(Dice dice) {
        this.dice = dice;
    }


    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }


    int getROUND_MONEY() {
        return ROUND_MONEY;
    }

    public boolean isEnded() {
        return ended;
    }

    // ----- Delegators -----


    public void setupNextPlayer() {
        gameLogic.setupNextPlayer();
    }

    public void throwDice(boolean alreadyThrown) {
        gameLogic.throwDice(alreadyThrown);
    }

    public int gameRules(int fieldId) {
        return gameLogic.gameRules(fieldId);
    }

    public void chanceFieldAction(Player activePlayer) {
        gameLogic.chanceFieldAction(activePlayer);
    }

    public void UpdateActivePlayerWithThrow(int fieldId, int diceThrow) {
        gameLogic.UpdateActivePlayerWithThrow(fieldId, diceThrow);
    }

    public void addStartMoney(Player player) {
        gameLogic.addStartMoney(player);
    }

    public void endPlayerTurn() {
        gameLogic.endPlayerTurn();
    }

    public void checkRound() {
        gameLogic.checkRound();
    }

    public Player findWinner() {
        return gameLogic.findWinner();
    }
}
