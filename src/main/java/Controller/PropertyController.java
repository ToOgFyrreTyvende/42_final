package Controller;

import Model.Fields.Field;
import Model.Fields.PropertyField;


public class PropertyController extends Controller {
    public static final String[] PropertyActions = new String[]{
            "Køb ejendom",
            "Spring over"
            //"Afslut Tur"
    };


    public static final String[] PropertyManagementActions = new String[]{
            "Køb/Sælg bygninger",
            "Afslut tur"
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
        super(gameController, PropertyManagementActions);
    }

    @Override
    public String handleActions(String action){
        String[] newMenu = new String[]{"Afslut Tur"};

        switch(action){
            case "Afslut tur":
            case "Spring over":
                gameController.getGame().endPlayerTurn();
                break;

            case "Køb ejendom":
                newMenu = PropertyManagementActions;
                gameController.buyFieldPlayerIsOn(gameController.getGame().getActivePlayer());
                break;
            case "Køb/Sælg bygninger":
                newMenu = buildingNameMenu();
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


    public String[] buildingNameMenu(){
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
            chosenField.sellHouse();
        }else{
            chosenField.sellHotel();
        }
    }

    public Field getChosenField() {
        return chosenField;
    }

    public void setChosenField(PropertyField chosenField) {
        this.chosenField = chosenField;
    }
}

