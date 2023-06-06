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
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Platform;

public class SceneOrchestror{
    int width = 1024;
    int height = 768;
    Stage stage;
    Tamagotchi tamagotchi;

    public SceneOrchestror(Stage stage, int w, int h) {
        this.height = h;
        this.width = w;
        this.stage = stage;
    }

    public Scene NewHomeScene() {
        String HomeCssPath = getClass().getResource("/style/Home.css").toString();
        var HomeTitle = new Label("Welcome to the game Tamagotchi !");
        HomeTitle.setId("home-title");
        Button playButton = new Button("Play");
        Button quitButton = new Button("Quit");
        playButton.setOnAction(e -> {
            this.stage.setScene(this.NewEggScene());
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
        Button buttonNext = new Button("Open egg");
        buttonNext.setOnAction(e-> {
            this.tamagotchi = Tools.getRandomTamagotchi();
            this.stage.setScene(this.InputBabyName());
        });
        return new Scene(new VBox(openTxtFile(eggTxtPath), buttonNext), this.width, this.height);
    }

    public Scene InputBabyName() {
        String txtPath = "/templates/" + this.tamagotchi.specie + "/baby.txt";
        TextField textField = new TextField(this.tamagotchi.specie);
        Button buttonSubmit = new Button("submit");
        Label label = new Label("Congrat you just got a " + this.tamagotchi.specie);
        Label label2 = new Label("Now give it a name !");
        buttonSubmit.setOnAction(e -> {
            this.tamagotchi.name = textField.getText();
            this.stage.setScene(Menu());
        });
        VBox body = new VBox(openTxtFile(txtPath), label, label2, textField, buttonSubmit);
        return new Scene(body, this.width, this.height);
    }

    public Scene Menu() {
        String txtPath = "/templates/" +this.tamagotchi.specie + "/"+ this.tamagotchi.lifeState +".txt";
        Label label = new Label("Your Action:");
        Button eatButton = new Button("Eat");
        eatButton.setOnAction(e -> {
            
        });
        Button playButton = new Button("Play");
        playButton.setOnAction(e -> {
            
        });
        Button cleanTamagotchiButton = new Button("Clean");
        cleanTamagotchiButton.setOnAction(e -> {
            
        });
        Button healButton = new Button("Heal");
        healButton.setOnAction(e -> {
            
        });
        Button doNothingButton = new Button("Do nothing");
        doNothingButton.setOnAction(e -> {
            
        });
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            Platform.exit();
        });
        HBox hbox = new HBox(eatButton, playButton, cleanTamagotchiButton, healButton, doNothingButton, quitButton);
        VBox body = new VBox(openTxtFile(txtPath), label, hbox);
        return new Scene(body, width, height);
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
        textArea.setPrefSize(width, height-100);
        return textArea;
    }
}