package View;

import Model.GameBoard;
import Model.Player;

public abstract class GameView {

    private GameBoard gameBoard;

    public abstract void setPlayers(Player[] players);
    public abstract int getPlayerCount();
    public abstract String getPlayerName(String text);
    public abstract String getRundeValg(String ... choice);
    public abstract String getRundeValgMedTekst(String tekst, String ... choice);
    public abstract void resetBoard();
    public abstract void setPlayerField(Player player, int field);
    public abstract void setPlayerField(Player player, int field, int previousField);

    public abstract void setPlayerMoney(Player player, int money);
    public abstract void renderPlayerData(Player player, int previousField);
    public abstract void endText(String text);

    public abstract void setDice(int result);

    public abstract void setCenterText(String text);

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}
