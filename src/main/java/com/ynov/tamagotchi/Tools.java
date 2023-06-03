package com.ynov.tamagotchi;

import java.util.Random;

public class Tools{
    public static void main(String[] args) {
        Tamagotchi randomTamagotchi = getRandomTamagotchi();
        System.out.println("Random Tamagotchi: " + randomTamagotchi.getClass().getSimpleName());
    }

    public static Tamagotchi getRandomTamagotchi() {
        int randomChoice = getRandom();

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

    public static int getRandom() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
}
