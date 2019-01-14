package Controller;

import Model.Game;

abstract public class Controller {
    private String[] menuActions;
    protected GameController gameController;

    public Controller(GameController game, String[] menuActions) {
        this.menuActions = menuActions;
        this.gameController = game;
    }

    abstract String handleActions(String action);

    public String[] getMenuActions() {
        return menuActions;
    }

    public void setMenuActions(String[] menuActions) {
        this.menuActions = menuActions;
    }
}

