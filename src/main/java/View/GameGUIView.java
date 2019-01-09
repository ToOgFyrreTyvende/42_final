package View;

import gui_fields.*;
import gui_main.GUI;
import gui_resources.Attrs;
import Model.Fields.*;
import Model.Global;
import Model.GameBoard;
import Model.Player;

import java.awt.*;
import java.util.HashMap;

// Vi ved godt, at mange elementer i denne klasse er gentagende fra vores model
// Men vi har været nødt til at "tvinge" noget data ind i UIets format igennem denne klasse.

public class GameGUIView extends GameView {
    private GUI ui;
    private GUI_Field[] fields;

    private HashMap<Player, GUI_Player> players = new HashMap<>();

    @Override
    public void setGameBoard(GameBoard gameBoard) {
        super.setGameBoard(gameBoard);
        createViewBoard();
        this.ui = new GUI(fields);
    }

    public void setFields(GUI_Field[] fields){
        this.fields = fields;
    }


    @Override
    public int getPlayerCount() {
        return ui.getUserInteger("Hvor mange players?", Global.MIN_SPILLERE, Global.MAX_SPILLERE);
    }

    @Override
    public String getPlayerName(String text) {
        return ui.getUserString(text);
    }


    @Override
    public String getRoundChoice(String... choice) {
        return ui.getUserButtonPressed("Foretag venligst en handling.", choice);
    }

    @Override
    public String getRoundChoiceWithText(String tekst, String... choice) {
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
            
            this.players.put(players[i], tempSpillerGUI);
            ui.addPlayer(this.players.get(players[i]));
        }
    }

    @Override
    public void resetBoard() {
        this.players.forEach((playerModel, playerPiece) -> this.fields[0].setCar(playerPiece, true));
    }

    @Override
    public void setPlayerField(Player player, int field) {
        int fieldIndex = (field % Global.FIELD_COUNT) -1;

        GUI_Player playerGUI = this.players.get(player);

        this.fields[fieldIndex].setCar(playerGUI, true);
    }

    @Override
    public void setPlayerField(Player player, int field, int previousField) {

        GUI_Player playerGUI = this.players.get(player);
        GUI_Field _field = this.fields[previousField];

        if(_field.hasCar(playerGUI)){
            _field.setCar(playerGUI, false);
        }


        this.fields[field].setCar(playerGUI, true);
    }

    @Override
    public void setPlayerMoney(Player player, int money) {
        this.players.get(player).setBalance(money);
    }

    @Override
    public void renderPlayerData(Player player, int previousField) {
        setPlayerField(player, player.getField(), previousField);

        this.players.forEach((playerModel, playerPiece) ->
                                playerPiece.setBalance(playerModel.getMoney()));

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



    @Override
    public void createViewBoard() {
        GUI_Field[] fields = new GUI_Field[Global.FIELD_COUNT];
        Model.Fields.Field[] fieldModel = this.getGameBoard().getFields();

        for (int i = 0; i < Global.FIELD_COUNT; i++) {
            fields[i] = translateModel(fieldModel[i]);
        }

        this.setFields(fields);
    }



    public GUI_Field translateModel(Model.Fields.Field modelField){
        if (modelField instanceof PropertyField){
            return new GUI_Street(modelField.getName(), modelField.getSubText(),
                    modelField.getDescription(), ((PropertyField)modelField).getPrice() + "M",
                    ((PropertyField)modelField).getColor(), Color.black);
        }else if(modelField instanceof StartField){
            return new GUI_Start(modelField.getName(), modelField.getSubText(),
                    modelField.getDescription(), Color.red, Color.BLACK);
        }else if(modelField instanceof ChanceField){
            return new GUI_Chance("?", modelField.getSubText(), modelField.getDescription(),
                    Color.white, Color.black);
        }else if(modelField instanceof JailField){
            return new GUI_Jail("default", modelField.getName(), modelField.getSubText(),
                    modelField.getDescription(), Color.white, Color.BLACK);
        }else if(modelField instanceof ToJailField){
            return new GUI_Jail("default", modelField.getName(), modelField.getSubText(),
                    modelField.getDescription(), Color.white, Color.BLACK);
        }else if(modelField instanceof FreeParkingField){
            return new GUI_Refuge("default", modelField.getName(),
                    modelField.getSubText(), modelField.getDescription(), Color.white, Color.black);
        }else if(modelField instanceof CompanyField){
            if(((CompanyField)modelField).isShipping()){

                GUI_Shipping field = new GUI_Shipping(Attrs.getString("GUI_Field.Default_Picture",
                        new Object[0]), modelField.getName(), modelField.getSubText(),
                        modelField.getDescription(), "" + ((CompanyField)modelField).getPrice(), Color.white, ((CompanyField)modelField).getColor());
                return field;
            }else{
                GUI_Brewery field = new GUI_Brewery(Attrs.getString("GUI_Field.Default_Picture", new Object[0]),
                        modelField.getName(), modelField.getSubText(),
                        modelField.getDescription(), "" + ((CompanyField)modelField).getPrice(), Color.white, ((CompanyField)modelField).getColor());
                return field;
            }
        }


        return null;

    }
}
