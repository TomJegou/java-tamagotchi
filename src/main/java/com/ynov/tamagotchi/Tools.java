package com.ynov.tamagotchi;

import java.util.Random;

public class Tools {
    
    public static Tamagotchi getRandomTamagotchi() {
        int randomChoice = getRandom(3);

        Tamagotchi tamagotchi;  

        switch (randomChoice) {
            case 1:
                tamagotchi = new Eagle();
                break;
            case 2:
                tamagotchi = new Lion();
                break;
            case 3:
                tamagotchi = new Whale();
                break;
            default:
                throw new IllegalStateException("erreur");
        }

        return tamagotchi;
    }

    public static int getRandom(int max) {
        Random random = new Random();
        return random.nextInt(max) + 1;
    }
}
