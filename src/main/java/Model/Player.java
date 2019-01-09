package Model;

import Model.Kort.ChanceCard;

/**
 * ------------------------------------------------------------/
 * Denne klasse kaldes for at oprette en ny spiller
 * Model.Player kan have et brugervalgt name som bruges under
 * kørsel af spillet
 * ------------------------------------------------------------/
 */
public class Player {
    private int field = 0;
    private String name;
    private int currentMoney = 1;
    private int lastDiceResult = 0;
    private boolean inJail = false;

    private ChanceCard chanceCard;
    private boolean chanceField = false;
    private boolean outOfJailFree = false;

    private String lastAction = "";

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
    public int setFelt(int newField){
        // Returnerer nye field af spiller
        field = newField;
        return field;
    }

    boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public int getMoney(){
        return this.currentMoney;
    }

    void setMoney(int penge){
        this.currentMoney = penge;
    }

    void setLastDiceResult(int lastDiceResult) {
        this.lastDiceResult = lastDiceResult;
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

    // #--------------Get--------------#
    public String getName(){
        // Returnerer spiller navn
        return name;
    }

    public int getLastDiceResult() {
        return lastDiceResult;
    }

    ChanceCard getChanceCard() {
        return chanceCard;
    }

    boolean isOutOfJailFree() {
        return outOfJailFree;
    }

    boolean isChanceField() {
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
            return "Player: " + this.getName() + " er landet på field nr. " + this.getField() + 1;
        }else {
            return "Player: " + this.getName() + " " + this.getLastAction();
        }
    }
}
