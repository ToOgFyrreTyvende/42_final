package Model.ChanceCards;

import Model.Game;
import Model.Player;

public class GetPaidCard extends ChanceCard {
    private int money;
    private boolean toOthers;

    GetPaidCard(String text, String name, int money, boolean toOthers) {
        super(text, name);
        this.money = money;
        this.toOthers = toOthers;
    }

    private void paidByOthers(Player activePlayer, int money, Player[] players) {
        int activePlayerCount = 0;
        // Vi trækker penge fra alle aktive players
        for (Player player : players) {
            if (!player.isBankrupt() && activePlayer != player) {
                player.addMoney(-money);
                activePlayerCount++;
            }
        }

        // Siden vi fjerner antallet af penge fra alle spillere, skal der tilføjes mængden af penge
        // ganget med alle players til stede for at spilleren får den rigtige mængde
        int moneyToGet = money * activePlayerCount;
        activePlayer.addMoney(moneyToGet);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isToOthers() {
        return toOthers;
    }

    public void setToOthers(boolean toOthers) {
        this.toOthers = toOthers;
    }

    @Override
    public void cardAction(Player player, Game game) {
        super.cardAction(player, game);

        if (this.isToOthers()){
            paidByOthers(player, this.getMoney(), game.getPlayers());
            player.setLastAction(player.getLastAction() + "\n - Har fået " + this.getMoney()
                    + " kr. fra hver spiller.");
        }
        else{
            player.setLastAction(player.getLastAction() + "\n - Har fået " + this.getMoney()
                    + " kr. fra banken.");
            player.addMoney(this.getMoney());
        }
    }
}
