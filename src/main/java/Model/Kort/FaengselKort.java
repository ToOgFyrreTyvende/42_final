package Model.Kort;

import Model.ChanceKort;
import Model.Spiller;

public class FaengselKort extends ChanceKort {

    public FaengselKort(String tekst, String navn) {
        super(tekst, navn);
    }


    @Override
    public void kortHandling(Spiller spiller) {
        super.kortHandling(spiller);
        spiller.setSidsteHandling(spiller.getSidsteHandling() + "\n - Har et kort til gratis løsladelse.");
        spiller.setFriFaengsel(true);
    }
}
