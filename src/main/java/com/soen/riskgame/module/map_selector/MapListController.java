package com.soen.riskgame.module.map_selector;

import com.soen.riskgame.module.core.interfaces.MapPicker;
import com.soen.riskgame.module.core.interfaces.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class MapListController implements view,
 * class controls list of map data on the GUI
 * @author Mayokun
 */
public class MapListController implements View {

    /**
     * name of the map view
     */
    private final String MAP_VIEW = "/view/map_selector.fxml";
    /**
     * variable of MapPicker class
     */
    private final MapPicker mapPicker;
    /**
     * variable of ;list of map data
     */
    private final List<MapInfo> mapData;
    /**
     * variable of scene view
     */
    private final Scene scene;
    /**
     * variable of list view
     */
    private ListView listView;
    /**
     * variable for cancel button
     */
    private Button cancelButton;
    /**
     * variable for ok button
     */
    private Button okButton;
    /**
     * variable to store name of selected map
     */
    private String selectedMap;

    /**
     * Constructor of the class Initializes mapIO object
     * @param mapPicker
     * @throws IOException
     */
    public MapListController(MapPicker mapPicker) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(MAP_VIEW));
        scene = new Scene(root, 600, 500);
        this.mapPicker = mapPicker;
        mapData = getMapData();
        bindView();
        setMapDataToList();

        cancelButton.setOnAction(event -> {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        });

        okButton.setOnAction(event -> {
            try {
                mapPicker.onMapSelected(selectedMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        });

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedMap = ((MapInfo)newValue).getMapName();
        });
    }
    /**
     * method that sets the map data to the list
     */
    private void setMapDataToList() {
        ObservableList<MapInfo> items = FXCollections.observableArrayList(
                mapData);
        listView.setItems(items);
        listView.setCellFactory(param -> new ListCell<MapInfo>() {
            private ImageView imageView = new ImageView();
            /**
             * method to update the map item
             * @param item map item
             * @param empty boolean
             */
            @Override
            protected void updateItem(MapInfo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getName());
                    try {
                        Image image = new Image(new FileInputStream("C:\\Users\\Admin\\APP\\RiskGameProject\\src\\main\\resources\\maps\\preview" + "/" + item.getImageUrl()));
                        imageView.setImage(image);
                        setGraphic(imageView);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    /**
     * binder method of the view to load and view scene
     */
    private void bindView() {
        listView = (ListView) scene.lookup("#mapListView");
        okButton = (Button) scene.lookup("#okButton");
        cancelButton = (Button) scene.lookup("#cancelButton");
    }

    /**
     * getter method of the map data
     * @return mapData
     */
    private List<MapInfo> getMapData() {
        File folder = new File("C:\\Users\\Admin\\APP\\RiskGameProject\\src\\main\\resources\\maps\\preview");
        File[] listOfFiles = folder.listFiles();
        List<MapInfo> mapData = new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fileName = listOfFiles[i].getName();
                MapInfo data = new MapInfo(fileName);
                mapData.add(data);
            }
        }
        return mapData;
    }
    /**
     * method to get the view
     * @return scene
     * @throws IOException
     */
    @Override
    public Scene getView() throws IOException {
        return scene;
    }
}
