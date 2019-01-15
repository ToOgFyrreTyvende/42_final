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

    PropertyField chosenField = null;

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
            case "Køb hus":
                buyBuilding(0);
                newMenu = BuySellBuildingActions;
                break;
            case "Køb hotel":
                buyBuilding(1);
                newMenu = BuySellBuildingActions;
                break;
            case "Sælg hus":
                sellBuilding(0);
                newMenu = BuySellBuildingActions;
                break;
            case "Sælg hotel":
                sellBuilding(1);
                newMenu = BuySellBuildingActions;
                break;

            default:
            //Vi antager, at andre menu elementer svarer til, at man har valgt en ejendom fra drop-down menuen
                setChosenField(gameController.getGame().getGameBoard().getPropertyFieldByName(action));
                newMenu = BuySellBuildingActions;
                setDropdown(false);
                break;
        }

        super.setMenuActions(newMenu);
        return action;
    }



    public String[] buildingMenu(){
        return gameController.getGame().getGameBoard().getPlayerPropertyNames(gameController.getGame().getActivePlayer());
        // return BuyBuildingActions;
    }

    //type: 0 -> house, 1 -> hotel

    // If player owns prop -> player pays >:3
    public void buyBuilding(int type){
        if (type == 0){
            chosenField.buyHouse();
        }else{
            chosenField.buyHotel();
        }
    }

    public void sellBuilding(int type){
        if (type == 0){

        }else{

        }
    }

    public Field getChosenField() {
        return chosenField;
    }

    public void setChosenField(PropertyField chosenField) {
        this.chosenField = chosenField;
    }
}

