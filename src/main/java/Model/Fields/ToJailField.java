package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;


import java.awt.*;

public class ToJailField extends Field {

    private final int JAIL_ID = 6;

    ToJailField(String name, String subText, String description) {
        super(name, subText, description);
    }

    // #--------------Get--------------#

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getSubText() {
        return super.getSubText();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public GUI_Field makeGUIFields() {
        return new GUI_Jail("default", this.getName(), this.getSubText(),
                this.getDescription(), Color.white, Color.BLACK);
    }

    @Override
    public void fieldAction(Player player) {
        throwInJail(player);
    }

    private void throwInJail(Player player){
        player.setLastAction(player.getLastAction() + "\n - Er sat i fængsel");
        player.setFelt(JAIL_ID);
        player.setInJail(true);
    }
}
