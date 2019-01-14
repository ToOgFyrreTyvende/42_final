package Model.ChanceCards;

import Model.Player;

public class GetPaidCard extends ChanceCard {
    private int money;
    private boolean toOthers;

    GetPaidCard(String text, String name, int money, boolean toOthers) {
        super(text, name);
        this.money = money;
        this.toOthers = toOthers;
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
    public void cardAction(Player player) {
        super.cardAction(player);
    }
}
