package Model;
import Model.Felter.*;
import gui_fields.*;

import java.awt.*;
import java.util.*;

public class GameBoard {

    private Felt[] felterModel;
    private GUI_Field[] felterGUI;

    private ChanceKort[] chanceKort;

    public GameBoard() {
        this.felterModel = new Felt[24];
        this.felterGUI = new GUI_Field[24];
        this.chanceKort = ChanceKort.opretKort();

        this.felterModel = lavFelter();

        for (int i = 0; i < felterGUI.length; i++) {
            GUI_Field temp = felterModel[i].lavGUIFelt();
            felterGUI[i] = temp;
        }
    }

    private Felt[] lavFelter() {
        return new Felt[]{
                new StartFelt("Start", "2M", "Få 2M når du passerer!"),
                new EjendomFelt("Burgerbaren", "Pris: 1M", "Burgerbaren", 1, Color.gray),
                new EjendomFelt("Pizzahuset", "Pris: 1M", "Pizzahuset", 1, Color.gray),

                new ChanceFelt("Chance", "Chance", "Tag et chancekort"),

                new EjendomFelt("Slikbutikken", "Pris: 1M", "Slikbutikken", 1, Color.blue),
                new EjendomFelt("Iskiosken", "Pris: 1M", "Iskiosken", 1, Color.blue),

                new FaengselFelt("Fængsel", "På besøg", "Enten er du endt i fængsel, eller er du på besøg."),

                new EjendomFelt("Museet", "Pris: 2M", "Museet", 2, Color.pink),
                new EjendomFelt("Biblioteket", "Pris: 2M", "Biblioteket", 2, Color.pink),

                new ChanceFelt("Chance", "Chance", "Tag et chancekort"),

                new EjendomFelt("Skaterparken", "Pris: 2M", "Skaterparken", 2, Color.yellow),
                new EjendomFelt("Swimmingpoolen", "Pris: 2M", "Swimmingpoolen", 2, Color.yellow),

                new GratisParkeringFelt("Fri", "Gratis Parkering", "Hold en fridag"),

                new EjendomFelt("Spillehallen", "Pris: 3M", "Spillehallen", 3, Color.red),
                new EjendomFelt("Biograf", "Pris: 3M", "Biograf", 3, Color.red),

                new ChanceFelt("Chance", "Chance", "Tag et chancekort"),

                new EjendomFelt("Legetøjsbutik", "Pris: 3M", "Legetøjsbutik", 3, Color.orange),
                new EjendomFelt("Dyrehandel", "Pris: 3M", "Dyrehandel", 3, Color.orange),

                new TilFaengselFelt("Fængsel", "Gå til fængsel", "Øv, du skal til fængsel"),

                new EjendomFelt("Bowlinghallen", "Pris: 4M", "Bowlinghallen", 4, Color.green),
                new EjendomFelt("Zoo", "Pris: 4M", "Zoo", 4, Color.green),

                new ChanceFelt("Chance", "Chance", "Tag et chancekort"),

                new EjendomFelt("Vandlandet", "Pris: 5M", "Vandlandet", 5, Color.cyan),
                new EjendomFelt("Strandpromenaden", "Pris: 5M", "Strandpromenaden", 5, Color.cyan)
        };
    }


    public Felt[] getFelterModel(){
        return felterModel;
    }

    public Felt getFeltModel(int index){
        //System.out.println(index);
        return felterModel[index % 24];
    }

    public GUI_Field[] getFelterGUI() {
        return felterGUI;
    }

    public boolean erEjet(int index){
        Felt felt = this.getFelterModel()[index % 24];
        if (felt instanceof EjendomFelt){
            Spiller ejer = ((EjendomFelt) felt).getEjer();
            return ejer != null;
        }
        return false;
    }

    public int getFaengsel(){
        return 6;
    }

    public ChanceKort[] getChanceKort() {
        return chanceKort;
    }

    public void setChanceKort(ChanceKort[] chanceKort) {
        this.chanceKort = chanceKort;
    }

    ChanceKort tilfaeldigKort(){
        float _random1 = (float) Math.random();
        int _random2 = (int) (_random1 * (this.getChanceKort().length - 1));
        int nr = _random2 + 1;

        return this.getChanceKort()[nr];
    }

    public int taettestFarve(int index, Color farve){
        Felt[] felter = this.getFelterModel();

        for (int i = 0; i < felter.length; i++) {
            int korrektIndex = i + index;
            Felt tempFelt = felter[korrektIndex % 24];
            if (tempFelt instanceof EjendomFelt &&
                ((EjendomFelt) tempFelt).getFarve() == farve){
                return korrektIndex % 24;
            }
        }

        return -1;
    }

}
