package Model;

import Model.ChanceCards.ChanceCard;
import Model.Fields.Field;

/**
 * ------------------------------------------------------------/
 * Denne klasse kaldes for at oprette en ny spiller
 * Model.Player kan have et brugervalgt name som bruges under
 * kørsel af spillet
 * ------------------------------------------------------------/
 */
public class Player {
    // We need previous field to remove user from it in the GUI

    private int previousField = 0;
    private int field = 0;
    private String name;
    private int currentMoney = 1;
    private int lastDiceResult = 0;
    private int[] lastDicePair;
    private boolean inJail = false;

    private ChanceCard chanceCard;
    private boolean chanceField = false;
    private boolean outOfJailFree = false;

    private String lastAction = "";

    private boolean bankrupt = false;

    // #----------Constructor----------#
    public Player(String name){
        // Vælg spiller name selv
        this.name = name;
    }

    // #------------Get/Set------------#
    public int getField(){
        // Returnerer nuværende field af spiller
        return field;
    }
    public int setField(int newField){
        // Returnerer nye field af spiller
        field = newField;
        return field;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public int getMoney(){
        return this.currentMoney;
    }

    void setMoney(int money){
        this.currentMoney = money;
    }

    public void setLastDiceResult(int lastDiceResult) {
        this.lastDiceResult = lastDiceResult;
    }

    public void setLastDicePair(int[] lastDicePair) {
        this.lastDicePair = lastDicePair;
    }

    public void setChanceCard(ChanceCard chanceCard) {
        this.chanceCard = chanceCard;
    }

    public void setOutOfJailFree(boolean outOfJailFree) {
        this.outOfJailFree = outOfJailFree;
    }

    public void setChanceField(boolean chanceField) {
        this.chanceField = chanceField;
    }

    public boolean isBankrupt() {return this.bankrupt;}

    public void setBankrupt(boolean bankrupt) {this.bankrupt = bankrupt;}

    // #--------------Get--------------#
    public String getName(){
        // Returnerer spiller navn
        return name;
    }

    public int getLastDiceResult() {
        return lastDiceResult;
    }

    public int[] getLastDicePair() {
        return lastDicePair;
    }

    public int[] getDicePair(){
        return null;
    }

    public ChanceCard getChanceCard() {
        return chanceCard;
    }

    public boolean isOutOfJailFree() {
        return outOfJailFree;
    }

    public boolean isChanceField() {
        return chanceField;
    }

    public String getLastAction() {
        return lastAction;
    }

    // #-------------Other-------------#
    public void addMoney(int money){
        this.currentMoney = currentMoney + money;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    @Override
    public String toString() {
        if (this.lastAction.equals("")) {
            return this.getName() + " er landet på " + (this.getField() + 1) + ".";
        }else {
            return this.getName() + " " + this.getLastAction();
        }
    }

    public int getPreviousField() {
        return previousField;
    }

    public void setPreviousField(int previousField) {
        this.previousField = previousField;
    }

    public void payTenPercent(){
        int payment = currentMoney/10;
        addMoney(-payment);
    }
}
