package com.ynov.tamagotchi;

public class tamagotchi {
    String name;
    int happiness;
    int cleanness;
    boolean hunger;
    boolean sick;

    public void Eat(){
        this.hunger = false;
    }

    public void Play(){
        this.happiness += 3;
        if (this.happiness >= 50){
            this.happiness = 50;
        }
    }

    public void Nurse(){
        this.sick = false;
    }
}


