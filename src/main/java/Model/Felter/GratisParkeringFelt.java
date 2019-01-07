package Model.Felter;

import Model.Felt;
import Model.Spiller;
import gui_fields.GUI_Field;
import gui_fields.GUI_Refuge;
import gui_fields.GUI_Start;

import java.awt.*;

public class GratisParkeringFelt extends Felt {

    public GratisParkeringFelt(String navn, String subText, String beskrivelse) {
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
        return new GUI_Refuge("default", this.getNavn(), this.getSubText(), this.getBeskrivelse(), Color.white, Color.black);
    }

    @Override
    public void feltHandling(Spiller spiller) {
        spiller.setSidsteHandling(" - Har fået gratis parkering. Intet sker.");
    }
}
