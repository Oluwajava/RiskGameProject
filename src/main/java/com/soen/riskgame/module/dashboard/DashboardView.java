package com.soen.riskgame.module.dashboard;

import com.soen.riskgame.module.core.base.BaseView;
import com.soen.riskgame.module.map_editor.MapEditorController;
import com.soen.riskgame.module.new_game.NewGameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class to implement the Dashboard view of the GUI
 * it is first face of user interaction.
 * @author Mayokun
 */
public class DashboardView extends BaseView {

    /**
     * name of the dashboard
     */
    private final String DASHBOARD = "/view/dashboard.fxml";
    /**
     * variable for new game button
     */
    private Button newGameButton;
    /**
     * variable for load game button
     */
    private Button loadGameButton;
    /**
     * variable for create map button
     */
    private Button createMapButton;
    /**
     * variable for edit map button
     */
    private Button editMapButton;
    /**
     * variable of scene view
     */
    private final Scene scene;

    public DashboardView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(DASHBOARD));
        scene = new Scene(root, 1100, 600);
        bindView();

        scene.getWindow();

        newGameButton.setOnAction(event -> {
            try {
                NewGameController newGameController = new NewGameController();
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                try {
                    stage.setScene(newGameController.getView());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createMapButton.setOnAction(event -> {
        });
        editMapButton.setOnAction(event -> {
            try {
                MapEditorController mapEditorController = new MapEditorController();
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                stage.setScene(mapEditorController.getView());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    @Override
    public Scene getView() {
        return scene;
    }

    private void bindView() {
        newGameButton = (Button) scene.lookup("#newGame");
        loadGameButton = (Button) scene.lookup("#loadGame");
        createMapButton = (Button) scene.lookup("#createMap");
        editMapButton = (Button) scene.lookup("#editMap");
    }
}
