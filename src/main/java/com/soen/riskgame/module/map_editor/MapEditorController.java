package com.soen.riskgame.module.map_editor;

import com.soen.riskgame.module.core.base.BaseView;
import com.soen.riskgame.module.core.command.*;
import com.soen.riskgame.module.core.command_line.CommandSytanxTree;
import com.soen.riskgame.module.core.command_line.Lexer;
import com.soen.riskgame.module.core.command_line.Token;
import com.soen.riskgame.module.core.constants.MapDelimiters;
import com.soen.riskgame.module.core.model.Country;
import com.soen.riskgame.module.core.model.Map;
import com.soen.riskgame.module.core.model.MapData;
import com.soen.riskgame.module.core.utils.FileReader;
import com.soen.riskgame.module.core.utils.MapDataUtil;
import com.soen.riskgame.module.core.utils.MapParser;
import com.soen.riskgame.module.core.utils.MapValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Class MapEditorController is a subclass of base view and implements Observer, ShowMapCommand, ValidateCommand, EditMapCommand
 * @author Mayokun
 */
public class MapEditorController extends BaseView implements Observer, ShowMapCommand.ShowMapListener, ValidateCommand.ValidateMapListener, EditMapCommand.EditMapListener {

    private final String DASHBOARD = "/view/map_editor.fxml";
    private final Scene scene;
    private Button loadMap;
    private Button removeContinent;
    private Button removeCountry;
    private Button removeNeigbour;
    private Button processCommand;
    private Button saveCountry;
    private Button addNeigbour;
    private Button saveContinent;
    private ListView countriesListView;
    private ListView continentListView;
    private ListView neigbhoursListView;
    private TextArea commandLine;
    private TextField addCountryName;
    private TextField addCountryContinentName;
    private TextField addCountryXCoordinate;
    private TextField addCountryYCoordinate;
    private TextField addNeigbourCountryName;
    private TextField addNeigbourCountryNeigbourName;
    private TextField addContinentName;
    private TextField addContinentColor;
    private String currentSelectedCountry;
    private String currentSelectedContinent;
    /**
     * variable to display currentSelectedneighbour
     */
    private String currentSelectedNeighbour;
    /**
     * variable of Map data
     */
    private MapData mapData;
    /**
     * Constructor of the class
     * @throws IOException
     */
    public MapEditorController() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(DASHBOARD));
        scene = new Scene(root, 1290, 765);
        bindView();
        scene.getWindow();

        loadMap.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Map File", "*" + MapDelimiters.MAP_FILE_EXTENSION)
            );
            fileChooser.setTitle("Open Map File");
            File file = fileChooser.showOpenDialog(scene.getWindow());
            try {
                loadMapData(file.getAbsoluteFile());
                setupCountriesList();
                setupContinentList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        countriesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentSelectedCountry = String.valueOf(newValue);
            Country country = MapDataUtil.findCountryByName(currentSelectedCountry, mapData.getCountries());
            if (country != null) {
                setupNeighbours(country);
            }
        });

        continentListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentSelectedContinent = String.valueOf(newValue);
        });

        neigbhoursListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentSelectedNeighbour = String.valueOf(newValue);
        });

        removeContinent.setOnAction(event -> {
            RemoveContinentCommand removeContinentCommand = new RemoveContinentCommand(mapData, currentSelectedContinent);
            removeContinentCommand.execute();
        });

        removeCountry.setOnAction(event -> {
            RemoveCountryCommand removeCountryCommand = new RemoveCountryCommand(mapData, currentSelectedCountry);
            removeCountryCommand.execute();
        });

        removeNeigbour.setOnAction(event -> {
        });

        processCommand.setOnAction(event -> {
            String command = commandLine.getText();
            List<Token> tokens = Lexer.lex(command);
            CommandSytanxTree commandSytanxTree = new CommandSytanxTree(mapData, tokens);
            commandSytanxTree.setShowMapListener(this);
            commandSytanxTree.setValidateMapListener(this);
            commandSytanxTree.setEditMapListener(this);
            commandSytanxTree.processCommand();
            System.out.println();
        });

        saveCountry.setOnAction(event -> {
            AddCountryCommand addCountryCommand = new AddCountryCommand(mapData, addCountryName.getText(),
                    addCountryContinentName.getText(), addCountryXCoordinate.getText(), addCountryYCoordinate.getText());
            addCountryCommand.execute();
        });

        addNeigbour.setOnAction(event -> {
            AddNeighbourCommand addNeighbourCommand = new AddNeighbourCommand(mapData, addNeigbourCountryName.getText(), addNeigbourCountryNeigbourName.getText());
            addNeighbourCommand.execute();
        });

        saveContinent.setOnAction(event -> {
            AddContinentCommand addContinentCommand = new AddContinentCommand(mapData, addContinentName.getText(), Integer.parseInt(addContinentColor.getText()));
            addContinentCommand.execute();
        });
    }
    /**
     * method to setup countries list
     */
    private void setupCountriesList() {
        List<String> countriesList = new ArrayList<>();
        mapData.getCountries().entrySet().forEach(v -> countriesList.add(v.getValue().getName()));
        ObservableList<String> items = FXCollections.observableArrayList(
                countriesList);
        countriesListView.setItems(items);
    }
    /**
     * method to setup continents list
     */
    private void setupContinentList() {
        List<String> continentList = new ArrayList<>();
        mapData.getContinents().entrySet().forEach(v -> continentList.add(v.getValue().getName()));
        ObservableList<String> items = FXCollections.observableArrayList(
                continentList);
        continentListView.setItems(items);

    }
    /**
     * method to setup neighbours to country
     * @param country country
     */
    private void setupNeighbours(Country country) {
        List<String> continentList = new ArrayList<>();
        country.getAdjacentCountries().forEach(v -> continentList.add(v.getName()));
        ObservableList<String> items = FXCollections.observableArrayList(
                continentList);
        neigbhoursListView.setItems(items);
    }
    /**
     * method to load the map data
     * @param file input file
     * @throws Exception
     */
    private void loadMapData(File file) throws Exception {
        FileReader fileReader = new FileReader(file.getAbsolutePath());
        String mapData = fileReader.readData().replaceAll(MapDelimiters.CARRIAGE_DELIMITER, "");
        MapValidator mapValidator = new MapValidator(mapData);
        mapValidator.validate();
        MapParser mapParser = new MapParser(mapData);
        this.mapData = new Map.Builder(mapParser.getGameFile(), mapParser.getCountries(), mapParser.getContinentDTOS(), mapParser.getBorderDTOS()).build();
        this.mapData.addObserver(this);
    }
    /**
     * getter method of the view
     * @return scene
     */
    @Override
    public Scene getView() {
        return scene;
    }
    /**
     * binder method of the view to load and view scene
     */
    private void bindView() {
        loadMap = (Button) scene.lookup("#loadMap");
        countriesListView = (ListView) scene.lookup("#countriesListView");
        continentListView = (ListView) scene.lookup("#continentListView");
        neigbhoursListView = (ListView) scene.lookup("#neigbhoursListView");
        removeContinent = (Button) scene.lookup("#removeContinent");
        removeCountry = (Button) scene.lookup("#removeCountry");
        processCommand = (Button) scene.lookup("#processCommand");
        removeNeigbour = (Button) scene.lookup("#removeNeigbour");
        saveCountry = (Button) scene.lookup("#saveCountry");
        addNeigbour = (Button) scene.lookup("#addNeigbour");
        saveContinent = (Button) scene.lookup("#saveContinent");
        commandLine = (TextArea) scene.lookup("#commandLine");
        addCountryName = (TextField) scene.lookup("#addCountryName");
        addCountryContinentName = (TextField) scene.lookup("#addCountryContinentName");
        addCountryXCoordinate = (TextField) scene.lookup("#addCountryXCoordinate");
        addCountryYCoordinate = (TextField) scene.lookup("#addCountryYCoordinate");
        addNeigbourCountryName = (TextField) scene.lookup("#addNeigbourCountryName");
        addNeigbourCountryNeigbourName = (TextField) scene.lookup("#addNeigbourCountryNeigbourName");
        addContinentName = (TextField) scene.lookup("#addContinentName");
        addContinentColor = (TextField) scene.lookup("#addContinentColor");
    }
    /**
     * method to update map data
     * @param o  Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        this.mapData = (MapData) arg;
        updateView();
    }
    /**
     * method to update the view
     */
    private void updateView() {
        setupContinentList();
        setupCountriesList();
    }
    /**
     * method to show map
     * @param mapData mapdata
     */
    @Override
    public void showMap(String mapData) {
        commandLine.setText(mapData);
        System.out.println();
    }
    /**
     * boolean method to print once th map is validated
     * @param isMapValid boolean varaible
     */
    @Override
    public void onMapValidated(Boolean isMapValid) {
        commandLine.setText("Map Valid: " + isMapValid);
    }
    /**
     * method to edit the map
     * @param mapData mapdata
     */
    @Override
    public void editMap(MapData mapData) {
        this.mapData = mapData;
        this.mapData.addObserver(this);
        updateView();
    }
}
