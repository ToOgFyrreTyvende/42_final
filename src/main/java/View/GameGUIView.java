package View;

import Model.Global;
import Model.GameBoard;
import Model.Player;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.util.HashMap;

// Vi ved godt, at mange elementer i denne klasse er gentagende fra vores model
// Men vi har været nødt til at "tvinge" noget data ind i UIets format igennem denne klasse.

public class GameGUIView extends GameView {
    private GUI ui;
    private GUI_Field[] felter;

    private HashMap<Player, GUI_Player> spillere = new HashMap<>();

    @Override
    public void setGameBoard(GameBoard gameBoard) {
        super.setGameBoard(gameBoard);
        this.felter = getGameBoard().getFelterGUI();
        this.ui = new GUI(felter);
    }

    @Override
    public int getPlayerCount() {
        return ui.getUserInteger("Hvor mange spillere?", Global.MIN_SPILLERE, Global.MAX_SPILLERE);
    }

    @Override
    public String getPlayerName(String text) {
        return ui.getUserString(text);
    }


    @Override
    public String getRundeValg(String... choice) {
        return ui.getUserButtonPressed("Foretag venligst en handling.", choice);
    }

    @Override
    public String getRundeValgMedTekst(String tekst, String... choice) {
        return ui.getUserButtonPressed(tekst, choice);
    }

    @Override
    public void setPlayers(Player... players) {
        Color[] farver = {Color.blue, Color.red, Color.yellow, Color.green, Color.black, Color.magenta};

        for (int i = 0; i < players.length; i++){

            GUI_Car tempcar = new GUI_Car();
            tempcar.setPrimaryColor(farver[i]);

            GUI_Player tempSpillerGUI = new GUI_Player(players[i].getName(),
                                        players[i].getMoney(), tempcar);
            
            this.spillere.put(players[i], tempSpillerGUI);
            ui.addPlayer(this.spillere.get(players[i]));
        }
    }

    @Override
    public void resetBoard() {
        this.spillere.forEach((spillerModel, spillerBrik) -> this.felter[0].setCar(spillerBrik, true));
    }

    @Override
    public void setPlayerField(Player player, int field) {
        int feltIndex = (field % 24) -1;

        GUI_Player spillerGUI = this.spillere.get(player);

        this.felter[feltIndex].setCar(spillerGUI, true);
    }

    @Override
    public void setPlayerField(Player player, int felt, int previousField) {

        GUI_Player spillerGUI = this.spillere.get(player);
        GUI_Field field = this.felter[previousField];

        if(field.hasCar(spillerGUI)){
            field.setCar(spillerGUI, false);
        }


        this.felter[felt].setCar(spillerGUI, true);
    }

    @Override
    public void setPlayerMoney(Player player, int money) {
        this.spillere.get(player).setBalance(money);
    }

    @Override
    public void renderPlayerData(Player player, int previousField) {
        setPlayerField(player, player.getField(), previousField);

        this.spillere.forEach((spillerModel, spillerBrik) ->
                                spillerBrik.setBalance(spillerModel.getMoney()));

    }

    @Override
    public void endText(String text) {
        this.ui.getUserButtonPressed(text, "Afslut");
    }

    @Override
    public void setDice(int result) {
        this.ui.setDice(1,2,1, result,2,1);
    }


    @Override
    public void setCenterText(String text) {
        ui.displayChanceCard(text);
    }
}
