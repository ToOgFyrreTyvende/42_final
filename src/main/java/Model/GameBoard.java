package Model;
import Model.Fields.*;
import Model.ChanceCards.ChanceCard;
import Model.ChanceCards.CardFactory;
import gui_fields.*;

import java.awt.*;

public class GameBoard {

    private Field[] felterModel;
    private GUI_Field[] felterGUI;

    private ChanceCard[] chanceCard;

    public GameBoard() {
        this.felterModel = new Field[24];
        this.felterGUI = new GUI_Field[24];

        this.chanceCard = lavKort();
        this.felterModel = lavFelter();

        for (int i = 0; i < felterGUI.length; i++) {
            GUI_Field temp = felterModel[i].makeGUIFields();
            felterGUI[i] = temp;
        }
    }

    private Field[] lavFelter() {
        return FieldFactory.makeFields();
    }

    private ChanceCard[] lavKort(){
        return CardFactory.makeCards();
    }


    Field[] getFelterModel(){
        return felterModel;
    }

    Field getFeltModel(int index){
        //System.out.println(index);
        return felterModel[index % 24];
    }

    public GUI_Field[] getFelterGUI() {
        return felterGUI;
    }

    public boolean erEjet(int index){
        Field field = this.getFelterModel()[index % 24];
        if (field instanceof PropertyField){
            Player ejer = ((PropertyField) field).getOwner();
            return ejer != null;
        }
        return false;
    }

    int getFaengsel(){
        return 6;
    }

    private ChanceCard[] getChanceCard() {
        return chanceCard;
    }

    public void setChanceCard(ChanceCard[] chanceCard) {
        this.chanceCard = chanceCard;
    }

    ChanceCard tilfaeldigKort(){
        float _random1 = (float) Math.random();
        int _random2 = (int) (_random1 * (this.getChanceCard().length - 1));
        int nr = _random2 + 1;

        return this.getChanceCard()[nr];
    }

    int taettestFarve(int index, Color farve){
        Field[] felter = this.getFelterModel();

        for (int i = 0; i < felter.length; i++) {
            int korrektIndex = i + index;
            Field tempField = felter[korrektIndex % 24];
            if (tempField instanceof PropertyField &&
                ((PropertyField) tempField).getColor() == farve){
                return korrektIndex % 24;
            }
        }

        return -1;
    }

}
