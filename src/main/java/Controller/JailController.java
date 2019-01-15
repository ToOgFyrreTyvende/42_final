package Controller;

import Model.Global;
import Model.Player;

public class JailController extends Controller{
    public static final String[] JailActions = new String[]{
            "Betal 1000 kr.",
            "Rul terning",
            "Brug løsladelseskort"
    };

    public JailController(GameController gameController) {
        super(gameController, JailActions);
    }

    @Override
    public String handleActions(String action){
        switch(action){
            case "Betal 1000 kr.":
                payBail(gameController.getGame().getActivePlayer());
                break;
            case "Rul terning":
                feelingLucky(action, gameController.getGame().getActivePlayer());
                return "Jail Rul terning";
                //break;
            case "Brug løsladelseskort":
                bailCard(action, gameController.getGame().getActivePlayer());
                break;
            default:
            // Indsæt auktion funktionalitet?
                break;
            }

            return action;
    }


    public void payBail(Player player){
        player.addMoney( -Global.JAIL_PRICE);

        player.setLastAction(player.getLastAction() + "\n - Har betalt 1000 kr for at komme ud af fængsel.");
        System.out.println("[INFO] " + player.getName() + " har betalt 1000 kr for at komme ud af fængsel.");
        player.setInJail(false);
        gameController.setCurrentController(gameController.diceController);
    }

    public void feelingLucky(String action,Player player){
        int[] diceThrow = new int[Global.DICE_AMOUNT +1];
        for (int i = 0 ; i < Global.DICE_AMOUNT ; i++){
            float _random3 = (float) Math.random();    // 0-1 float
            int _random4 = (int) (_random3 * Global.DICE_SIDES);   // 0-5 integer
            int random5 = _random4 + 1;                 // 1-6 integer
            diceThrow[i] = random5;
        }

        if(diceThrow[0] == diceThrow[1]){
            player.setLastAction(player.getLastAction() + "\n - Slog " + diceThrow[0] + " to gange og kom ud af fængsel.");
            System.out.println("[INFO] " + player.getName() + " har slået " + diceThrow[0] + " to gange og kom ud af fængsel.");
            player.setInJail(false);

            gameController.getGame().setDice(diceThrow,diceThrow[0] + diceThrow[1] );
            gameController.getGame().throwDice(true);
            gameController.playerInfoUpdate(gameController.getGame().getActivePlayer());

        }
        else if (diceThrow[0] != diceThrow[1]){
            player.setLastAction(player.getLastAction() + "\n - Slog ikke to ens og kom ud af fængsel.");
            System.out.println("[INFO] " + player.getName() + " har slået "+ diceThrow[0]+ " og " + diceThrow[1] + " så de kom ikke ud af fængsel.");
        } else {
            System.out.println("Something went wrong");
            handleActions(action);
        }

    }

    public void bailCard(String action,Player player){
        if(player.isOutOfJailFree()){
            player.setLastAction(player.getLastAction() + "\n - Har brugt sit løsladelses chancekort.");
            System.out.println("[INFO] " + player.getName() + " kom ud af fængslet med deres 'frikort'");
            player.setInJail(false);
            player.setOutOfJailFree(false);
        } else {
            System.out.println("[INFO] " + player.getName() + " har ikke et 'frikort' saa du kan ikke goere dette.");
            // JailController();
        }
    }
}
