package Model;
import Model.Felter.*;
import Model.Kort.ChanceKort;
import Model.Kort.KortFabrik;
import gui_fields.*;

import java.awt.*;

public class GameBoard {

    private Felt[] felterModel;
    private GUI_Field[] felterGUI;

    private ChanceKort[] chanceKort;

    public GameBoard() {
        this.felterModel = new Felt[24];
        this.felterGUI = new GUI_Field[24];

        this.chanceKort = lavKort();
        this.felterModel = lavFelter();

        for (int i = 0; i < felterGUI.length; i++) {
            GUI_Field temp = felterModel[i].lavGUIFelt();
            felterGUI[i] = temp;
        }
    }

    private Felt[] lavFelter() {
        return FeltFabrik.lavFelter();
    }

    public ChanceKort[] lavKort(){
        return KortFabrik.lavKort();
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
