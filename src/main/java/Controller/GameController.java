package Controller;

import Model.Game;
import Model.GameBoard;
import Model.Player;
import View.GameView;

public class GameController {
    private Game game;
    private GameView view;
    private GameBoard gameBoard;
    private JailController jailController;
    private PropertyController propertyController;
    private UserChoiceController userChoiceController;
    private DiceController diceController;
    private EndTurnController endTurnController;

    private Controller currentController;
    private String[] currentGameMenu;

    
    // #----------Constructor----------#
    public GameController(GameBoard board, GameView view){
        this.gameBoard = board;
        this.view = view;
        this.view.setGameBoard(this.gameBoard);

       // this.jailController = new JailController(game);
        this.propertyController = new PropertyController(this);
       // this.userChoiceController = new UserChoiceController(game);
        this.diceController = new DiceController(this);
        this.endTurnController = new EndTurnController(this);

        initalizeGame();
        currentController = diceController;
        testPlayTurn(game.getActivePlayer());
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

    private void testPlayTurn(Player player){
        Player activePlayer = player;
        game.setupNextPlayer();
        boolean turnOver = false;
        while (!turnOver){
            currentGameMenu = currentController.getMenuActions();
            String action = view.getRoundChoiceWithText("Vælg venligst en handling", currentGameMenu);
            currentController.handleActions(action);

            String fieldTypeString = game.getPlayerFieldType(activePlayer);
            System.out.println(fieldTypeString);
            switch (fieldTypeString){
                case "PropertyField":
                    currentController = propertyController;
                    currentGameMenu = propertyController.PropertyActions;
                    break;
                case "PropertyFieldOwned":
                    currentController = endTurnController;
                    currentGameMenu = endTurnController.EndActions;
                    break;
                case "PropertyFieldMe":
                    currentController = propertyController;
                    currentGameMenu = propertyController.PropertyManagementActions;
                    break;
            }

            /*if (!activePlayer.isInJail()){
                game.throwDice();
                playerInfoUpdate(activePlayer);

            }else{
                currentGameMenu = jailController.JailActions;
            }*/
       }
    }

    public void playerInfoUpdate(Player player){
        updateUIPlayer(player, player.getPreviousField());
        view.setDice(player.getLastDicePair());
        view.setCenterText(player.toString());
        player.setChanceCard(null);
        player.setLastAction("");
    }

    public void buyFieldPlayerIsOn(Player player){
        game.buyFieldPlayerIsOn(player);
    }

    /*

     game.endPlayerTurn();

                if (this.game.isEnded()){
                    view.renderPlayerData(activePlayer, activePlayer.getPreviousField());
                    view.setCenterText("SPILLET ER AFSLUTTET\nVinderen er: " +
                            this.game.getWinner().getName());
                    view.endText("spillet er slut!");
                }

     */

   /* private void playerTurn(Player player){

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
   */

    private void updateUIPlayer(Player player, int previousField){
        view.renderPlayerData(player, previousField);
    }

    Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameView getView() {
        return view;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}