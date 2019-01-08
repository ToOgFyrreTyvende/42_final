package View;

import Model.GameBoard;
import Model.Spiller;

public abstract class GameView {

    protected GameBoard spilBraet;

    public abstract void setSpillere(Spiller[] spillere);
    public abstract int getAntalSpillere();
    public abstract String getSpillerNavn(String tekst);
    public abstract String getRundeValg(String ... valg);
    public abstract String getRundeValgMedTekst(String tekst, String ... valg);
    public abstract void resetBoard();
    public abstract void setSpillerFelt(Spiller spiller, int felt);
    public abstract void setSpillerFelt(Spiller spiller, int felt, int forrigeFelt);

    public abstract void setSpillerPenge(Spiller spiller, int penge);
    public abstract void opdaterSpillerData(Spiller spiller, int forrigeFelt);
    public abstract void slutTekst(String tekst);

    public abstract void setTerning(int slag);

    public abstract void setCenterTekst(String tekst);

    public GameBoard getSpilBraet() {
        return spilBraet;
    }

    public void setSpilBraet(GameBoard spilBraet) {
        this.spilBraet = spilBraet;
    }
}
