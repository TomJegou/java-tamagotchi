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

    public String getName() {
        return this.name;
    }

    public int getHappiness() {
        return this.happiness;
    }

    public boolean getHunger() {
        return this.hunger;
    }

    public boolean isSick() {
        return this.sick;
    }
}


