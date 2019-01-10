package Model.GameLogic;

import Model.*;

public abstract class GameLogic {

    private String memes;

    GameLogic(){
    }

    
    abstract public Player BeforeTurn();
    abstract public Player PlayTurn();
    abstract public int AfterTurn();

    
    
    

    
    }
}