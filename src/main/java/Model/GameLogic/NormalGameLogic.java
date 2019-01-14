package Model.GameLogic;

import Model.Game;
import Model.GameBoard;
import Model.Player;

public class NormalGameLogic extends GameLogic{

    public NormalGameLogic(Game game) {
        super(game);
    }

    @Override
    public Player BeforeTurn() {
        return null;
    }

    @Override
    public Player PlayTurn() {
        return null;
    }

    @Override
    public int AfterTurn() {
        return 0;
    }
}
