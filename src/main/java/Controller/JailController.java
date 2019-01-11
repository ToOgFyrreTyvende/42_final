package Controller;

import Model.Global;
import Model.Player;

public class JailController {
    public final String[] JailActions = new String[]{
            "Betal 1000",
            "Rul terning",
            "Brug frikort"
    };

    private int bailPrice = 1000;


    public void handleActions(String action, Player player){
        switch(action){
            case "Betal 1000":
                payBail(player);
                break;
            case "Rul terning":
                feelingLucky(player);
                break;
            case "Brug frikort":
                bailCard(player);
                break;
            default:
            // Indsæt auktion funktionalitet?
                break;
            }
    }


    public void payBail(Player player){
        player.addMoney( - bailPrice);

        player.setLastAction(player.getLastAction() + "\n - har betalt 1000 kr for at komme ud af faengsel.");
        System.out.println("[INFO] " + player.getName() + " Har betalt 1000 kr for at komme ud af faengsel.");
    }

    public void feelingLucky(Player player){

    }

    public void bailCard(Player player){

    }

}
