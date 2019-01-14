package Controller;

import Model.Game;
import Model.GameBoard;
import Model.Player;
import View.GameView;

import java.util.Arrays;

public class GameController {
    public final String[] DefaultActions = new String[]{"Rul terning"};

    private Game game;
    private GameView view;
    private GameBoard gameBoard;
    private JailController jailController;
    private PropertyController propertyController;
    private UserChoiceController userChoiceController;

    
    // #----------Constructor----------#
    public GameController(GameBoard board, GameView view){
        this.gameBoard = board;
        this.view = view;
        this.view.setGameBoard(this.gameBoard);

        this.jailController = new JailController();
        this.propertyController = new PropertyController();
        this.userChoiceController = new UserChoiceController();

        initalizeGame();
        playerTurn(game.getActivePlayer());
    }

    private void initalizeGame(){
        // pga. abstrakt klasse, har vi polymorfi, og kan kalde "getantalspillere" for ethvert view
        int playerAmount = this.view.getPlayerCount();
        String[] playerNames = new String[playerAmount];

        for (int i = 0; i < playerAmount; i++) {
            if (i==0){
                playerNames[i] = (this.view.getPlayerName("Indtast venligst første, yngste spillers name."));

            }else{
                playerNames[i] = (this.view.getPlayerName("Indtast venligst " + (i+1) + ". spillers name."));
            }
        }

        game = new Game(this.gameBoard, playerNames);
        this.view.setPlayers(game.getPlayers());
        this.view.resetBoard();
    }


    private void playerTurn(Player player){

        Player activePlayer = player;
        while(!this.game.isEnded()){
            view.getRoundChoiceWithText(activePlayer.getName() + "'s tur. Rul venligst terningen.", "Rul terning");

            int previousField = activePlayer.getField();

            activePlayer = game.playTurn();

            if (activePlayer != null && !this.game.isEnded()){
                updateUIPlayer(activePlayer, previousField);
                view.setDice(activePlayer.getLastDicePair());
                view.setCenterText(activePlayer.toString());
                activePlayer.setChanceCard(null);
                activePlayer.setLastAction("");

                activePlayer = game.getActivePlayer();
            }else {
                view.renderPlayerData(activePlayer, previousField);
                view.setCenterText("SPILLET ER AFSLUTTET\nVinderen er: " +
                        this.game.getWinner().getName());
                view.endText("spillet er slut!");
            }
        }
    }

    private void updateUIPlayer(Player player, int previousField){
        view.renderPlayerData(player, previousField);
    }

    Game getGame() {
        return game;
    }
}