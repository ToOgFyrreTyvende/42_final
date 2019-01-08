package Model.Kort;

import Model.Spiller;

public class RykKort extends ChanceKort {
    private int feltIndex;

    public RykKort(String tekst, String navn, int feltIndex) {
        super(tekst, navn);
        this.feltIndex = feltIndex;
    }


    @Override
    public void kortHandling(Spiller spiller) {
        super.kortHandling(spiller);
        spiller.setFelt(feltIndex);
        spiller.setSidsteHandling(spiller.getSidsteHandling() + "\n - Er rykket til feltet " + feltIndex + 1);
    }
}
