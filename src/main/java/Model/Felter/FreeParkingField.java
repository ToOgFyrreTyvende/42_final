package Model.Felter;

import Model.Field;
import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Refuge;

import java.awt.*;

public class FreeParkingField extends Field {

    FreeParkingField(String name, String subText, String description) {
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
        return new GUI_Refuge("default", this.getName(), this.getSubText(), this.getDescription(), Color.white, Color.black);
    }

    @Override
    public void fieldAction(Player player) {
        player.setLastAction(" - Har fået gratis parkering. Intet sker.");
    }
}
