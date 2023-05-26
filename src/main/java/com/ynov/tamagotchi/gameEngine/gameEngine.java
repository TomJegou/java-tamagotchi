package com.ynov.tamagotchi.gameEngine;

import java.util.concurrent.TimeUnit;


public class gameEngine {
    private enum etapedevie {
        EOUF,
        BEVE,
        ADULT,
        Veillard,
        MORT
    }

    private etapedevie currentStage;
    private int happiness;
    private int hunger;
    private int dirtyLevel;
    private boolean isSick;
  
    
    public static void wait(int time,int unitTime)
    {
        try {
            TimeUnit.SECONDS.sleep(time*unitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[])
    {
        // Oeuf
        System.out.println("Bienvenue sur Tamagotchi attendez que votre oeuf soit éclo");
        wait(second,1);
        System.out.println("L'oeuf à éclo");
        // bebe
        // Adult
        // Veillard
        // Mort
    }

}
