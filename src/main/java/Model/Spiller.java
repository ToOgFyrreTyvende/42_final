package Model;

import Model.Kort.ChanceKort;

/**
 * ------------------------------------------------------------/
 * Denne klasse kaldes for at oprette en ny spiller
 * Model.Spiller kan have et brugervalgt navn som bruges under
 * kørsel af spillet
 * ------------------------------------------------------------/
 */
public class Spiller {
    private int felt = 0;
    private String navn;
    private int pengebeholdning = 1;
    private int sidstSlaaet = 0;
    private boolean iFaengsel = false;

    private ChanceKort chanceKort;
    private boolean chanceFelt = false;
    private boolean friFaengsel = false;

    private String sidsteHandling = "";

    // #----------Constructor----------#
    public Spiller(String navn){
        // Vælg spiller navn selv
        this.navn = navn;
    }

    // #------------Get/Set------------#
    public int getFelt(){
        // Returnerer nuværende felt af spiller
        return felt;
    }
    public int setFelt(int nyFelt){
        // Returnerer nye felt af spiller
        felt = nyFelt;
        return felt;
    }

    public boolean isiFaengsel() {
        return iFaengsel;
    }

    public void setiFaengsel(boolean iFaengsel) {
        this.iFaengsel = iFaengsel;
    }

    public int getPenge(){
        return this.pengebeholdning;
    }

    public void setPenge(int penge){
        this.pengebeholdning = penge;
    }

    public void setSidstSlaaet(int sidstSlaaet) {
        this.sidstSlaaet = sidstSlaaet;
    }

    public void setChaneKort(ChanceKort chanceKort) {
        this.chanceKort = chanceKort;
    }

    public void setFriFaengsel(boolean friFaengsel) {
        this.friFaengsel = friFaengsel;
    }

    public void setChanceFelt(boolean chanceFelt) {
        this.chanceFelt = chanceFelt;
    }

    // #--------------Get--------------#
    public String getNavn(){
        // Returnerer spiller navn

        return navn;
    }

    public int getSidstSlaaet() {
        return sidstSlaaet;
    }

    public ChanceKort getChanceKort() {
        return chanceKort;
    }

    public boolean isFriFaengsel() {
        return friFaengsel;
    }

    public boolean isChanceFelt() {
        return chanceFelt;
    }

    public String getSidsteHandling() {
        return sidsteHandling;
    }

    // #-------------Other-------------#
    public void addPenge(int penge){
        this.pengebeholdning = pengebeholdning + penge;
    }

    public void setSidsteHandling(String sidsteHandling) {
        this.sidsteHandling = sidsteHandling;
    }

    @Override
    public String toString() {
        if (this.sidsteHandling.equals("")) {
            return "Spiller: " + this.getNavn() + " er landet på felt nr. " + this.getFelt() + 1;
        }else {
            return "Spiller: " + this.getNavn() + " " + this.getSidsteHandling();
        }
    }
}
