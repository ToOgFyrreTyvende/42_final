package stub;

import Model.GameBoard;
import Model.Player;
import View.GameView;

public class ViewStub extends GameView {

    private Player[] players;
    private final int PLAYER_AMOUNT = 4;

    public ViewStub() {
    }

    @Override
    public void renderBuildings() {

    }

    @Override
    public String getRoundChoiceDropDownWithText(String text, String... choice) {
        return "testString";
    }

    @Override
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    @Override
    public int getPlayerCount() {
        return PLAYER_AMOUNT;
    }

    @Override
    public String getPlayerName(String text) {
        int sum = 0;
        float _random1 = (float) Math.random();
        int _random2 = (int) (_random1 * this.getPlayerCount());
        int result = _random2 + 1;

        return "Player " + result;
    }

    @Override
    public String getRoundChoice(String... choice) {
        return "Rul terning";
    }

    @Override
    public String getRoundChoiceWithText(String text, String... choice) {
        return "Rul terning";
    }

    @Override
    public void resetBoard() {
    }

    @Override
    public void setPlayerField(Player player, int field) {
    }

    @Override
    public void setPlayerField(Player player, int field, int previousField) {
    }

    @Override
    public void setPlayerMoney(Player player, int money) {
    }

    @Override
    public void renderPlayerData(Player player, int previousField) {
    }

    @Override
    public void endText(String text) {
    }

    @Override
    public void setDice(int[] result) {
    }

    @Override
    public void setCenterText(String text) {
    }

    @Override
    public GameBoard getGameBoard() {
        return super.getGameBoard();
    }

    @Override
    public void setGameBoard(GameBoard gameBoard) {
        super.setGameBoard(gameBoard);
    }

    @Override
    public void createViewBoard() {
    }
}
