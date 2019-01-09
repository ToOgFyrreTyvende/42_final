package Model.ChanceCards;

import Model.Player;

public class OutOfJailCard extends ChanceCard {

    OutOfJailCard(String text, String name) {
        super(text, name);
    }


    @Override
    public void cardAction(Player player) {
        super.cardAction(player);
        player.setLastAction(player.getLastAction() + "\n - Har et kort til gratis løsladelse.");
        player.setOutOfJailFree(true);
    }
}
