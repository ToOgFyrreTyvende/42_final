package Controller;

import Model.Fields.Field;
import Model.Fields.PropertyField;
import Model.Player;


public class PropertyController extends Controller {
    public static final String[] PropertyActions = new String[]{
            "Køb ejendom",
            "Sæt til auktion"
            //"Afslut Tur"
    };

    public static final String[] PropertyManagementActions = new String[]{
            "Køb bygninger",
            "Sælg bygninger",
            "Afslut Tur"
    };

    public static final String[] BuyBuildingActions = new String[]{
        "Køb hus",
        "Køb hotel",
        "Tilbage"
    };

    public static final String[] SellBuildingActions = new String[]{
        "Sælg hus",
        "Sælg hotel",
        "Tilbage"
    };

    // A menu is a list of strings that will be parsed in the switch/cases beneath
    public String[] getMenu(Player player, Field field){

        // Is this a property field, and does the player own it?
        if(field instanceof PropertyField && ((PropertyField) field).getOwner() == player){
            return PropertyManagementActions;
        }

        return new String[]{};

    }

    public PropertyController(GameController gameController) {
        super(gameController, PropertyActions);
    }

    @Override
    public String handleActions(String action){
        String[] newMenu = new String[]{"FEJL", action};

        switch(action){
            case "Afslut Tur":
            case "Sæt til auktion":
                gameController.getGame().endPlayerTurn();
                break;

            case "Køb ejendom":
                newMenu = PropertyManagementActions;
                gameController.buyFieldPlayerIsOn(gameController.getGame().getActivePlayer());
                break;

            case "Køb bygninger":
                newMenu = buyBuildingMenu();
                break;
            case "Sælg bygninger":
                newMenu = sellBuildingMenu();
                break;
            case "Tilbage":
                newMenu = PropertyManagementActions;
                break;
            default:
            // Indsæt auktion funktionalitet?
                break;
        }

        super.setMenuActions(newMenu);
        return action;
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

