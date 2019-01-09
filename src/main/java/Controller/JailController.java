package Controller;

import Model.Global;

public class JailController {
    public final String[] JailActions = new String[]{
            "Betal 1000",
            "Rul terning",
            "Brug frikort"
    };

    public void handleActions(String action){
        switch(action){
            case "Betal 1000":
                payBail();
                break;
            case "Rul terning":
                feelingLucky();
                break;
            case "Brug frikort":
                bailCard();
                break;
            default:
            // Indsæt auktion funktionalitet?
                break;
            }
    }


    public void payBail(){

    }

    public void feelingLucky(){

    }

    public void bailCard(){

    }

}
