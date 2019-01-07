package Model.Felter;

import Model.Felt;
import Model.Spiller;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;

import java.awt.*;

public class ChanceFelt extends Felt {

    public ChanceFelt(String navn, String subText, String beskrivelse) {
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
        return new GUI_Chance("?", this.getSubText(), this.getBeskrivelse(), Color.white, Color.black);
    }

    @Override
    public void feltHandling(Spiller spiller) {
        spiller.setSidsteHandling(spiller.getSidsteHandling() + "\n - Er landet på et chancekort.");
        spiller.setChanceFelt(true);
    }
}
