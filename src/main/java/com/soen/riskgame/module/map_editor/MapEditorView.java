package com.soen.riskgame.module.map_editor;

import com.soen.riskgame.module.core.base.BaseView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class MapEditorView extends BaseView {

    private final String DASHBOARD = "/view/map_editor.fxml";
    private final Scene scene;

    public MapEditorView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(DASHBOARD));
        scene = new Scene(root, 1090, 765);
        bindView();

        scene.getWindow();

    }

    @Override
    public Scene getView() {
        return scene;
    }

    private void bindView() {

    }
}
