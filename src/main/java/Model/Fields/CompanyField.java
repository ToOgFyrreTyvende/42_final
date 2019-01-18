package Model.Fields;

import Model.Player;
import gui_fields.GUI_Brewery;
import gui_fields.GUI_Field;
import gui_fields.GUI_Shipping;
import gui_resources.Attrs;

import java.awt.*;

/**
 * This class is a simpler version of PropertyField
 */
public class CompanyField extends Field {

    private Color color;
    private boolean isShipping;
    private int price;
    private int rent;
    private Player owner;

    /**
     * A CompanyField can be purchased, however they do not have variable rent.
     * A game rule says to double rent if all same company fields are owned.
     *
     * @param name
     * @param subText
     * @param description
     * @param price
     * @param color
     * @param isShipping
     */
    public CompanyField(String name, String subText, String description, int price, Color color, boolean isShipping){
        super(name, subText, description);
        this.price = price;
        this.rent = price / 2; // SKAL ÆNDRET SENERE !!!
        this.color = color;
        this.isShipping = isShipping;
    }

    /**
     * When a player lands on an owned field, they will pay rent to the owner.
     * If not another part of the game can take care of letting it be bought
     * In both cases, the main player log is written to
     *
     * @param player that has landed on the field
     */
    @Override
    public void fieldAction(Player player){
        if (this.isOwned()){
            payToPlayerLogic(player);
        } else {
            player.setLastAction(player.getLastAction() + "\n - Er landet på " +
                    this.getName());

            System.out.println("[INFO] " + player.getName() + " er landet på " +
                    this.getName());
        }
    }

    /**
     * If the owner is not out of the game (bankrupt) rent is paid.
     * Otherwise the action is ignored
     *
     * @param player the player who will be paying "rent"
     */
    private void payToPlayerLogic(Player player){
        if (!owner.isBankrupt() && player != owner){
            player.setLastAction(player.getLastAction() + "\n - Har betalt " +
                    this.getRent() + " kr. til " +
                    this.getOwner().getName() + ".");

            System.out.println("[INFO] " + player.getName() + " har betalt " +
                    this.getRent() + " kr. til " +
                    this.getOwner().getName() + ".");
            payToPlayerField(player);
        }
    }

    /**
     * The actual rent payment of the house.
     * Simply calcualte the rent, pay the owner, remove money from player.
     *
     * @param player
     */
    private void payToPlayerField(Player player){
        Player owner = this.getOwner();
        int payment = this.getRent();
        player.addMoney(-payment);
        owner.addMoney(payment);
    }

    /**
     * Method to buy a CompanyField from given price array. Also write to log.
     *
     * @param player the player who will buy the company
     */
    public void buyField(Player player){
        if (!isOwned()){
            player.setLastAction(player.getLastAction() + "\n - Har købt " +
                    this.getName() + " for " +
                    this.getPrice() + " kr.");

            System.out.println("[INFO] " + player.getName() + " har købt " +
                    this.getName() + " for " +
                    this.getPrice() + " kr.");

            int payment = this.getPrice();
            player.addMoney(-payment);
            this.setOwner(player);
        }
    }

    @Override
    public String getName(){
        return super.getName();
    }

    @Override
    public String getSubText(){
        return super.getSubText();
    }

    @Override
    public String getDescription(){
        return super.getDescription();
    }

    public boolean isOwned(){
        Player owner = this.getOwner();
        return owner != null;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public boolean isShipping(){
        return isShipping;
    }

    public void setShipping(boolean shipping){
        isShipping = shipping;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public Player getOwner(){
        return owner;
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }

    public int getRent(){
        return rent;
    }

    public void setRent(int rent){
        this.rent = rent;
    }
}
