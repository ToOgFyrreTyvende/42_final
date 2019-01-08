package Model.Kort;
import Model.Spiller;

import java.awt.*;

abstract public class ChanceKort {

    private String tekst;
    private String navn;

    public ChanceKort(String tekst, String navn) {
        this.tekst = tekst;
        this.navn = navn;
    }

    public void kortHandling(Spiller spiller){
        spiller.setChaneKort(this);
    }
}