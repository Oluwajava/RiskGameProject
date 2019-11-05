package com.soen.riskgame.module.game_play;

import com.soen.riskgame.module.core.command.ShowMapCommand;
import com.soen.riskgame.module.core.command_line.CommandSytanxTree;
import com.soen.riskgame.module.core.command_line.Lexer;
import com.soen.riskgame.module.core.command_line.Token;
import com.soen.riskgame.module.core.enums.Phase;
import com.soen.riskgame.module.core.interfaces.View;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.model.Player;
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
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class GamePlayController implements View, Observer, ShowMapCommand.ShowMapListener {

    private final String NEW_GAME = "/view/game-play.fxml";
    private final Scene scene;
    private ImageView mapImage;
    private AnchorPane anchorPane;
    private ListView listView;
    private MapData mapData;
    private Button processCommandButton;
    private Text turnText;
    private TextArea commandLine;
    private ListView playerListView;
    private Text reinforceArmyText;
    private Text initialArmyText;
    private Text totalArmyText;
    private Text phaseText;

    public GamePlayController(MapData mapData) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(NEW_GAME));
        scene = new Scene(root, 800, 760);
        bindView();
        this.mapData = mapData;
        mapData.addObserver(this);
        mapData.setGameStarted(true);
        setupGameView();
        setPlayerList(mapData);

        processCommandButton.setOnAction(event -> {
            String command = commandLine.getText();
            List<Token> tokens = Lexer.lex(command);
            CommandSytanxTree commandSytanxTree = new CommandSytanxTree(mapData, tokens);
            commandSytanxTree.setShowMapListener(this);
            commandSytanxTree.processCommand();
            System.out.println();
        });
    }

    private void setupGameView() {
        updateImage(mapData.getFileName());
    }

    private void updateCountriesLocation() {
        List<Node> nodes = mapData.getCountries().values().stream().map(country -> {
            Circle circle = new Circle();
            StackPane stack = new StackPane();
            stack.setLayoutX(Double.parseDouble(country.getXCoordinate()) + 10);
            stack.setLayoutY(Double.parseDouble(country.getYCoordinate()) + 10);
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
    }

    private void updateImage(String name) {
        InputStream inputStream = (getClass().getResourceAsStream("/maps/" + name + "_pic.jpg"));
        if (inputStream == null) {
            inputStream = (getClass().getResourceAsStream("/maps/" + name + "_pic.png"));
        }
        Image mapImage = new Image(inputStream);
        this.mapImage.setImage(mapImage);
    }

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
                    setText(item.getPlayerName() + "\t\t\t" + String.valueOf(result * 100)+"%");
                    setGraphic(circle);
                }
            }
        });

    }

    @Override
    public Scene getView() throws IOException {
        return scene;
    }

    @Override
    public void update(Observable o, Object arg) {
        MapData mapTest = (MapData) arg;
        System.out.println(mapData);
        Player player = mapTest.getPlayers().last();
        turnText.setText("" + player.getPlayerName() + " Turn!!!");
        initialArmyText.setText("Initial Army: " + player.getPlaceArmiesNo());
        reinforceArmyText.setText("Reinforcement Army: " + player.getNumOfArmies());
        Long totalArmyCount = player.getCountries().stream().mapToLong(Country::getNoOfArmies).sum();
        totalArmyText.setText("Total Army: " + totalArmyCount);
        updateCountriesLocation();
        setPhase(player);
        setPlayerList(mapTest);
    }

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

    @Override
    public void showMap(String mapData) {
        commandLine.setText(mapData);
    }
}
