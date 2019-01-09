package Model;
import Model.Fields.*;
import Model.ChanceCards.ChanceCard;
import Model.ChanceCards.CardFactory;


import java.awt.*;

public class GameBoard {

    private Field[] fields;

    private ChanceCard[] chanceCard;

    public GameBoard() {
        this.fields = new Field[Global.FIELD_COUNT];

        this.chanceCard = makeCards();
        this.fields = makeFields();

    }

    private Field[] makeFields() {
        return FieldFactory.makeFields();
    }

    private ChanceCard[] makeCards(){
        return CardFactory.makeCards();
    }


    public Field[] getFields(){
        return fields;
    }

    public Field getFeltModel(int index){
        //System.out.println(index);
        return fields[index % Global.FIELD_COUNT];
    }

    public boolean isOwned(int index){
        Field field = this.getFields()[index % Global.FIELD_COUNT];
        if (field instanceof PropertyField){
            Player ejer = ((PropertyField) field).getOwner();
            return ejer != null;
        }
        return false;
    }

    int getJail(){
        return Global.JAIL_INDEX;
    }

    private ChanceCard[] getChanceCard() {
        return chanceCard;
    }

    public void setChanceCard(ChanceCard[] chanceCard) {
        this.chanceCard = chanceCard;
    }

    ChanceCard randomChanceCard(){
        float _random1 = (float) Math.random();
        int _random2 = (int) (_random1 * (this.getChanceCard().length - 1));
        int nr = _random2 + 1;

        return this.getChanceCard()[nr];
    }

    int closestColor(int index, Color color){
        Field[] fields = this.getFields();

        for (int i = 0; i < fields.length; i++) {
            int correctIndex = i + index;
            Field tempField = fields[correctIndex % Global.FIELD_COUNT];

            if (tempField instanceof PropertyField &&
                ((PropertyField) tempField).getColor() == color){
                return correctIndex % Global.FIELD_COUNT;
            }
        }

        return -1;
    }

    public Field[] getPlayerProperties(Player player) {

        Field[] tempProperties = new Field[Global.COLORED_PROPERTIES];

        int counter = 0;

        // Tjekker om et felt er et "property-felt" og om det ejes af den aktuelle spiller og indsætter i "tempProp..".
        // Counteren tæller en op hver gang et ejet felt er registreret.
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] instanceof PropertyField && ((PropertyField) fields[i]).getOwner() == player) {
                tempProperties[counter] = fields[i];
                counter++;
            }
        }

        // tempProperties vil i næsten alle tilfælde være for lang da længden er antal property-felter.
        // Her oprettes en ny array med den rigtige længde i forhold til hvor mange felter spilleren egentlig ejer.
        if (counter == 0) {
            return new Field[]{};
        } else {
            Field[] ownedProperties = new Field[counter];
            for (int i = 0; i < counter; i++) {
                ownedProperties[i] = tempProperties[i];
            }
            return ownedProperties;

        }
    }
}
