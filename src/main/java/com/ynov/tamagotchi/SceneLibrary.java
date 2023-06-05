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
    int width = 1024;
    int height = 768;
    public Scene NewHomeScene(Stage stage) {
        String HomeCssPath = getClass().getResource("/style/Home.css").toString();
        var HomeTitle = new Label("Welcome to the game Tamagotchi !");
        HomeTitle.setId("home-title");
        Button playButton = new Button("Play");
        Button quitButton = new Button("Quit");
        playButton.setOnAction(e -> {
            Scene eggScene = this.NewEggScene(stage);
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
    
    public Scene NewEggScene(Stage stage) {
        String eggTxtPath = "/templates/egg.txt";
        StackPane stackPane = new StackPane();
        Button buttonNext = new Button("Open egg");
        buttonNext.setOnAction(e-> {
            Scene lionScene = this.NewLionScene(stage, "adult");
            stage.setScene(lionScene);
        });
        stackPane.getChildren().addAll(openTxtFile(eggTxtPath), buttonNext);
        Scene eggScene = new Scene(stackPane, this.width, this.height);
        return eggScene;
    }

    public Scene NewLionScene(Stage stage, String lifeState) {
        String txtPath = "/templates/lion/" + lifeState + ".txt";
        System.out.println(txtPath);
        StackPane stackPane = new StackPane(openTxtFile(txtPath));
        Scene scene = new Scene(stackPane, this.width, this.height);
        return scene;
    }

    private TextArea openTxtFile(String path) {
        InputStream inputStream = getClass().getResourceAsStream(path);
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean doubleSpace = false;
                StringBuilder modifiedLine = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    char currentChar = line.charAt(i);
                    if (currentChar == ' ') {
                        if (doubleSpace) {
                            modifiedLine.append("   ");
                        } else {
                            modifiedLine.append("  ");
                        }
                        doubleSpace = !doubleSpace;
                    } else {
                        modifiedLine.append(currentChar);
                    }
                }
                content.append(modifiedLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextArea textArea = new TextArea(content.toString());
        textArea.setEditable(false);
        return textArea;
    }
}
