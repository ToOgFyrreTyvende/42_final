package Model.Kort;

import Model.Spiller;

public class BetalKort extends ChanceKort {
    private int penge;

    public BetalKort(String tekst, String navn, int penge) {
        super(tekst, navn);
        this.penge = penge;
    }


    @Override
    public void kortHandling(Spiller spiller) {
        spiller.setSidsteHandling(spiller.getSidsteHandling() + "\n - Har ved chancekort betalt " + this.penge + "M til banken");

        super.kortHandling(spiller);
        if (penge < 20 && penge > 0){
            spiller.addPenge(- penge);
        }
    }
}
