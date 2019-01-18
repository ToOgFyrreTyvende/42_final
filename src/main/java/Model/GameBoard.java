package Model;

import Model.Fields.*;
import Model.ChanceCards.ChanceCard;
import Model.ChanceCards.CardFactory;


import java.awt.*;

/**
 * Class containing all fields and chancecards, as well as methods to query them and manipulate them if neede
 */
public class GameBoard {

    private Field[] fields;

    private ChanceCard[] chanceCards;

    public GameBoard(){
        this.fields = new Field[Global.FIELD_COUNT];

        this.chanceCards = makeCards();
        this.fields = makeFields();

    }

    /**
     * Delegator method to create fields from our FieldFactory
     *
     * @return list of premade fields
     */
    private Field[] makeFields(){
        return FieldFactory.makeFields();
    }

    /**
     * Delegator method to create chance cards from our CardFactory
     *
     * @return list of premade chance cards
     */
    private ChanceCard[] makeCards(){
        return CardFactory.makeCards();
    }

    public Field[] getFields(){
        return fields;
    }

    /**
     * Get Field instance from the gameboard array of fields at a given index
     *
     * @param index field index to return
     * @return an instance of a Field from the GameBoard Field[]
     */
    public Field getFieldModel(int index){
        return fields[index % Global.FIELD_COUNT];
    }

    /**
     * Return whether or not a given Field is owned.
     * Only if it's a property.
     *
     * @param index field index to return
     * @return only true if the Field in question is of type PropertyField and owned by someone
     */
    public boolean isOwned(int index){
        Field field = this.getFields()[index % Global.FIELD_COUNT];
        if (field instanceof PropertyField){
            Player owner = ((PropertyField) field).getOwner();
            return owner != null;
        }
        return false;
    }

    /**
     * A way to retrieve the Jail field index from the Global constants class.
     *
     * @return jail field index
     */
    public int getJail(){
        return Global.JAIL_INDEX;
    }

    public ChanceCard[] getChanceCards(){
        return chanceCards;
    }

    public void setChanceCards(ChanceCard[] chanceCards){
        this.chanceCards = chanceCards;
    }

    /**
     * Retrieve a random chance card from the list of ChanceCards
     *
     * @return a random ChanceCard
     */
    public ChanceCard randomChanceCard(){
        // Generate a random number (float)
        float _random1 = (float) Math.random();
        // Scale number up to length of all ChanceCards, casting to int
        int _random2 = (int) (_random1 * (this.getChanceCards().length - 1));
        int nr = _random2 + 1;

        return this.getChanceCards()[nr];
    }

    /**
     * Retrieve closest field with given color from the point of a certain index
     *
     * @param index index to start from
     * @param color color to look for
     * @return Field that is hopefully the color asked for closest to the given index
     */
    public int closestColor(int index, Color color){
        Field[] fields = this.getFields();

        for (int i = 0; i < fields.length; i++){
            int correctIndex = i + index;
            Field tempField = fields[correctIndex % Global.FIELD_COUNT];

            if (tempField instanceof PropertyField &&
                    ((PropertyField) tempField).getColor() == color){
                return correctIndex % Global.FIELD_COUNT;
            }
        }

        return -1;
    }

    /**
     * Retrieve closest field with given name from the point of a certain index
     *
     * @param index index to start from
     * @param name  name to look for
     * @return Field that is hopefully the name asked for closest to the given index
     */
    public int closestName(int index, String name){
        Field[] fields = this.getFields();

        for (int i = 0; i < fields.length; i++){
            int correctIndex = i + index;
            Field tempField = fields[correctIndex % Global.FIELD_COUNT];

            if (tempField.getName() == name){
                return correctIndex % Global.FIELD_COUNT;
            }
        }
        return -1;
    }

    /**
     * Retrieve closest CompanyField that is a "shipping" field relative to an index
     *
     * @param index index to start from
     * @return Shipping CompanyField that is closest to a given index
     */
    public int getClosestShipping(int index){
        Field[] fields = this.getFields();

        for (int i = 0; i < fields.length; i++){
            int correctIndex = i + index;
            Field tempField = fields[correctIndex % Global.FIELD_COUNT];

            if (tempField instanceof CompanyField &&
                    ((CompanyField) tempField).isShipping()){
                return correctIndex % Global.FIELD_COUNT;
            }
        }
        return -1;
    }

    /**
     * Retrieve a PropertyField array containing all properties a player owns
     *
     * @param player
     * @return PropertyField[] of all properties the player owns (is Owner on), if none: an empty array
     */
    public PropertyField[] getPlayerProperties(Player player){

        PropertyField[] tempProperties = new PropertyField[Global.COLORED_PROPERTIES];

        // counter bruges til at holde styr på hvor mange ejendomme som er fundet
        int counter = 0;

        // Tjekker om et felt er et "property-felt" og om det ejes af den aktuelle spiller og indsætter i "tempProp..".
        // Counteren tæller en op hver gang et ejet felt er registreret.
        for (int i = 0; i < fields.length; i++){
            if (fields[i] instanceof PropertyField && ((PropertyField) fields[i]).getOwner() == player){
                tempProperties[counter] = (PropertyField) fields[i];
                counter++;
            }
        }

        // tempProperties vil i næsten alle tilfælde være for lang da længden er antal property-felter.
        // Her oprettes en ny array med den rigtige længde i forhold til hvor mange felter spilleren egentlig ejer.
        if (counter == 0){
            return new PropertyField[]{};
        } else {
            PropertyField[] ownedProperties = new PropertyField[counter];
            for (int i = 0; i < counter; i++){
                ownedProperties[i] = tempProperties[i];
            }
            return ownedProperties;

        }
    }

    /**
     * Extract all property names from the players owned properties
     *
     * @param player
     * @return String[] containing names of all properties owned, empty array if none
     */
    public String[] getPlayerPropertyNames(Player player){
        PropertyField[] props = getPlayerProperties(player);
        String[] names = new String[props.length];
        for (int i = 0; i < props.length; i++){
            names[i] = props[i].getName();
        }

        return names;
    }

    /**
     * Retrieve a CompanyField array containing all companies a player owns
     *
     * @param player
     * @return CompanyField[] of all companies the player owns (is Owner on), if none: an empty array
     */
    public CompanyField[] getPlayerCompanies(Player player){
        CompanyField[] tempProperties = new CompanyField[Global.COLORED_PROPERTIES];

        int counter = 0;

        // Tjekker om et felt er et "company-felt" og om det ejes af den aktuelle spiller og indsætter i "tempProp..".
        // Counteren tæller en op hver gang et ejet felt er registreret.
        for (int i = 0; i < fields.length; i++){
            if (fields[i] instanceof CompanyField && ((CompanyField) fields[i]).getOwner() == player){
                tempProperties[counter] = (CompanyField) fields[i];
                counter++;
            }
        }

        // tempProperties vil i næsten alle tilfælde være for lang da længden er antal property-felter.
        // Her oprettes en ny array med den rigtige længde i forhold til hvor mange felter spilleren egentlig ejer.
        if (counter == 0){
            return new CompanyField[]{};
        } else {
            CompanyField[] ownedProperties = new CompanyField[counter];
            for (int i = 0; i < counter; i++){
                ownedProperties[i] = tempProperties[i];
            }
            return ownedProperties;

        }
    }

    /**
     * Gets a unique PropertyField from a given name
     *
     * @param name is the name of the property to find
     * @return PropertyField that has teh same name as the parameter. Null if none
     */
    public PropertyField getPropertyFieldByName(String name){
        for (Field property : getFields()){
            if (property instanceof PropertyField){
                if (property.getName() == name){
                    return (PropertyField) property;
                }
            }
        }
        return null;
    }

    /**
     * Gets a unique CompanyField from a given name
     *
     * @param name is the name of the company to find
     * @return CompanyField that has teh same name as the parameter. Null if none
     */
    public CompanyField getCompanyFieldByName(String name){
        for (Field property : getFields()){
            if (property instanceof CompanyField){
                if (property.getName() == name){
                    return (CompanyField) property;
                }
            }
        }
        return null;
    }
}
