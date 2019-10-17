package com.soen.riskgame;

import com.soen.riskgame.module.dashboard.DashboardView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DashboardView dashboardView = new DashboardView();
        primaryStage.setTitle("Risk Game");
        primaryStage.setScene(dashboardView.getView());
        primaryStage.show();
    }
}
