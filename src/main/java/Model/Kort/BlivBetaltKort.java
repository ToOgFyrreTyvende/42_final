package Model.Kort;

import Model.ChanceKort;
import Model.Spiller;

public class BlivBetaltKort extends ChanceKort {
    private int penge;
    private boolean andre;

    public BlivBetaltKort(String tekst, String navn, int penge, boolean andre) {
        super(tekst, navn);
        this.penge = penge;
        this.andre = andre;
    }


    public int getPenge() {
        return penge;
    }

    public void setPenge(int penge) {
        this.penge = penge;
    }

    public boolean isAndre() {
        return andre;
    }

    public void setAndre(boolean andre) {
        this.andre = andre;
    }

    @Override
    public void kortHandling(Spiller spiller) {
        super.kortHandling(spiller);
    }
}
