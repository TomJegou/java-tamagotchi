package com.ynov.tamagotchi.gameEngine;

import com.ynov.tamagotchi.Tamagotchi;
import com.ynov.tamagotchi.tamagotchi;

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
    private static int second = 1;


    public static void wait(int unitTime)
    {
        try {
            TimeUnit.SECONDS.sleep(second*unitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        System.out.println("Le jeu Tamagotchi commence avec " + tamagotchi.name + "!");

        while (true) {
            switch (currentStage) {
                case EGG:
                    System.out.println(tamagotchi.name + " est encore un œuf.");
                    wait(1);
                    this.currentStage = LifeStage.EGG;
                    
                    break;
                case BABY:
                    System.out.println(tamagotchi.name + " est un bébé.");
                    tamagotchi.feed();
                    tamagotchi.play();
                    checkLifeStageTransition();
                    break;
                case ADULT:
                    System.out.printlntamagotchi.name + " est adulte.");
                    tamagotchi.feed();
                    tamagotchi.play();
                    checkLifeStageTransition();
                    break;
                case ELDERLY:
                    System.out.println(tamagotchi.name + " est un vieillard.");
                    tamagotchi.feed();
                    tamagotchi.clean();
                    if (isSick) {
                        heal();
                    }
                    checkLifeStageTransition();
                    break;
                case DEAD:
                    System.out.println(tamagotchi.name + " est mort.");
                    restart();
                    break;
            }

            wait(1);
        }
    }



    private void checkLifeStageTransition() {
        if (tamagotchi.happiness <= 0) {
            currentStage = LifeStage.DEAD;
        } else if (currentStage == LifeStage.BABY && tamagotchi.happiness >= 40 && tamagotchi.hunger == 0) {
            currentStage = LifeStage.ADULT;
        } else if (currentStage == LifeStage.ADULT && tamagotchi.hunger >= 15) {
            currentStage = LifeStage.ELDERLY;
        } else if (currentStage == LifeStage.ELDERLY && tamagotchi.isSick) {
            currentStage = LifeStage.DEAD;
        }
    }

    private void restart() {
        System.out.println(tamagotchi.name + " est remplacé par un nouvel œuf.");
        currentStage = LifeStage.EGG;
        tamagotchi.happiness = 15;
        tamagotchi.cleanliness = 0;
        tamagotchi. hunger = 0;
        tamagotchi.isSick = false;
    }

    public static void main(String[] args) {
        Tamagotchi tamagotchi = new Tamagotchi("testtama");
        gameEngine.startGame();
    }
}
