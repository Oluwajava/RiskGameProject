package com.soen.riskgame.module.dashboard;

import com.soen.riskgame.module.core.base.BaseView;
import com.soen.riskgame.module.map_editor.MapEditorView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardView extends BaseView {

    private final String DASHBOARD = "/view/dashboard.fxml";
    private Button newGameButton;
    private Button loadGameButton;
    private Button createMapButton;
    private Button editMapButton;
    private final Scene scene;

    public DashboardView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(DASHBOARD));
        scene = new Scene(root, 1100, 600);
        bindView();

        scene.getWindow();

        createMapButton.setOnAction(event -> {
        });
        editMapButton.setOnAction(event -> {

            try {
                MapEditorView mapEditorView = new MapEditorView();
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                stage.setScene(mapEditorView.getView());
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
