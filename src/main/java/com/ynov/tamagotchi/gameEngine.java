package com.ynov.tamagotchi;

import java.util.Scanner;

import java.util.concurrent.TimeUnit;

public class gameEngine {
    private enum LifeStage {
        EGG,
        BABY,
        ADULT,
        ELDERLY,
        DEAD
    }
    private LifeStage currentStage;
    private int dayCounter = 0;
    private int timeCounter = 0;
    Tamagotchi tamagotchi = new Tamagotchi();
    Scanner scanner = new Scanner(System.in);

    random instantiator = new random();
    Tamagotchi randomTamagotchi = random.getRandomTamagotchi();

    
    public void incrementDay() {
        timeCounter++;
        if (timeCounter == 2) {
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
        String name = randomTamagotchi.getClass().getSimpleName();
        System.out.println("Bienvenue dans le jeu Tamagotchi !");
        System.out.println("Nom du Tamagotchi : " + name);
        System.out.println(name + " est un œuf.");
        wait(1);
        currentStage = LifeStage.BABY;
        boolean gameRunning = true;
        System.out.println(name + " est maintenat un Bébé.");

        while (gameRunning) {
            System.out.println("\nQue voulez-vous faire avec " + name + " ?");
            System.out.println("1. Manger");
            System.out.println("2. Jouer");
            System.out.println("3. Quitter");
    
            System.out.print("Votre choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            switch (choice) {
                case 1:
                    tamagotchi.Eat();
                    break;
                case 2:
                    tamagotchi.Play();
                    break;
                case 3:
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
    
            incrementDay();
        }
    }
    



    private void checkLifeStageTransition() {
        if (tamagotchi.happiness <= 0) {
            currentStage = LifeStage.DEAD;
        } else if (currentStage == LifeStage.BABY && tamagotchi.happiness >= 40 && tamagotchi.hunger == false) {
            currentStage = LifeStage.ADULT;
        } else if (currentStage == LifeStage.ADULT && dayCounter > 15) {
            currentStage = LifeStage.ELDERLY;
        } else if (currentStage == LifeStage.ELDERLY && tamagotchi.isSick()) {
            currentStage = LifeStage.DEAD;
        }
    }

    private void restart() {
        String name = randomTamagotchi.getClass().getSimpleName();
        System.out.println(name + ": nouvel œuf.");
        currentStage = LifeStage.EGG;
        tamagotchi.happiness = 15;
        tamagotchi.hunger = false;
        tamagotchi.sick = false;
    }

    public static void main(String[] args) {
        gameEngine gameEngine = new gameEngine();
        gameEngine.startGame();
    }
}
