package View;

import Model.ChanceKort;
import Model.GameBoard;
import Model.Spiller;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

// Vi ved godt, at mange elementer i denne klasse er gentagende fra vores model
// Men vi har været nødt til at "tvinge" noget data ind i UIets format igennem denne klasse.

public class GameGUIView extends GameView {
    private GUI ui;
    private GUI_Field[] felter;

    private HashMap<Spiller, GUI_Player> spillere = new HashMap<>();

    @Override
    public void setSpilBraet(GameBoard spilBraet) {
        super.setSpilBraet(spilBraet);
        this.felter = getSpilBraet().getFelterGUI();
        this.ui = new GUI(felter);
    }

    @Override
    public int getAntalSpillere() {
        return ui.getUserInteger("Hvor mange spillere?", 2,4);
    }

    @Override
    public String getSpillerNavn(String tekst) {
        return ui.getUserString(tekst);
    }


    @Override
    public String getRundeValg(String... valg) {
        return ui.getUserButtonPressed("Foretag venligst en handling.", valg);
    }

    @Override
    public String getRundeValgMedTekst(String tekst, String... valg) {
        return ui.getUserButtonPressed(tekst, valg);
    }

    @Override
    public void setSpillere(Spiller ... spillereModel) {
        Color[] farver = {Color.blue, Color.red, Color.yellow, Color.green};

        for (int i = 0; i < spillereModel.length; i++){

            GUI_Car tempcar = new GUI_Car();
            tempcar.setPrimaryColor(farver[i]);

            GUI_Player tempSpillerGUI = new GUI_Player(spillereModel[i].getNavn(),
                                        spillereModel[i].getPenge(), tempcar);
            
            this.spillere.put(spillereModel[i], tempSpillerGUI);
            ui.addPlayer(this.spillere.get(spillereModel[i]));
        }
    }

    @Override
    public void resetBoard() {
        this.spillere.forEach((spillerModel, spillerBrik) -> this.felter[0].setCar(spillerBrik, true));
    }

    @Override
    public void setSpillerFelt(Spiller spillerModel, int felt) {
        int feltIndex = (felt % 24) -1;

        GUI_Player spillerGUI = this.spillere.get(spillerModel);

        this.felter[feltIndex].setCar(spillerGUI, true);
    }

    @Override
    public void setSpillerFelt(Spiller spillerModel, int felt, int forrigeFelt) {
        int feltIndex = felt;

        GUI_Player spillerGUI = this.spillere.get(spillerModel);
        GUI_Field field = this.felter[forrigeFelt];

        if(field.hasCar(spillerGUI)){
            field.setCar(spillerGUI, false);
        }


        this.felter[feltIndex].setCar(spillerGUI, true);
    }

    @Override
    public void setSpillerPenge(Spiller spiller, int penge) {
        this.spillere.get(spiller).setBalance(penge);
    }

    @Override
    public void opdaterSpillerData(Spiller spiller, int forrigeFelt) {
        setSpillerFelt(spiller, spiller.getFelt(), forrigeFelt);

        this.spillere.forEach((spillerModel, spillerBrik) ->
                                spillerBrik.setBalance(spillerModel.getPenge()));

    }

    @Override
    public void slutTekst(String tekst) {
        this.ui.getUserButtonPressed(tekst, "Afslut");
    }

    @Override
    public void setTerning(int slag) {
        this.ui.setDice(1,2,1,slag,2,1);
    }


    @Override
    public void setCenterTekst(String tekst) {
        ui.displayChanceCard(tekst);
    }
}
