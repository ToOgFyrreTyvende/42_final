package Model.ChanceCards;
import Model.Game;
import Model.Player;

/**
 * Monopoly Chance Cards are modelled as an abstract super class here.
 * From this class, the implementations of the different cards can be made.
 */
abstract public class ChanceCard {

    private String text;
    private String name;

    public ChanceCard(String text, String name) {
        this.text = text;
        this.name = name;
    }

    /**
     * Every type of ChanceCard can implement a card action.
     * If nothing is implemented, a flag in the player model is set.
     * This would be considered a polymorphic call to execute.
     * @param player that is using the ChanceCard
     * @param game instance of the game, to manipulate more than jsut the player (mostly not needed)
     */
    public void cardAction(Player player, Game game){
        player.setChanceCard(this);
    }

    public String getName() {
        return name;
    }
}