package Model.Felter;

import Model.Felt;
import Model.Spiller;
import gui_fields.GUI_Field;
import gui_fields.GUI_Start;

import java.awt.*;

public class StartFelt extends Felt {

    public StartFelt(String navn, String subText, String beskrivelse) {
        super(navn, subText, beskrivelse);
    }

    // #--------------Get--------------#
    @Override
    public String getNavn() {
        return super.getNavn();
    }

    @Override
    public String getSubText() {
        return super.getSubText();
    }

    @Override
    public String getBeskrivelse() {
        return super.getBeskrivelse();
    }

    @Override
    public GUI_Field lavGUIFelt() {
        return new GUI_Start(this.getNavn(), this.getSubText(), this.getBeskrivelse(), Color.red, Color.BLACK);
    }

    @Override
    public void feltHandling(Spiller spiller) {
    }
}
