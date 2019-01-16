package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;

/**
 * -------------------------------------------------
 * Denne superklasse bruges til at gøre det nemmere
 * at give alle felterne et name, beksrivelse og
 * hvilken effekt feltet skal gøre
 *-------------------------------------------------
 */

public abstract class Field {
    private String name;
    private String subText;
    private String description;


    // #----------Constructor----------#
    public Field(String name, String subText, String description){
        this.name = name;
        this.subText=subText;
        this.description = description;
    }

    public void fieldAction(Player player){
        player.setLastAction(player.getLastAction() + "\n - Er landet på " +
                this.getName());

        System.out.println("[INFO] " + player.getName() + " er landet på " +
                this.getName());
    }

    // #--------------Get--------------#
    public String getName() {
        return name;
    }

    public String getSubText() {
        return subText;
    }

    public String getDescription() {
        return description;
    }


}
