package com.ynov.tamagotchi;

import java.util.Random;

public class Tamagotchi {
    public String name = "";
    public String specie = "";
    public String lifeState = "baby";
    public boolean cleanness = true ;
    public boolean eat = false;
    public boolean sick = false;
    public int happiness = 15;
    public int daywhitouteating = 0;

    public void Eat(){
        this.eat = true;
        daywhitouteating = 0;
    }

    public void Play(){
        this.happiness += 3;
        if (this.happiness >= 50){
            this.happiness = 50;
        }
    }
    public void cleaning(){
        this.cleanness = true;
    }
    public void Healing(){
        this.sick = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isSick() {
        return this.sick;
    }

    public void disease(){
        Random random = new Random();
        int randomNumber = random.nextInt(3); 
        if (randomNumber == 0) {
            this.sick = true;
            System.out.println("Je suis malade :/");
        }
    }
}


