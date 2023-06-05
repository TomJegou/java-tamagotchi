package com.ynov.tamagotchi;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Platform;

public class SceneLibrary {
    int width = 800;
    int height = 600;
    public Scene NewHomeScene(Stage stage) {
        String HomeCssPath = getClass().getResource("/style/Home.css").toString();
        var HomeTitle = new Label("Welcome to the game Tamagotchi !");
        HomeTitle.setId("home-title");
        Button playButton = new Button("Play");
        Button quitButton = new Button("Quit");
        playButton.setOnAction(e -> {
            Scene eggScene = this.NewEggScene();
            stage.setScene(eggScene);
        });
        quitButton.setOnAction(e -> {
            Platform.exit();
        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(playButton, quitButton);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(HomeTitle, hBox);
        var homeScene = new Scene(vBox, this.width, this.height);
        homeScene.getStylesheets().add(HomeCssPath);
        return homeScene;
    }
    
    public Scene NewEggScene() {
        String eggTxtPath = "/templates/egg.txt";
        InputStream inputStream = getClass().getResourceAsStream(eggTxtPath);
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextArea textArea = new TextArea(content.toString());
        textArea.setEditable(false);
        StackPane stackPane = new StackPane(textArea);
        Scene eggScene = new Scene(stackPane, this.width, this.height);
        return eggScene;
    }
}
