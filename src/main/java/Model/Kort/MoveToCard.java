package Model.Kort;

import Model.Player;

public class MoveToCard extends ChanceCard {
    private int fieldIndex;

    MoveToCard(String text, String name, int fieldIndex) {
        super(text, name);
        this.fieldIndex = fieldIndex;
    }


    @Override
    public void cardAction(Player player) {
        super.cardAction(player);
        player.setFelt(fieldIndex);
        player.setLastAction(player.getLastAction() + "\n - Er rykket til feltet " + fieldIndex + 1);
    }
}
