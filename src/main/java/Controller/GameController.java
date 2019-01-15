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
    public DiceController diceController;
    private EndTurnController endTurnController;

    private Controller currentController;
    private String[] currentGameMenu;

    
    // #----------Constructor----------#
    public GameController(GameBoard board, GameView view){
        this.gameBoard = board;
        this.view = view;
        this.view.setGameBoard(this.gameBoard);

        this.jailController = new JailController(this);
        this.propertyController = new PropertyController(this);
        this.userChoiceController = new UserChoiceController(this);
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
                playerNames[i] = (this.view.getPlayerName("Indtast venligst " + (i+1) + ". spillers navn."));
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
            boolean turnOver = false;
            game.setupNextPlayer();
            while (!turnOver) {
                if (activePlayer.isInJail()){
                    currentController = jailController;
                }
                currentGameMenu = currentController.getMenuActions();
                //System.out.println(currentController.getClass().getSimpleName());
                String action;
                if (currentController.isDropdown()){
                    action = view.getRoundChoiceDropDownWithText(activePlayer.getName()+ "'s tur. Vælg venligst fra listen", currentGameMenu);
                }else{
                    action = view.getRoundChoiceWithText(activePlayer.getName()+ "'s tur. Vælg venligst en handling", currentGameMenu);
                }
                String result = currentController.handleActions(action);

                if (result.equals("Afslut tur") || result.equals("Spring over")) {
                    turnOver = true;
                    activePlayer = game.getActivePlayer();
                    continue;
                }else if (result.equals("Jail Rul terning")){
                    getGame().endPlayerTurn();
                    turnOver = true;
                    activePlayer = game.getActivePlayer();
                    continue;
                }


                renderBuilding();
                playerInfoUpdate(activePlayer);

                if(currentController == userChoiceController ||
                        currentController == propertyController ){
                    currentController = propertyController;
                    continue;
                }

                String fieldTypeString = game.getPlayerFieldType(activePlayer);
                switch (fieldTypeString) {
                    case "PropertyField":
                        currentController = propertyController;
                        propertyController.setMenuActions(PropertyController.PropertyActions);
                        break;

                    /*case "CompanyField":
                        currentController = propertyController;
                        propertyController.setMenuActions(PropertyController.PropertyActions);
                        break;
                    /*case "PropertyFieldOwned":
                        currentController = endTurnController;
                        currentGameMenu = EndTurnController.EndActions;
                        break;*/

                    case "TaxFieldChoice":
                        currentController = userChoiceController;
                        currentGameMenu = UserChoiceController.UserChoiceActions;
                        break;

                    default:
                        currentController = propertyController;
                        break;
                }

            }
            if (this.game.isEnded()){
                view.setCenterText("SPILLET ER AFSLUTTET\nVinderen er: " +
                        this.game.getWinner().getName());
                view.endText("spillet er slut!");
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
        this.propertyController.setMenuActions(buildPropertyMenu());
        this.propertyController.setChosenField(null);
        this.propertyController.setDropdown(false);
    }

    public String[] buildPropertyMenu(){
        if (getGame().getActivePlayer() != null &&
                getGameBoard().getPlayerProperties(
                        getGame().getActivePlayer()).length > 0){
            return PropertyController.PropertyManagementActions;
        }else{
            return new String[]{PropertyController.PropertyManagementActions[1]};
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