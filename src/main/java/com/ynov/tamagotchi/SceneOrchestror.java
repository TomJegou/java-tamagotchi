package com.ynov.tamagotchi;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.application.Platform;

public class SceneOrchestror {
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
            File saveFile = new File("save");
            if (saveFile.exists()) {
                this.tamagotchi = (Tamagotchi) Save.deserializeData("save");
                if (this.tamagotchi.isDead) {
                    this.stage.setScene(this.NewEggScene());
                }
                this.stage.setScene(this.Menu());
            } else {
                this.stage.setScene(this.NewEggScene());
            }
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
        Label lifeStateLabel = new Label("Age: " + this.tamagotchi.lifeState);
        Label hapinessLabel = new Label("Hapiness: " + Integer.toString(this.tamagotchi.happiness));
        Label nameLabel = new Label("Name: " + this.tamagotchi.name);
        Label stateHunger;
        Label sickLabel;
        Label cleanessLabel;
        Label dayLivedLabel = new Label(this.tamagotchi.name + " has lived " + this.tamagotchi.dayLived);
        if (this.tamagotchi.daywhitouteating > 1) {
            stateHunger = new Label(this.tamagotchi.name + " is hungry");
        } else {
            stateHunger = new Label(this.tamagotchi.name +  " is not Hungry");
        }
        if (this.tamagotchi.sick) {
            sickLabel = new Label(this.tamagotchi.name + " is sick");
        } else {
            sickLabel = new Label(this.tamagotchi.name + " is in good Health");
        }
        if (this.tamagotchi.cleanness) {
            cleanessLabel = new Label(this.tamagotchi.name + " is clean");
        } else {
            cleanessLabel = new Label(this.tamagotchi.name + " is dirty");
        }
        VBox vBoxInfoTamagotchi = new VBox(lifeStateLabel, hapinessLabel, nameLabel, stateHunger, sickLabel, cleanessLabel, dayLivedLabel);
        Button eatButton = new Button("Eat");
        eatButton.setOnAction(e -> {
            this.tamagotchi.Eat();
            this.tamagotchi.getHold();
            if (this.tamagotchi.isDead) {
                this.stage.setScene(this.newDeadScene());
                return;
            }
            this.stage.setScene(this.Menu());
        });
        Button playButton = new Button("Play");
        playButton.setOnAction(e -> {
            this.tamagotchi.Play();
            this.tamagotchi.getHold();
            if (this.tamagotchi.isDead) {
                this.stage.setScene(this.newDeadScene());
                return;
            }
            this.stage.setScene(this.Menu());
        });
        Button cleanTamagotchiButton = new Button("Clean");
        cleanTamagotchiButton.setOnAction(e -> {
            this.tamagotchi.cleaning();
            this.tamagotchi.getHold();
            if (this.tamagotchi.isDead) {
                this.stage.setScene(this.newDeadScene());
                return;
            }
            this.stage.setScene(this.Menu());
        });
        Button healButton = new Button("Heal");
        healButton.setOnAction(e -> {
            this.tamagotchi.Healing();
            this.tamagotchi.getHold();
            if (this.tamagotchi.isDead) {
                this.stage.setScene(this.newDeadScene());
                return;
            }
            this.stage.setScene(this.Menu());
        });
        Button doNothingButton = new Button("Do nothing");
        doNothingButton.setOnAction(e -> {
            this.tamagotchi.getHold();
            if (this.tamagotchi.isDead) {
                this.stage.setScene(this.newDeadScene());
                return;
            }
            this.stage.setScene(this.Menu());
        });
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            Save.serializeData(this.tamagotchi, "save");
            Platform.exit();
        });
        HBox hbox = new HBox(eatButton, playButton, cleanTamagotchiButton, healButton, doNothingButton, quitButton);
        VBox body = new VBox(openTxtFile(txtPath), vBoxInfoTamagotchi, label, hbox);
        return new Scene(body, width, height);
    }

    public Scene newDeadScene() {
        Label deadMessage = new Label(this.tamagotchi.name + "is Dead sorry, start a new game !");
        Button quiButton = new Button("Quit");
        quiButton.setOnAction(e -> {
            Platform.exit();
        });
        VBox vbox = new VBox(deadMessage, quiButton);
        return new Scene(vbox, width, height);
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
