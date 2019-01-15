package Controller;

public class EndTurnController extends Controller{
    public static final String[] EndActions = new String[]{"Afslut Tur"};

    public EndTurnController(GameController game) {
        super(game, EndActions);
    }

    @Override
    String handleActions(String action) {

        switch (action){
            case "Afslut Tur":
                gameController.getGame().endPlayerTurn();
                break;
            default:
                break;
        }
        return "Afslut Tur";
    }

}
