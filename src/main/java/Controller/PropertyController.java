package Controller;

import Model.Fields.Field;
import Model.Fields.PropertyField;
import Model.Player;


public class PropertyController {
    public final String[] PropertyActions = new String[]{
        "Køb bygninger",
        "Sælg bygninger"
    };

    public final String[] BuyBuildingActions = new String[]{
        "Køb hus",
        "Køb hotel",
        "Tilbage"
    };

    public final String[] SellBuildingActions = new String[]{
        "Sælg hus",
        "Sælg hotel",
        "Tilbage"
    };

    // A menu is a list of strings that will be parsed in the switch/cases beneath
    public String[] getMenu(Player player, Field field){

        // Is this a property field, and does the player own it?
        if(field instanceof PropertyField && ((PropertyField) field).getOwner() == player){
            return PropertyActions;
        }

        return new String[]{};

    }


    public String[] handleActions(String action){
        String[] newMenu = new String[]{"FEJL", action};

        switch(action){
            // case "Køb ejendom":
            //     newMenu = buyProperty();
            //     break;
            case "Køb bygninger":
                newMenu = buyBuildingMenu();
                break;
            case "Sælg bygninger":
                newMenu = sellBuildingMenu();
                break;
            default:
            // Indsæt auktion funktionalitet?
                break;
            }
        return newMenu;
    }

    public void handlePropertyAction(String action, Field field){
        switch(action){
            case "Køb hus":
                buyBuilding(0, field);
                break;
            case "Køb hotel":
                buyBuilding(1, field);
                break;
            case "Sælg hus":
                sellBuilding(0, field);
                break;
            case "Sælg hotel":
                sellBuilding(1, field);
                break;
            case "Tilbage":
                menuBack();
                break;

            default:
            // Indsæt (eller evt yoink) auktion funktionalitet?
                break;
        }
    }


    private void menuBack() {

    }

    public void buyProperty() {

    }

    public String[] buyBuildingMenu(){
        return BuyBuildingActions;
    }

    public String[]  sellBuildingMenu(){
        return SellBuildingActions;
    }

    //type: 0 -> house, 1 -> hotel

    // If player owns prop -> player pays >:3
    public void buyBuilding(int type, Field field){

    }

    public void sellBuilding(int type, Field field){

    }
    
}

