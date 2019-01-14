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
            "Køb/Sælg bygninger",
            "Afslut Tur"
    };

    public static final String[] BuySellBuildingActions = new String[]{
        "Køb hus",
        "Køb hotel",
        "Sælg hus",
        "Sælg hotel",
        "Tilbage"
    };

    Field chosenField = null;

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

            case "Køb/Sælg bygninger":
                newMenu = buildingMenu();
                setDropdown(true);
                break;
            case "Tilbage":
                newMenu = PropertyManagementActions;
                break;
            default:
            //Vi antager, at andre menu elementer svarer til, at man har valgt en ejendom fra drop-down menuen
                setChosenField(gameController.getGame().getGameBoard().getFieldByName(action));
                newMenu = BuySellBuildingActions;
                setDropdown(false);
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
                break;

            default:
            // Indsæt (eller evt yoink) auktion funktionalitet?
                break;
        }
    }



    public String[] buildingMenu(){
        return gameController.getGame().getGameBoard().getPlayerPropertyNames(gameController.getGame().getActivePlayer());
        // return BuyBuildingActions;
    }

    //type: 0 -> house, 1 -> hotel

    // If player owns prop -> player pays >:3
    public void buyBuilding(int type, Field field){

    }

    public void sellBuilding(int type, Field field){

    }

    public Field getChosenField() {
        return chosenField;
    }

    public void setChosenField(Field chosenField) {
        this.chosenField = chosenField;
    }
}

