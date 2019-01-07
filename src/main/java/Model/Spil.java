package Model;

import Model.Felter.EjendomFelt;
import Model.Felter.TilFaengselFelt;
import Model.Kort.BetalKort;
import Model.Kort.BlivBetaltKort;
import Model.Kort.FaengselKort;
import Model.Kort.GratisFeltKort;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------------------------------------/
 * Denne klasse er hoveddelen der kører hele spillet og diverse
 * klasser efter en bruger har åbnet programmet
 * ------------------------------------------------------------/
 */

public class Spil {
    private final int RUNDE_PENGE = 2;
    private final int FAENGSEL_PRIS = 1;

    private int[] muligeStartPenge = {20, 18, 16};
    private int startPenge;

    private Spiller[] spillere;
    private Spiller vinder;
    private Spiller aktivSpiller;

    private Terning terning;
    private List<Runde> runder;
    private Runde aktivRunde;
    private boolean afsluttet;

    private GameBoard spilBraet;

    // #----------Constructor----------#

    public Spil(GameBoard sb, String[] spillerNavne){
        opretSpillere(spillerNavne);

        //Kodedelen med runder er taget fra vores forrige opgave: 42_del1
        runder = new ArrayList<>();
        runder.add(new Runde());
        terning = new Terning();

        aktivSpiller = spillere[0];
        aktivRunde = runder.get(runder.size()-1);

        spilBraet = sb;

        afsluttet = false;
    }

    private void opretSpillere(String[] spillerNavne){

        this.startPenge = muligeStartPenge[spillerNavne.length - 2];

        Spiller[] spillere = new Spiller[spillerNavne.length];
        for (int i = 0; i < spillerNavne.length; i++) {
            spillere[i] = new Spiller(spillerNavne[i]);
            spillere[i].setPenge(startPenge);
        }

        this.spillere = spillere;
    }


    public Spiller spilTur(){
        if (!afsluttet) {
            int nuIndex = java.util.Arrays.asList(spillere).indexOf(aktivSpiller);
            int nyIndex = (nuIndex + 1) % spillere.length;
            int slag = terning.getResultat();
            int[] tempTur = {slag, nuIndex};

            Spiller _aktivSpiller = aktivSpiller;
            int feltId = (aktivSpiller.getFelt() + slag) % 24;

            feltId = spilRegler(feltId);

            opdaterAktivSpillerMedSlag(feltId, slag);

            aktivRunde.tilfoejTur(tempTur);
            aktivSpiller = spillere[nyIndex];

            checkRunde();

            return _aktivSpiller;
        }else{
            return null;
        }
    }

    public int spilRegler(int feltId) {
        if (aktivSpiller.isiFaengsel()){
            if (!aktivSpiller.isFriFaengsel()){
                System.out.println("[INFO] " + aktivSpiller.getNavn() + " Har betalt " +
                        FAENGSEL_PRIS + " For at komme ud af fængslet");
                aktivSpiller.setSidsteHandling("\n - Har betalt for fængsel.");
                aktivSpiller.addPenge(- FAENGSEL_PRIS);
            }else{
                aktivSpiller.setSidsteHandling("\n - Har brugt sit løsladelses chancekort.");
                System.out.println("[INFO] " + aktivSpiller.getNavn() + " Kom ud af fængslet med deres 'frikort'");
            }

            aktivSpiller.setiFaengsel(false);
            checkRunde();
        }

        if (!afsluttet){
            if (feltId < aktivSpiller.getFelt()){
                System.out.println("[INFO] " + aktivSpiller.getNavn() + " har passeret start. +2M");
                aktivSpiller.setSidsteHandling(aktivSpiller.getSidsteHandling() + "\n - Har fået 2M for at passere start.");
                tilFoejStartPenge(aktivSpiller);
            }
            Felt landetFelt = this.getSpilBraet().getFeltModel(feltId);
            landetFelt.feltHandling(aktivSpiller);
            //chancekort skal tilføjes...

            if (aktivSpiller.isChanceFelt()){
                chanceFeltHandling(aktivSpiller);
                return aktivSpiller.getFelt();
            }

        }

        return feltId;
    }

    private void chanceFeltHandling(Spiller aktivSpiller) {
        aktivSpiller.setChanceFelt(false);

        ChanceKort kort = this.getSpilBraet().tilfaeldigKort();
        aktivSpiller.setChaneKort(kort);

        aktivSpiller.getChanceKort().kortHandling(aktivSpiller);

        if (kort instanceof BlivBetaltKort){
            if (((BlivBetaltKort) kort).isAndre()){
                betaltAfAndre(((BlivBetaltKort) kort).getPenge());
                aktivSpiller.setSidsteHandling(aktivSpiller.getSidsteHandling() + "\n - Har fået " + ((BlivBetaltKort) kort).getPenge()
                        + "M fra hver af de andre spillere.");
            }else{
                aktivSpiller.setSidsteHandling(aktivSpiller.getSidsteHandling() + "\n - Har fået " + ((BlivBetaltKort) kort).getPenge()
                        + "M fra banken.");
                aktivSpiller.addPenge(((BlivBetaltKort) kort).getPenge());
            }
        }else if(kort instanceof GratisFeltKort){
            int feltIndex = this.getSpilBraet().taettestFarve(
                    aktivSpiller.getFelt(),
                    ((GratisFeltKort) kort).getFarve());
            Felt tempFelt = this.getSpilBraet().getFelterModel()[feltIndex];

            if (tempFelt instanceof EjendomFelt){
                ((EjendomFelt) tempFelt).feltHandling(aktivSpiller, 0);
                aktivSpiller.setFelt(feltIndex);
            }

        }
    }

    private void betaltAfAndre(int penge) {
        // Vi trækker penge fra alle spillere
        for (Spiller spiller : spillere) {
            spiller.addPenge(- penge);
        }

        // SIden vi fjerner antallet af penge fra alle spilelre. skal det tilføjes mængden af penge
        // ganget med alle spillere til stede for at spilleren får den rigtige mængde
        int antalAtFaa = penge * spillere.length - 1;
        aktivSpiller.addPenge(antalAtFaa);
    }

    private void tilFoejStartPenge(Spiller spiller) {
        spiller.addPenge(RUNDE_PENGE);
    }

    private void opdaterAktivSpillerMedSlag(int feltId, int slag) {
        if (aktivSpiller.isiFaengsel()){
            aktivSpiller.setFelt(this.getSpilBraet().getFaengsel());
            aktivSpiller.setSidstSlaaet(slag);
        }else{
            aktivSpiller.setFelt(feltId);
            aktivSpiller.setSidstSlaaet(slag);
        }
    }

    //godt og grundigt Yoinked direkte fra vores 42_del1 af CDIO
    public void checkRunde(){
        // Vi tjekker om den nuværende spiller er den sidste psiller i spiller listen. Dette gør, at
        // alle spillere har mulighed for at vinde i slutningen af en runde

        for (int i = 0; i < spillere.length; i++) {
            if (spillere[i].getPenge() <= 0){
                afsluttet = true;
                this.setVinder(this.findVinder());
            }
        }
    }

    public Spiller findVinder() {

        Spiller højest = null;

        if (afsluttet) {
            int max = 0;


            for (int i = 0; i < spillere.length; i++) {
                if (spillere[i].getPenge() > max) {
                    max = spillere[i].getPenge();
                    højest = spillere[i];
                }
            }
        }
        return (højest);
    }

    // Getters & setters


    public Spiller[] getSpillere() {
        return spillere;
    }

    public void setSpillere(Spiller[] spillere) {
        this.spillere = spillere;
    }

    public Spiller getVinder() {
        return vinder;
    }

    public void setVinder(Spiller vinder) {
        this.vinder = vinder;
    }

    public Spiller getAktivSpiller() {
        return aktivSpiller;
    }

    public void setTerning(Terning terning) {
        this.terning = terning;
    }


    public void setAfsluttet(boolean afsluttet) {
        this.afsluttet = afsluttet;
    }

    public GameBoard getSpilBraet() {
        return spilBraet;
    }


    public int getRUNDE_PENGE() {
        return RUNDE_PENGE;
    }

    public boolean isAfsluttet() {
        return afsluttet;
    }
}
