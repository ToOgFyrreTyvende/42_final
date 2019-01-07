package stub;

import Model.GameBoard;
import Model.Spiller;
import View.GameView;

public class ViewStub extends GameView {

    private Spiller[] spillere;
    private final int SPILLERE_ANTAL = 2;

    public ViewStub() {
    }

    @Override
    public void setSpillere(Spiller[] spillere) {
        this.spillere = spillere;
    }

    @Override
    public int getAntalSpillere() {
        return SPILLERE_ANTAL;
    }

    @Override
    public String getSpillerNavn(String tekst) {
        int sum = 0;
        float _random1 = (float) Math.random();
        int _random2 = (int) (_random1 * this.getAntalSpillere());
        int resultat = _random2 + 1;

        return "Spiller " + resultat;
    }

    @Override
    public String getRundeValg(String... valg) {
        return "Rul terning";
    }

    @Override
    public String getRundeValgMedTekst(String tekst, String... valg) {
        return "Rul terning";
    }

    @Override
    public void resetBoard() {
    }

    @Override
    public void setSpillerFelt(Spiller spiller, int felt) {
    }

    @Override
    public void setSpillerFelt(Spiller spiller, int felt, int forrigeFelt) {
    }

    @Override
    public void setSpillerPenge(Spiller spiller, int penge) {
    }

    @Override
    public void opdaterSpillerData(Spiller spiller, int forrigeFelt) {
    }

    @Override
    public void slutTekst(String tekst) {
    }

    @Override
    public void setTerning(int slag) {
    }

    @Override
    public void setCenterTekst(String tekst) {
    }

    @Override
    public GameBoard getSpilBraet() {
        return super.getSpilBraet();
    }

    @Override
    public void setSpilBraet(GameBoard spilBraet) {
        super.setSpilBraet(spilBraet);
    }
}
