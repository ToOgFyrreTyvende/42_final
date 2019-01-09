package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

import java.awt.*;

/**
 * @author Xephyz
 * Denne klasse holder styr på alle felter der handler om Ejendomme som kan købes af spillere
 */

public class PropertyField extends Field {
    // Variables
    private int price;
    private int rent;
    private Player owner;
    private Color color;

    // #----------Constructor----------#
    public PropertyField(String name, String subText, String description, int price, Color color) {
        super(name, subText, description);
        this.price = price;
        this.color = color;
        this.rent = price;
    }

    // #--------------Get--------------#
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getSubText() {
        return super.getSubText();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }


    @Override
    public void fieldAction(Player player) {
        if (this.isOwned()){
            payToPlayerLogic(player);
        }else{
            player.setLastAction(player.getLastAction() + "\n - Har købt " +
                    this.getName() + " for " +
                    this.getPrice() + "M");

            System.out.println("[INFO] " + player.getName() + " Har købt " +
                    this.getName() + " for " +
                    this.getPrice()+ "M");

            buyField(player);
        }
    }

    public void fieldAction(Player player, int price) {
        if (this.isOwned()){
            payToPlayerLogic(player);
        }else{
            player.setLastAction(player.getLastAction() + "\n - Har fået " +
                    this.getName() + " GRATIS!");
            System.out.println("[INFO] " + player.getName() + " Har gratis fået " +
                    this.getName());
            if (price == 0)
                this.setOwner(player);
        }
    }

    private void payToPlayerLogic(Player player) {
        player.setLastAction(player.getLastAction() + "\n - Har betalt " +
                this.getPrice() + "M til " +
                this.getOwner().getName());

        System.out.println("[INFO] " + player.getName() + " Har betalt " +
                this.getPrice() + "M til " +
                this.getOwner().getName());
        payToPlayerField(player);
    }

    private void payToPlayerField(Player player){
        Player ejer = this.getOwner();
        int payment = this.getRent();
        player.addMoney( - payment);
        ejer.addMoney(payment);
    }

    private void buyField(Player player) {
        int betaling = this.getPrice();
        player.addMoney( - betaling);
        this.setOwner(player);
    }


    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isOwned(){
        Player owner = this.getOwner();
        return owner != null;
    }

    public Color getColor() {
        return color;
    }

    // #--------------Get--------------#
    public void setPrice(int price) {
        this.price = price;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    private void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
