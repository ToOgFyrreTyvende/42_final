package Model.Felter;

import Model.Felt;
import Model.Spiller;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

import java.awt.*;

/**
 * @author Xephyz
 * Denne klasse holder styr på alle felter der handler om Ejendomme som kan købes af spillere
 */

public class EjendomFelt extends Felt {
    // Variables
    private int pris;
    private int leje;
    private Spiller ejer;
    private Color farve;

    // #----------Constructor----------#
    public EjendomFelt(String navn, String subText, String beskrivelse, int pris, Color farve) {
        super(navn, subText, beskrivelse);
        this.pris = pris;
        this.farve = farve;
        this.leje = pris;
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
        return new GUI_Street(this.getNavn(), this.getSubText(),
                this.getBeskrivelse(), this.getPris() + "M", this.getFarve(), Color.black);

    }

    @Override
    public void feltHandling(Spiller spiller) {
        if (this.erEjet()){
            betalTilSpillerLogik(spiller);
        }else{
            spiller.setSidsteHandling(spiller.getSidsteHandling() + "\n - Har købt " +
                    this.getNavn() + " for " +
                    this.getPris() + "M");

            System.out.println("[INFO] " + spiller.getNavn() + " Har købt " +
                    this.getNavn() + " for " +
                    this.getPris()+ "M");

            koebFelt(spiller);
        }
    }

    public void feltHandling(Spiller spiller, int pris) {
        if (this.erEjet()){
            betalTilSpillerLogik(spiller);
        }else{
            spiller.setSidsteHandling(spiller.getSidsteHandling() + "\n - Har fået " +
                    this.getNavn() + " GRATIS!");
            System.out.println("[INFO] " + spiller.getNavn() + " Har gratis fået " +
                    this.getNavn());
            if (pris == 0)
                this.setEjer(spiller);
        }
    }

    private void betalTilSpillerLogik(Spiller spiller) {
        spiller.setSidsteHandling(spiller.getSidsteHandling() + "\n - Har betalt " +
                this.getPris() + "M til " +
                this.getEjer().getNavn());

        System.out.println("[INFO] " + spiller.getNavn() + " Har betalt " +
                this.getPris() + "M til " +
                this.getEjer().getNavn());
        betalTilSpillerFelt(spiller);
    }

    public void betalTilSpillerFelt(Spiller spiller){
        Spiller ejer = this.getEjer();
        int betaling = this.getLeje();
        spiller.addPenge( - betaling);
        ejer.addPenge(betaling);
    }

    private void koebFelt(Spiller spiller) {
        int betaling = this.getPris();
        spiller.addPenge( - betaling);
        this.setEjer(spiller);
    }


    public int getPris() {
        return pris;
    }

    public int getLeje() {
        return leje;
    }

    public Spiller getEjer() {
        return ejer;
    }

    public boolean erEjet(){
        Spiller ejer = this.getEjer();
        return ejer != null;
    }

    public Color getFarve() {
        return farve;
    }

    // #--------------Get--------------#
    public void setPris(int pris) {
        this.pris = pris;
    }

    public void setLeje(int leje) {
        this.leje = leje;
    }

    public void setEjer(Spiller ejer) {
        this.ejer = ejer;
    }

    public void setFarve(Color farve) {
        this.farve = farve;
    }
}
