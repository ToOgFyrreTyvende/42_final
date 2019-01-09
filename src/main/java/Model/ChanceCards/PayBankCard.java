package Model.ChanceCards;

import Model.Player;

public class PayBankCard extends ChanceCard {
    private int money;

    PayBankCard(String text, String name, int money) {
        super(text, name);
        this.money = money;
    }


    @Override
    public void cardAction(Player player) {
        player.setLastAction(player.getLastAction() + "\n - Har ved chancekort betalt " + this.money + "M til banken");

        super.cardAction(player);
        if (money < 20 && money > 0){
            player.addMoney(-money);
        }
    }
}
