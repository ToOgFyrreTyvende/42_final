package Controller;

import Model.Game;

public class DiceController extends Controller{
    public static final String[] DefaultActions = new String[]{"Rul terning"};

    public DiceController(GameController gameController) {
        super(gameController, DefaultActions);
    }

    @Override
    String handleActions(String action) {
        if(gameController.getGame().getActivePlayer().isLucky()){
            gameController.getGame().getActivePlayer().setLucky(false);
            gameController.getGame().setDice(gameController.getGame().getActivePlayer().getLastDicePair(),gameController.getGame().getActivePlayer().getLastDiceResult() );
            gameController.getGame().throwDice(true);
            gameController.playerInfoUpdate(gameController.getGame().getActivePlayer());
            return DefaultActions[0];
        }
        //gameController.getGame().setDice(new int[]{3, 3}, 6);
        gameController.getGame().throwDice(false);
        gameController.updateDice(gameController.getGame().getActivePlayer());
        return DefaultActions[0];
    }


    @Override
    public String[] getMenuActions() {
        return super.getMenuActions();
    }

    @Override
    public void setMenuActions(String[] menuActions) {
        super.setMenuActions(menuActions);
    }
}
