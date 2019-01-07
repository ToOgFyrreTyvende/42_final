package Model;

import gui_fields.GUI_Field;

/**
 * -------------------------------------------------
 * Denne superklasse bruges til at gøre det nemmere
 * at give alle felterne et navn, beksrivelse og
 * hvilken effekt feltet skal gøre
 *-------------------------------------------------
 */

public abstract class Felt {
    protected String navn, subText, beskrivelse;


    // #----------Constructor----------#
    public Felt(String navn, String subText, String beskrivelse){
        this.navn=navn;
        this.subText=subText;
        this.beskrivelse=beskrivelse;
    }

    abstract public GUI_Field lavGUIFelt();

    abstract public void feltHandling(Spiller spiller);

    // #--------------Get--------------#
    public String getNavn() {
        return navn;
    }

    public String getSubText() {
        return subText;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }


}
