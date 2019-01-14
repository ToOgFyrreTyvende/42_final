package Controller;

import Model.Game;

public class DiceController extends Controller{
    public static final String[] DefaultActions = new String[]{"Rul terning"};

    public DiceController(GameController gameController) {
        super(gameController, DefaultActions);
    }

    @Override
    String handleActions(String action) {
        gameController.getGame().throwDice();
        gameController.playerInfoUpdate(gameController.getGame().getActivePlayer());
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
