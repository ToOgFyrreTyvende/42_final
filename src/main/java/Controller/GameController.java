package Controller;

import Model.Game;
import Model.GameBoard;
import Model.Player;
import View.GameView;

public class GameController {
    private Game game;
    private GameView view;
    private GameBoard gameBoard;
    
    // #----------Constructor----------#
    public GameController(GameBoard braet, GameView view){
        this.gameBoard = braet;
        this.view = view;
        this.view.setGameBoard(this.gameBoard);

        initalizeGame();
        playerTurn(game.getActivePlayer());

    }

    private void initalizeGame(){
        // pga. abstrakt klasse, har vi polymorfi, og kan kalde "getantalspillere" for ethvert view
        int spillerAntal = this.view.getPlayerCount();
        String[] spillerNavne = new String[spillerAntal];

        for (int i = 0; i < spillerAntal; i++) {
            if (i==0){
                spillerNavne[i] = (this.view.getPlayerName("Indtast venligst første, yngste spillers name."));

            }else{
                spillerNavne[i] = (this.view.getPlayerName("Indtast venligst " + (i+1) + ". spillers name."));
            }
        }

        game = new Game(this.gameBoard, spillerNavne);
        this.view.setPlayers(game.getPlayers());
        this.view.resetBoard();
    }


    private void playerTurn(Player player){

        Player activePlayer = player;
        while(!this.game.isEnded()){
            view.getRoundChoiceWithText(activePlayer.getName() + "'s tur. Rul venligst terningen.", "Rul terning");

            int forrigeFelt = activePlayer.getField();

            Player nextPlayer = game.playTurn();

            if (nextPlayer != null && !this.game.isEnded()){
                updateUIPlayer(nextPlayer, forrigeFelt);
                view.setDice(nextPlayer.getLastDiceResult());
                view.setCenterText(nextPlayer.toString());
                nextPlayer.setChanceCard(null);
                nextPlayer.setLastAction("");

                activePlayer = game.getActivePlayer();
            }else {
                view.renderPlayerData(activePlayer, forrigeFelt);
                view.setCenterText("SPILLET ER AFSLUTTET\nVinderen er: " +
                        this.game.getWinner().getName());
                view.endText("spillet er slut!");
            }
        }
    }

    private void updateUIPlayer(Player player, int forrigeFelt){
        view.renderPlayerData(player, forrigeFelt);
    }

    Game getGame() {
        return game;
    }
}