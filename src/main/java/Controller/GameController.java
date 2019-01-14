package Controller;

import Model.Fields.PropertyField;
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
        while(!this.game.isEnded()){
            resetControllers();
            currentController = diceController;
            game.setupNextPlayer();
            boolean turnOver = false;
            while (!turnOver) {
                currentGameMenu = currentController.getMenuActions();
                System.out.println(currentController.getClass().getSimpleName());
                String action;
                if (currentController.isDropdown()){
                    action = view.getRoundChoiceDropDownWithText("Vælg venligst fra listen", currentGameMenu);
                }else{
                    action = view.getRoundChoiceWithText("Vælg venligst en handling", currentGameMenu);
                }
                String result = currentController.handleActions(action);
                currentController = endTurnController;

                if (result.equals("Afslut Tur")) {
                    turnOver = true;
                    activePlayer = game.getActivePlayer();
                    continue;
                }

                renderBuilding();

                String fieldTypeString = game.getPlayerFieldType(activePlayer);
                switch (fieldTypeString) {
                    case "PropertyField":
                        System.out.println("In propfield case");

                        currentController = propertyController;
                        currentGameMenu = PropertyController.PropertyActions;
                        break;
                    case "PropertyFieldOwned":
                        currentController = endTurnController;
                        currentGameMenu = EndTurnController.EndActions;
                        break;
                    case "PropertyFieldMe":
                        currentController = propertyController;
                        currentGameMenu = PropertyController.PropertyManagementActions;
                        break;

                    default:
                        /*turnOver = true;
                        activePlayer = game.getActivePlayer();
                        currentController = endTurnController;*/
                        break;
                }

                if (this.game.isEnded()){
                    view.setCenterText("SPILLET ER AFSLUTTET\nVinderen er: " +
                            this.game.getWinner().getName());
                    view.endText("spillet er slut!");
                }
            }

                /*if (!activePlayer.isInJail()){
                    game.throwDice();
                    playerInfoUpdate(activePlayer);

                }else{
                    currentGameMenu = jailController.JailActions;
                }*/
           }
    }

    private void resetControllers() {
        this.propertyController.setMenuActions(PropertyController.PropertyActions);
        this.propertyController.setChosenField(null);
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

    public Controller getCurrentController() {
        return currentController;
    }

    public void setCurrentController(Controller currentController) {
        this.currentController = currentController;
    }

    public String[] getCurrentGameMenu() {
        return currentGameMenu;
    }

    public void setCurrentGameMenu(String[] currentGameMenu) {
        this.currentGameMenu = currentGameMenu;
    }

    public void renderBuilding() {
        view.renderBuildings();
    }
}