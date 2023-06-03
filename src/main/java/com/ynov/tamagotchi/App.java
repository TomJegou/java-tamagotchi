package com.ynov.tamagotchi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        SceneLibrary sceneLibrary = new SceneLibrary();
        Scene homeScene = sceneLibrary.NewHomeScene();
        stage.setTitle("Tamagotchi");
        stage.setScene(homeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}