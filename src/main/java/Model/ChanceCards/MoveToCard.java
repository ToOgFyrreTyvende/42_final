package Model.ChanceCards;

import Model.Game;
import Model.Player;

public class MoveToCard extends ChanceCard {
    private int fieldIndex;

    MoveToCard(String text, String name, int fieldIndex) {
        super(text, name);
        this.fieldIndex = fieldIndex;
    }


    @Override
    public void cardAction(Player player, Game game) {
        super.cardAction(player, game);
        player.setField(fieldIndex);
        player.setLastAction(player.getLastAction() + "\n - Er rykket til feltet " + fieldIndex + 1);
    }
}
