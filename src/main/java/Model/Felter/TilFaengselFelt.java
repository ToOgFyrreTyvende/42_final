package Model.Felter;

import Model.Felt;
import Model.Spiller;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import gui_fields.GUI_Refuge;


import java.awt.*;

public class TilFaengselFelt extends Felt {

    private final int FAENGSEL_ID = 6;

    public TilFaengselFelt(String navn, String subText, String beskrivelse) {
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
        return new GUI_Jail("default", this.getNavn(), this.getSubText(),
                this.getBeskrivelse(), Color.white, Color.BLACK);
    }

    @Override
    public void feltHandling(Spiller spiller) {
        smidIFaengsel(spiller);
    }

    public void smidIFaengsel(Spiller spiller){
        spiller.setSidsteHandling(spiller.getSidsteHandling() + "\n - Er sat i fængsel");
        spiller.setFelt(FAENGSEL_ID);
        spiller.setiFaengsel(true);
    }
}
