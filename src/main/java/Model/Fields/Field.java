package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;

/**
 * This class is used to generalize the way Fields are managed in the Monopoly game.
 * Common attributes are specified here (these attributes happen to be the basic attributes needed for GUI fields)
 * Every Field should have a name, description and some kind of action
 * <p>
 * Not all Fields have javadoc, since they are mostly self explanatory
 */
public abstract class Field {
    private String name;
    private String subText;
    private String description;


    // #----------Constructor----------#
    public Field(String name, String subText, String description){
        this.name = name;
        this.subText = subText;
        this.description = description;
    }

    /**
     * Every type of Field can implement a field action.
     * If nothing is implemented, basic logging takes place.
     * This would be considered a polymorphic call to execute.
     *
     * @param player that has landed on the field
     */
    public void fieldAction(Player player){
        player.setLastAction(player.getLastAction() + "\n - Er landet på " +
                this.getName());

        System.out.println("[INFO] " + player.getName() + " er landet på " +
                this.getName());
    }

    // #--------------Get--------------#
    public String getName(){
        return name;
    }

    public String getSubText(){
        return subText;
    }

    public String getDescription(){
        return description;
    }


}
