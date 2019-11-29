package com.soen.riskgame;

import com.soen.riskgame.module.dashboard.DashboardController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * main class of the game
 * @author Sai
 */
public class Main extends Application {
    /**
     * Main method
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * game starts here
     * @param primaryStage stage of game
     * @throws Exception Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        DashboardController dashboardController = new DashboardController();
        primaryStage.setTitle("Risk Game");
        primaryStage.setScene(dashboardController.getView());
		primaryStage.show();
	}
}
