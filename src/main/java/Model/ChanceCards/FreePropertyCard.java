package Model.ChanceCards;

import Model.Fields.Field;
import Model.Fields.PropertyField;
import Model.Game;
import Model.GameBoard;
import Model.Player;

import java.awt.*;

public class FreePropertyCard extends ChanceCard {
    private Color color;

    FreePropertyCard(String title, String name, Color color) {
        super(title, name);
        this.color = color;
    }


    public Color getColor() {
        return color;
    }

    @Override
    public void cardAction(Player activePlayer, Game game) {
        super.cardAction(activePlayer, game);

    }


}
