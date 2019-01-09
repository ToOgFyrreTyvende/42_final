package Model.Kort;
import Model.Player;

abstract public class ChanceCard {

    private String text;
    private String name;

    public ChanceCard(String text, String name) {
        this.text = text;
        this.name = name;
    }

    public void cardAction(Player player){
        player.setChanceCard(this);
    }
}