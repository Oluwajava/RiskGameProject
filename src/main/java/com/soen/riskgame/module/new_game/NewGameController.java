package com.soen.riskgame.module.new_game;

import com.soen.riskgame.module.core.command.LoadMapCommand;
import com.soen.riskgame.module.core.command_line.CommandSytanxTree;
import com.soen.riskgame.module.core.command_line.Lexer;
import com.soen.riskgame.module.core.command_line.Token;
import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.interfaces.PlayerCommandListener;
import com.soen.riskgame.module.core.interfaces.View;
import com.soen.riskgame.module.core.model.Map;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.FileReader;
import com.soen.riskgame.module.core.utils.MapParser;
import com.soen.riskgame.module.core.utils.MapValidator;
import com.soen.riskgame.module.game_play.GamePlayController;
import com.soen.riskgame.module.map_selector.MapListController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

//import com.soen.riskgame.module.map_selector.MapListController;


/**
 * Class represents the start of the new game. Calss implements methods of View(GUI)
 * and command(player command and load map command) amd also the observer
 * new start start by calling this class, which selects the players(add and remove) , updates and binds the view
 * @author Mayokun
 */
public class NewGameController implements View, PlayerCommandListener, LoadMapCommand.LoadMapListener, Observer {

    /**
     * name of the new games
     */
    private final String NEW_GAME = "/view/new-game.fxml";
    /**
     * variable of new game scne view
     */
    private final Scene scene;
    /**
     * variable for choose map button
     */
    private Button chooseMap;
    /**
     * variable for Start Game button
     */
    private Button startGame;

    private List<String> playersList = new ArrayList<>();
    private ImageView mapPreviewImage;
    private ListView playerListView;
    private Button removePlayerButton;
    private TextField playerName;
    private TextArea commandLine;
    private ColorPicker playerColor;
    private Button addPlayerButton;
    private Button processCommandButton;
    private String selectedPlayer;
    private MapData mapData;

    public NewGameController() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NEW_GAME));
        scene = new Scene(root, 980, 740);
        bindView();

        chooseMap.setOnAction(event -> {
            try {
                MapListController mapListController = new MapListController(name -> {
                    System.out.println(name);
                    FileReader fileReader = new FileReader("C:\\Users\\Admin\\APP\\RiskGameProject\\src\\main\\resources\\maps\\0" + name + ".map");
                    String mapData = fileReader.readData().replaceAll(MapDelimiters.CARRIAGE_DELIMITER, "");
                    MapValidator mapValidator = new MapValidator(mapData);
                    mapValidator.validate();
                    MapParser mapParser = new MapParser(mapData);
                    this.mapData = new Map.Builder(mapParser.getGameFile(), mapParser.getCountries(), mapParser.getContinentDTOS(), mapParser.getBorderDTOS()).build();
                    this.mapData.addObserver(this);
                    updateImage(name);
                });
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                try {
                    stage.setScene(mapListController.getView());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        startGame.setOnAction(event -> {
            try {
                playersList.forEach(mapData::addPlayer);
                GamePlayController gamePlayController = new GamePlayController(mapData);
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                try {
                    stage.setScene(gamePlayController.getView());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        playerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedPlayer = String.valueOf(newValue);
        });

        removePlayerButton.setOnAction(event -> {
            removePlayer(selectedPlayer);
        });

        addPlayerButton.setOnAction(event -> {
            addPlayer(playerName.getText());
        });

        processCommandButton.setOnAction(event -> {
            String command = commandLine.getText();
            List<Token> tokens = Lexer.lex(command);
            CommandSytanxTree commandSytanxTree = new CommandSytanxTree(null, tokens);
            commandSytanxTree.setPlayerCommandListener(this);
            commandSytanxTree.setLoadMapListener(this);
            commandSytanxTree.processCommand();
            System.out.println();
        });

    }

    private void updateImage(String name) {
        InputStream inputStream = (getClass().getResourceAsStream("/maps/" + name + "_pic.jpg"));
        if(inputStream == null) {
            inputStream = (getClass().getResourceAsStream("/maps/" + name + "_pic.png"));
        }
        Image mapImage = new Image(inputStream);
        mapPreviewImage.setImage(mapImage);
    }

    private void setupPlayerList() {
        ObservableList<String> items = FXCollections.observableArrayList(
                playersList);
        playerListView.setItems(items);
    }


    public void addPlayer(String playerName) {
        playersList.add(playerName);
        setupPlayerList();
    }

    public void removePlayer(String playerName) {
        List<String> newList = playersList.stream().filter(s -> !(s.equalsIgnoreCase(playerName))).collect(Collectors.toList());
        this.playersList = newList;
        setupPlayerList();
    }

    private void bindView() {
        chooseMap = (Button) scene.lookup("#chooseMap");
        startGame = (Button) scene.lookup("#startGame");
        mapPreviewImage = (ImageView) scene.lookup("#mapPreviewImage");
        playerListView = (ListView) scene.lookup("#playerListView");
        removePlayerButton = (Button) scene.lookup("#removePlayerButton");
        playerName = (TextField) scene.lookup("#playerName");
        commandLine = (TextArea) scene.lookup("#commandLine");
        playerColor = (ColorPicker) scene.lookup("#playerColor");
        addPlayerButton = (Button) scene.lookup("#addPlayer");
        processCommandButton = (Button) scene.lookup("#processCommandButton");
    }

    @Override
    public Scene getView() throws IOException {
        return scene;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void loadMap(MapData mapData) {
        this.mapData = mapData;
        updateImage(mapData.getFileName());
    }
}
