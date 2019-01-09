package stub;

import Model.GameBoard;
import Model.Player;
import View.GameView;

public class ViewStub extends GameView {

    private Player[] spillere;
    private final int SPILLERE_ANTAL = 2;

    public ViewStub() {
    }

    @Override
    public void setPlayers(Player[] players) {
        this.spillere = players;
    }

    @Override
    public int getPlayerCount() {
        return SPILLERE_ANTAL;
    }

    @Override
    public String getPlayerName(String text) {
        int sum = 0;
        float _random1 = (float) Math.random();
        int _random2 = (int) (_random1 * this.getPlayerCount());
        int resultat = _random2 + 1;

        return "Player " + resultat;
    }

    @Override
    public String getRundeValg(String... choice) {
        return "Rul terning";
    }

    @Override
    public String getRundeValgMedTekst(String tekst, String... choice) {
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
    public void setDice(int result) {
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
}
