package com.ynov.tamagotchi;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SceneLibrary {
    public Scene NewHomeScene() {
        String HomeCssPath = getClass().getResource("/Home.css").toString();
        var HomeTitle = new Label("Welcome to the game Tamagotchi !");
        HomeTitle.setId("home-title");
        Button playButton = new Button("Play");
        Button quitButton = new Button("Quit");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(playButton, quitButton);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(HomeTitle, hBox);
        var homeScene = new Scene(vBox, 640, 480);
        homeScene.getStylesheets().add(HomeCssPath);
        return homeScene;
    }
}
