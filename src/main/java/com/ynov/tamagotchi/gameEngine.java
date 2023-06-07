package com.ynov.tamagotchi;


import java.util.Scanner;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

public class gameEngine implements Serializable {
    private enum LifeStage {
        EGG,
        BABY,
        ADULT,
        ELDERLY,
        DEAD
    }
    private int unitTime = 4;// you can change this value (this values is the number of possibel action in a day)
    List<String> Played = new ArrayList<>();
    private LifeStage currentStage;
    private int dayCounter = 0;
    private int timeCounter = 0;
    Tamagotchi tamagotchi = new Tamagotchi();
    Scanner scanner = new Scanner(System.in);

    Tools instantiator = new Tools();
    Tamagotchi randomTamagotchi = Tools.getRandomTamagotchi();

    public void incrementDay() {
        if (tamagotchi.cleanness==false){
            tamagotchi.happiness -=3;
        }
        if (tamagotchi.sick) {
            tamagotchi.happiness -= 5;
        }
        if(tamagotchi.happiness <0){
            currentStage = LifeStage.DEAD;
        }

        for (int i = Played.size(); i >= 0; i--) {
            String action = Played.get(i);
            if( action == "eat"){
                tamagotchi.cleanness=false;
            }
        }
        timeCounter++;
        if (timeCounter == unitTime) {
            if(tamagotchi.eat == false){
                tamagotchi.dayWhitOutEating += 1;
                tamagotchi.happiness -= 5*tamagotchi.dayWhitOutEating;
            }
            else{
                tamagotchi.eat = false;
            }
            if (currentStage == LifeStage.ELDERLY){
                tamagotchi.disease();
            }
            dayCounter++;
            timeCounter = 0;
            System.out.println("Jour " + dayCounter);
        }
    }

    public static void wait(int unitTime)
    {
        try {
            TimeUnit.SECONDS.sleep(1*unitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        System.out.print("\033[H\033[2J");
        String name = randomTamagotchi.specie;
        System.out.println("Bienvenue dans le jeu Tamagotchi !");
        System.out.println("Nom du Tamagotchi : " + name);
        System.out.println(name + " est un œuf.");
        wait(1);
        currentStage = LifeStage.BABY;
        boolean gameRunning = true;
        System.out.println(name + " est maintenat un Bébé.");

        while (gameRunning) {
            System.out.println("\nQue voulez-vous faire avec " + name + " ?");
            System.out.println();
            System.out.println("1. Manger");
            System.out.println("2. Jouer");
            System.out.println("3. Nettoyer");
            System.out.println("4. Soigner");
            System.out.println("5. Ne rien faire");
            System.out.println("6. Quitter");
            System.out.print("Votre choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    tamagotchi.Eat();
                    Played.add("eat");
                    System.out.println();
                    System.out.println(name+" à bien manger");
                    break;
                case 2:
                    tamagotchi.Play();
                    Played.add("play");
                    System.out.println();
                    System.out.println("à bien jouer");
                    break;
                case 3:
                    tamagotchi.cleaning();
                    Played.add("clean");
                    System.out.println();
                    System.out.println(name+" est tous propre");
                    break;
                case 4:
                    Played.add("heal");
                    if(currentStage == LifeStage.ELDERLY){
                        tamagotchi.Healing();
                    }else{
                        System.out.println();
                        System.out.println("il est en pleine santé ! pas besoin de le soigner");
                    }
                    break;
                case 5:
                    Played.add("nothing");
                    break;
                case 6:
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
            
            switch (currentStage) {
                case BABY:
                    System.out.println(name + " est un bébé.");
                    checkLifeStageTransition();
                    break;
                case ADULT:
                    System.out.println(name + " est adulte.");
                    checkLifeStageTransition();
                    break;
                case ELDERLY:
                    System.out.println(name + " est un vieillard.");
                    checkLifeStageTransition();
                    break;
                case DEAD:
                    System.out.println(name + " est mort.");
                    restart();
                    break;
                default:
                    break;
            }
            System.out.println();
            System.out.println(name + " est à : "+tamagotchi.happiness+" de bonheur");;
            incrementDay();
        }
    }

    private void checkLifeStageTransition() {
        if (tamagotchi.happiness <= 0) {
            currentStage = LifeStage.DEAD;
        } else if (currentStage == LifeStage.BABY && tamagotchi.happiness >= 40 && tamagotchi.eat == true) {
            currentStage = LifeStage.ADULT;
        } else if (currentStage == LifeStage.ADULT && dayCounter > 15) {
            currentStage = LifeStage.ELDERLY;
        } else if (currentStage == LifeStage.ELDERLY && tamagotchi.sick) {
            currentStage = LifeStage.DEAD;
        }
    }

    private void restart() {
        String name = randomTamagotchi.specie;
        System.out.println(name + ": nouvel œuf.");
        currentStage = LifeStage.EGG;
        tamagotchi.happiness = 15;
        tamagotchi.eat = false;
        tamagotchi.sick = false;
    }

    public static void main(String[] args) {
        gameEngine gameEngine = new gameEngine();
        gameEngine.startGame();
    }
}
