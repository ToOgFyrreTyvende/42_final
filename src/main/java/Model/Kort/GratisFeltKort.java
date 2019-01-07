package Model.Kort;

import Model.ChanceKort;
import Model.Spiller;

import java.awt.*;

public class GratisFeltKort extends ChanceKort {
    private Color farve;

    public GratisFeltKort(String tekst, String navn, Color farve) {
        super(tekst, navn);
        this.farve = farve;
    }


    public Color getFarve() {
        return farve;
    }

    @Override
    public void kortHandling(Spiller spiller) {
        super.kortHandling(spiller);
    }
}
