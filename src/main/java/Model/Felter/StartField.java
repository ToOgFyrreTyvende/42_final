package Model.Felter;

import Model.Field;
import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Start;

import java.awt.*;

public class StartField extends Field {

    StartField(String name, String subText, String description) {
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
        return new GUI_Start(this.getName(), this.getSubText(), this.getDescription(), Color.red, Color.BLACK);
    }

    @Override
    public void fieldAction(Player player) {
    }
}
