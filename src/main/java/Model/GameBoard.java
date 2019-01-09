package Model;
import Model.Fields.*;
import Model.ChanceCards.ChanceCard;
import Model.ChanceCards.CardFactory;


import java.awt.*;

public class GameBoard {

    private Field[] fieldsModel;
    private gui_fields.GUI_Field[] fieldsGUI;

    private ChanceCard[] chanceCard;

    public GameBoard() {
        this.fieldsModel = new Field[24];
        this.fieldsGUI = new gui_fields.GUI_Field[24];

        this.chanceCard = lavKort();
        this.fieldsModel = lavFelter();

        for (int i = 0; i < fieldsGUI.length; i++) {
            gui_fields.GUI_Field temp = fieldsModel[i].makeGUIFields();
            fieldsGUI[i] = temp;
        }
    }

    private Field[] lavFelter() {
        return FieldFactory.makeFields();
    }

    private ChanceCard[] lavKort(){
        return CardFactory.makeCards();
    }


    Field[] getFieldsModel(){
        return fieldsModel;
    }

    Field getFeltModel(int index){
        //System.out.println(index);
        return fieldsModel[index % 24];
    }

    public gui_fields.GUI_Field[] getFieldsGUI() {
        return fieldsGUI;
    }

    public boolean erEjet(int index){
        Field field = this.getFieldsModel()[index % 24];
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
        Field[] felter = this.getFieldsModel();

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
