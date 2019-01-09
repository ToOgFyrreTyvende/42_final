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
        spillerTur(game.getActivePlayer());

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


    private void spillerTur(Player player){
        view.getRundeValgMedTekst(player.getName() + "'s tur. Rul venligst terningen.", "Rul terning");

        int forrigeFelt = player.getField();

        Player muligPlayer = game.playTurn();

        if (muligPlayer != null && !this.game.isEnded()){
            opdaterUIspiller(muligPlayer, forrigeFelt);
            view.setDice(muligPlayer.getLastDiceResult());
            view.setCenterText(muligPlayer.toString());
            muligPlayer.setChanceCard(null);
            muligPlayer.setLastAction("");

            spillerTur(game.getActivePlayer());
        }else {
            view.renderPlayerData(player, forrigeFelt);
            view.setCenterText("SPILLET ER AFSLUTTET\nVinderen er: " +
                    this.game.getWinner().getName());
            view.endText("spillet er slut!");
        }
    }

    private void opdaterUIspiller(Player player, int forrigeFelt){
        view.renderPlayerData(player, forrigeFelt);
    }

    Game getGame() {
        return game;
    }
}