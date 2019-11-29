package com.soen.riskgame.module.game_play;

import com.soen.riskgame.module.core.command.ShowMapCommand;
import com.soen.riskgame.module.core.command_line.CommandSytanxTree;
import com.soen.riskgame.module.core.command_line.Lexer;
import com.soen.riskgame.module.core.command_line.Token;
import com.soen.riskgame.module.core.enums.Phase;
import com.soen.riskgame.module.core.interfaces.CommandSytanxProcessor;
import com.soen.riskgame.module.core.interfaces.View;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;
import com.soen.riskgame.module.core.strategy.HumanStrategy;
import com.soen.riskgame.module.core.utils.MapDataUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

/**
 * This class provides all the methods to control various activities during the game play,
 * implements Observer.
 * @author Sai
 */
public class GamePlayController implements View, Observer, ShowMapCommand.ShowMapListener, CommandSytanxProcessor, PhaseListener {

    /**
     * name of the gameplay
     */
    private final String NEW_GAME = "/view/game-play.fxml";
    /**
     * variable of scene view
     */
    private final Scene scene;
    /**
     * variable of image view
     */
    private ImageView mapImage;
    /**
     * variable for country list view
     */
    private ListView countriesListView;
    /**
     * variable for neighbour list view
     */
    private ListView neigbhoursListView;
    /**
     * GUI variable of Anchor pane
     */
    private AnchorPane anchorPane;
    /**
     * variable of list view
     */
    private ListView listView;
    /**
     * variable of Mpa data
     */
    private MapData mapData;
    /**
     * variable for processCommand button
     */
    private Button processCommandButton;
    /**
     * variable for text line
     */
    private Text turnText;
    /**
     * variable for test area
     */
    private TextArea commandLine;
    /**
     * variable for test area
     */
    private TextArea gameLog;

    /**
     * variable for players list view
     */
    private ListView playerListView;
    /**
     * variable for reinforcement army
     */
    private Text reinforceArmyText;
    /**
     * variable for initial  army
     */
    private Text initialArmyText;
    /**
     * total army
     */
    private Text totalArmyText;
    /**
     * phase variable
     */
    private Text phaseText;
    /**
     * card view variable
     */
    private Text cardView;
    /**
     * variable to display currentSelectedCountry
     */
    private String currentSelectedCountry;

    private List<Node> nodes;

    /**
     * Constructor of the class
     *
     * @param mapData map data
     * @throws IOException IOException
     */
    public GamePlayController(MapData mapData) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NEW_GAME));
        scene = new Scene(root, 1300, 760);
        bindView();
        updateView(mapData);
        this.mapData = mapData;
        mapData.addObserver(this);
        mapData.setGameStarted(true);
        setupGameView();
        setPlayerList(mapData);
        setupCountriesList();
        processCommandButton.setOnAction(event -> {
            String command = commandLine.getText();
            List<Token> tokens = Lexer.lex(command);
            CommandSytanxTree commandSytanxTree = new CommandSytanxTree(mapData, tokens);
            commandSytanxTree.setShowMapListener(this);
            commandSytanxTree.setCommandSytanxProcessor(this);
            commandSytanxTree.processCommand();
            System.out.println();
        });
        countriesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setupNeighbours((Country) newValue);
        });
        autoPopulate(mapData);
    }

    /**
     * method to  autoPopulate map
     * @param mapData hold map data
     */
    private void autoPopulate(MapData mapData) {
        mapData.toList().forEach(v -> {
            if (!(v.getPlayerStrategy() instanceof HumanStrategy)) {
                mapData.populateCountries();
                mapData.placeAll();
            }
        });
    }

    /**
     * method for setup game view
     */
    private void setupGameView() {
        updateImage(mapData.getFileName());
    }

    /**
     * method to update country location
     */
    private void updateCountriesLocation() {
        if (nodes != null) {
            anchorPane.getChildren().removeAll(nodes);
        }
        nodes = mapData.getCountries().values().stream().map(country -> {
            Circle circle = new Circle();
            StackPane stack = new StackPane();
            if (!mapData.isRisk()) {
                stack.setLayoutX(Double.parseDouble(country.getXCoordinate()) - 40);
                stack.setLayoutY(Double.parseDouble(country.getYCoordinate()) - 30);
            } else {
                stack.setLayoutX(Double.parseDouble(country.getXCoordinate()) + 10);
                stack.setLayoutY(Double.parseDouble(country.getYCoordinate()) + 10);
            }

            if (country.getPlayer() != null) {
                Player.PlayerColor playerColor = country.getPlayer().getPlayerColor();
                circle.setFill(javafx.scene.paint.Color.rgb(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue()));
                circle.setRadius(10.0f);
                Text text = new Text(String.valueOf(country.getNoOfArmies()));
                text.setBoundsType(TextBoundsType.VISUAL);
                stack.getChildren().addAll(circle, text);
            }
            return stack;
        }).collect(Collectors.toList());


        anchorPane.getChildren().addAll(nodes);
    }

    /**
     * binder method of the view to load and view scene
     */
    private void bindView() {
        mapImage = (ImageView) scene.lookup("#mapImage");
        anchorPane = (AnchorPane) scene.lookup("#anchorPane");
        processCommandButton = (Button) scene.lookup("#processCommandButton");
        turnText = (Text) scene.lookup("#turnText");
        commandLine = (TextArea) scene.lookup("#comandLine");
        playerListView = (ListView) scene.lookup("#playerListView");
        reinforceArmyText = (Text) scene.lookup("#reinforceArmyText");
        initialArmyText = (Text) scene.lookup("#initialArmyText");
        totalArmyText = (Text) scene.lookup("#totalArmyText");
        phaseText = (Text) scene.lookup("#phaseText");
        cardView = (Text) scene.lookup("#cardView");
        gameLog = (TextArea) scene.lookup("#gameLog");
        countriesListView = (ListView) scene.lookup("#countriesListView");
        neigbhoursListView = (ListView) scene.lookup("#neigbhoursListView");
    }

    /**
     * method to setup countries list
     */
    private void setupCountriesList() {
        List<Country> countries = mapData.getCountries().entrySet().stream().map(stringCountryEntry -> stringCountryEntry.getValue()).collect(Collectors.toList());
        ObservableList<Country> items = FXCollections.observableArrayList(
                countries);
        countriesListView.setItems(items);
        setCountryTableView(countriesListView);
    }

    /**
     * method to setup neighbours to country
     *
     * @param country country
     */
    private void setupNeighbours(Country country) {
        ObservableList<Country> items = FXCollections.observableArrayList(
                country.getAdjacentCountries());
        neigbhoursListView.setItems(items);
        setCountryTableView(neigbhoursListView);
    }

    /**
     * method to set setCountryTableView
     * @param listView list of view
     */
    private void setCountryTableView(ListView listView) {
        listView.setCellFactory(param -> new ListCell<Country>() {
            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Circle circle = new Circle();
                    if (item.getPlayer() != null) {
                        Player.PlayerColor playerColor = item.getPlayer().getPlayerColor();
                        circle.setFill(javafx.scene.paint.Color.rgb(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue()));
                        circle.setRadius(10.0f);
                        setText(item.getName() + "; Armies: " + item.getNoOfArmies());
                        setGraphic(circle);
                    }
                }
            }
        });
    }

    /**
     * method to update image
     *
     * @param name input string
     */
    private void updateImage(String name) {
        InputStream inputStream = (getClass().getResourceAsStream("/maps/" + name + "_pic.jpg"));
        if (inputStream == null) {
            inputStream = (getClass().getResourceAsStream("/maps/" + name + "_pic.png"));
        }
        Image mapImage = new Image(inputStream);
        this.mapImage.setImage(mapImage);
    }

    /**
     * method to set players list
     */
    private void setPlayerList(MapData mapData) {
        ObservableList<Player> items = FXCollections.observableArrayList(
                mapData.toList());
        playerListView.setItems(items);
        playerListView.setCellFactory(param -> new ListCell<Player>() {
            @Override
            protected void updateItem(Player item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Circle circle = new Circle();
                    Player.PlayerColor playerColor = item.getPlayerColor();
                    circle.setFill(javafx.scene.paint.Color.rgb(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue()));
                    circle.setRadius(10.0f);
                    double mapSize = mapData.getCountries().size();
                    double countries = item.getCountries().size();
                    double result = countries / mapSize;
                    setText(item.getPlayerName() + "\t\t\t" + String.valueOf(result * 100) + "%");
                    setGraphic(circle);
                }
            }
        });

    }

    /**
     * getter method of the view
     *
     * @return scene
     * @throws IOException IOException
     */
    @Override
    public Scene getView() throws IOException {
        return scene;
    }

    /**
     * method to update the view
     *
     * @param o   oberseravble
     * @param arg object
     */
    @Override
    public void update(Observable o, Object arg) {

        MapData mapTest = (MapData) arg;
        this.mapData = mapTest;
        updateView(mapTest);
    }

    private void updateView(MapData mapTest) {
        this.mapData = mapTest;
//        System.out.println(mapData);
        Player player = mapTest.getPlayers().last();
        turnText.setText("" + player.getPlayerName() + " Turn!!!");
        initialArmyText.setText("Initial Army: " + player.getPlaceArmiesNo());
        reinforceArmyText.setText("Reinforcement Army: " + player.getNumOfArmies());
        Long totalArmyCount = player.getCountries().stream().mapToLong(Country::getNoOfArmies).sum();
        totalArmyText.setText("Total Army: " + totalArmyCount);
        gameLog.setText(mapTest.getAttackLog());

        setupGameView();
        setPhase(player);
        setPlayerList(mapTest);
        setCardView(player);
        setupCountriesList();

        if (mapData.isGameOver()) {
            setupGameView();
            setPhase(player);
            setPlayerList(mapTest);
            setCardView(player);
            setupCountriesList();
            updateCountriesLocation();
        } else {
            if (!(player.getPlayerStrategy() instanceof HumanStrategy) && !mapData.isTournamentEnd()) {
                if (player.getPhase() == Phase.ATTACK) {
                    player.getPlayerStrategy().attack(mapData);
                } else if (player.getPhase() == Phase.REINFORCEMENT) {
                    player.getPlayerStrategy().reinforce(mapData);
                } else if (player.getPhase() == Phase.FORTIFICATION) {
                    player.getPlayerStrategy().fortify(mapData);
                }
            }

            if (mapData.isNewPhase() && !mapData.isTournamentEnd()) {
                mapData.setNewPhase(false);
                autoPopulate(mapData);
            }
        }

        updateCountriesLocation();
    }

    /**
     * Setter method for card view
     *
     * @param player game player
     */
    private void setCardView(Player player) {
        if (player.getPhase() == Phase.REINFORCEMENT) {
            cardView.setVisible(true);
            StringBuilder cardViewBuilder = new StringBuilder();
            List<String> cards = player.getCards().getPlayerCards();
            for (int i = 0; i < cards.size(); i++) {
                cardViewBuilder.append("Index: " + i + "\tCard Type: " + cards.get(i) + "\n");
            }
            cardView.setText(cardViewBuilder.toString());
        } else {
            cardView.setVisible(false);
        }
    }

    /**
     * Setter method for phase view
     *
     * @param player game player
     */
    private void setPhase(Player player) {
        if (player.getPlaceArmiesNo() > 0) {
            phaseText.setText("Startup Phase");
        }

        if (player.getPhase() == Phase.REINFORCEMENT) {
            phaseText.setText("Reinforcement Phase");
        }

        if (player.getPhase() == Phase.ATTACK) {
            phaseText.setText("Attack Phase");
        }

        if (player.getPhase() == Phase.FORTIFICATION) {
            phaseText.setText("Fortification Phase");
        }
    }

    /**
     * method implementing show map in GUI
     *
     * @param mapData mapData
     */
    @Override
    public void showMap(String mapData) {
        commandLine.setText(mapData);
    }

    /**
     * method if any error
     *
     * @param message error message
     */
    @Override
    public void onError(String message) {
        commandLine.setText(message);
    }

    /**
     * method for next phase
     */
    @Override
    public void nextPhase() {

    }
}
