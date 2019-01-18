package Model;

import Controller.PropertyController;
import Model.Fields.CompanyField;
import Model.Fields.Field;
import Model.ChanceCards.ChanceCard;
import Model.Fields.PropertyField;
import Model.Fields.TaxField;
import Model.GameLogic.*;

/**
 * The main class for controlling and interacting with the Monopoly game
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


    /**
     * Set up a new Game. This should by itself create a game good enough for play.
     * Though it is important to mention, that the order of execution for different actions
     * are not trivial without a controller.
     * @param gameboard that contains all fields and chancecards (as well as actions to query them)
     * @param playerNames which is an array of strings equivalent to player names
     */
    public Game(GameBoard gameboard, String[] playerNames){
        this.startMoney = Global.START_MONEY;
        this.JAIL_PRICE = Global.JAIL_PRICE;
        this.ROUND_MONEY = Global.ROUND_MONEY;

        createPlayers(playerNames);

        dice = new DemoDice();

        activePlayer = players[0];

        this.gameBoard = gameboard;
        ended = false;

        gameLogic = new NormalGameLogic(this);
    }

    /**
     * Initialize players from an array of strings.
     * @param playerNames string[] of player names to be created in the game
     */
    private void createPlayers(String[] playerNames){
        Player[] players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]);
            players[i].setMoney(startMoney);
        }

        this.players = players;
    }

    /**
     * Method to buy the field a player is positioned at.
     * Only if the current field is of type PropertyField.
     * @param player
     */
    public void buyFieldPlayerIsOn(Player player){
        Field fieldPlayerIsOn = gameBoard.getFieldModel(player.getField());
        if (fieldPlayerIsOn instanceof PropertyField){
            ((PropertyField) fieldPlayerIsOn).buyField(player);
        }
    }

    /**
     * Return the class name, as a string, of the current field a player is positioned at
     * These strings are used for a switch/case expression in GameController.
     * The main reason for this method, is to make the GameController more lean
     * and to customize the state of the field, such as adding "Me" or "Owned" at the end.
     * @param player
     * @return String that represents the type of the field. e.g. "CompanyField"
     */
    public String getPlayerFieldType(Player player){
        Field playerField = gameBoard.getFieldModel(player.getField());

        if (playerField instanceof PropertyField){
            if (((PropertyField) playerField).getOwner() == null){
                return "PropertyField";
            }else{
                // If player is on PropertyField and it is owned

                // If owner is player that is examined
                if (((PropertyField) playerField).getOwner() == player){
                    return "PropertyFieldMe";
                }
                return "PropertyFieldOwned";
            }
        }
        // Same as above, but for CompanyField
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

    /**
     * Throw the dice and return the result (this still sets attributes in the Dice class).
     * @return int result (sum) of dice
     */
    public int setAndGetDiceResult() {
        return dice.setAndGetResult();
    }

    /**
     * Return dice result of latest dice throw
     * @return int result (sum) of dice
     */
    public int getDiceResult() {
        return dice.getResult();
    }

    /**
     * If its needed (which it is), the result of the dice can be set here.
     * @param pair int[] representing the dice results
     * @param result sum of the dice
     */
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


    public void setupNextPlayer() {
        gameLogic.setupNextPlayer();
    }

    public void throwDice(boolean alreadyThrown) {
        gameLogic.throwDice(alreadyThrown);
    }

    public int gameRules(int fieldId) {
        return gameLogic.gameRules(fieldId);
    }

    public void endPlayerTurn() {
        gameLogic.endPlayerTurn();
    }

    public NormalGameLogic getGameLogic() {
        return gameLogic;
    }
}
