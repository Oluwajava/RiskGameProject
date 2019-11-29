package com.soen.riskgame.module.new_game;

import com.soen.riskgame.module.core.command.LoadGameCommand;
import com.soen.riskgame.module.core.command.LoadMapCommand;
import com.soen.riskgame.module.core.command_line.CommandSytanxTree;
import com.soen.riskgame.module.core.command_line.Lexer;
import com.soen.riskgame.module.core.command_line.Token;
import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.interfaces.GameTypeListener;
import com.soen.riskgame.module.core.interfaces.PlayerCommandListener;
import com.soen.riskgame.module.core.interfaces.View;
import com.soen.riskgame.module.core.model.Map;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.PlayerDTO;
import com.soen.riskgame.module.core.strategy.GameStrategy;
import com.soen.riskgame.module.core.utils.FileReader;
import com.soen.riskgame.module.core.utils.MapDataUtil;
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
public class NewGameController implements View, PlayerCommandListener, LoadMapCommand.LoadMapListener, LoadGameCommand.LoadGameListener, GameTypeListener, Observer {

    /**
     * name of the new game
     */
    private final String NEW_GAME = "/view/new-game.fxml";
    /**
     * variable of scene view
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
    /**
     * list of the players in the game
     */
    private List<PlayerDTO> playersList = new ArrayList<>();
    /**
     * variable for map preview image
     */
    private ImageView mapPreviewImage;
    /**
     * variable of players list view
     */
    private ListView playerListView;
    /**
     * variable for remove player button
     */
    private Button removePlayerButton;
    /**
     * variable for text field view player name
     */
    private TextField playerName;
    /**
     * ariable for text area for the command line
     */
    private TextArea commandLine;
    /**
     * variable to chose color
     */
    private ColorPicker playerColor;
    /**
     * variable for add player button
     */
    private Button addPlayerButton;
    /**
     * variable for processCommand button
     */
    private Button processCommandButton;
    /**
     * variable to select player
     */
    private String selectedPlayer;
    /**
     * variable for the map data
     */
    private MapData mapData;

    /**
     * Constructor of the class that initializes objects
     * @throws IOException
     */
    public NewGameController() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NEW_GAME));
        scene = new Scene(root, 980, 740);
        bindView();

        mapData = new MapData();
        chooseMap.setOnAction(event -> {
            try {
                MapListController mapListController = new MapListController(name -> {
                    System.out.println(name);
                    FileReader fileReader = new FileReader("C:\\Users\\Adekunle\\RiskGameProject\\src\\main\\resources\\maps\\0" + name + ".map");
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
                playersList.forEach(v -> mapData.addPlayer(v.getPlayerName(), v.getStrategy()));
                startGame();
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
            addPlayer(playerName.getText(), GameStrategy.HUMAN);
        });

        processCommandButton.setOnAction(event -> {
            String command = commandLine.getText();
            List<Token> tokens = Lexer.lex(command);
            CommandSytanxTree commandSytanxTree= new CommandSytanxTree(mapData, tokens);
            commandSytanxTree.setPlayerCommandListener(this);
            commandSytanxTree.setLoadMapListener(this);
            commandSytanxTree.setLoadGameListener(this);
            commandSytanxTree.setGameTypeListener(this);
            commandSytanxTree.processCommand();
        });

    }

    private void startGame() throws IOException {
        GamePlayController gamePlayController = new GamePlayController(mapData);
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        try {
            stage.setScene(gamePlayController.getView());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    /**
     * method to update the image
     * @param name String name of image
     */
    private void updateImage(String name) {
        InputStream inputStream = (getClass().getResourceAsStream("/maps/" + name + "_pic.jpg"));
        if(inputStream == null) {
            inputStream = (getClass().getResourceAsStream("/maps/" + name + "_pic.png"));
        }
        Image mapImage = new Image(inputStream);
        mapPreviewImage.setImage(mapImage);
    }
    /**
     * method to setup players list
     */
    private void setupPlayerList() {
        List<String> list = playersList.stream().map(v -> v.getPlayerName()).collect(Collectors.toList());;
        ObservableList<String> items = FXCollections.observableArrayList(
                list);
        playerListView.setItems(items);
    }

    /**
     * method to add player to the list
     */
    public void addPlayer(String playerName, String strategy) {
        if (mapData.isTournamentMode()) {
            addPlayerToList(playerName, strategy);
        } else {
            if (playersList.size() < mapData.getCountries().size()) {
                addPlayerToList(playerName, strategy);
            } else {
                commandLine.setText("You can't add more players that number of countries available");
            }
        }
    }

    private void addPlayerToList(String playerName, String strategy) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerName(playerName);
        playerDTO.setStrategy(strategy);
        playersList.add(playerDTO);
        setupPlayerList();
    }

    /**
     * method to remove player to the list
     */
    public void removePlayer(String playerName) {
        List<PlayerDTO> newList = playersList.stream().filter(s -> !(s.getPlayerName().equalsIgnoreCase(playerName))).collect(Collectors.toList());
        this.playersList = newList;
        setupPlayerList();
    }
    /**
     * binder method of the view to load and view scene
     */
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
    /**
     * getter method of the view
     * @return scene
     * @throws IOException
     */
    @Override
    public Scene getView() throws IOException {
        return scene;
    }
    /**
     * obseravle class method
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {

    }
    /**
     * method to load map
     * @param mapData mapdata
     */
    @Override
    public void loadMap(MapData mapData) {
        this.mapData = mapData;
        updateImage(mapData.getFileName());
    }

    @Override
    public void setListOfMapFiles(List<String> listOfMapFiles) {
        MapData data = MapDataUtil.loadMapFromFile(listOfMapFiles.get(mapData.getGameCounter()));
        mapData.setGameCounter(listOfMapFiles);
        mapData.setCountries(data.getCountries());
        mapData.setContinents(data.getContinents());
        mapData.setFileName(data.getFileName());
        updateImage(listOfMapFiles.get(0));
    }

    @Override
    public void setListOfPlayerStrategies(List<String> listOfMapFiles) {
        mapData.setListOfPlayersStrategies(listOfMapFiles);
    }

    @Override
    public void setNumberOfGames(int num) {
        mapData.setNumberOfGames(num);
    }

    @Override
    public void setMaxNumberOfGames(int num) {
        mapData.setMaxNumberOfTurns(num);
    }

    @Override
    public void setIsTournament() {
        mapData.setTournamentMode(true);
    }

    @Override
    public void loadGame(MapData ma) {
        this.mapData = ma;
        try {
            startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved game data: "+ma);
    }
}
