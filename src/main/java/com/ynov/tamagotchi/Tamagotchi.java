package com.ynov.tamagotchi;

import java.io.Serializable;
import java.util.Random;

public class Tamagotchi implements Serializable {
    public String name = "";
    public String specie = "";
    public String lifeState = LifeStateEnum.baby.toString();
    public boolean cleanness = true;
    public boolean eat = false;
    public boolean sick = false;
    public boolean isDead = false;
    public int happiness = 15;
    public int dayWhitOutEating = 0;
    public int dayWithoutCleaning = 0;
    public int dayLivedAsAdult = 0;
    public int dayLivedAsOld = 0;

    public void Eat() {
        this.eat = true;
        dayWhitOutEating = 0;
    }

    public void Play() {
        this.happiness += 3;
        if (this.happiness >= 50){
            this.happiness = 50;
        }
    }

    public void cleaning(){
        this.cleanness = true;
        this.dayWithoutCleaning = 0;
    }

    public void Healing(){
        this.sick = false;
    }

    public void getOld() {
        this.dayWhitOutEating++;
        this.dayWithoutCleaning++;
        if (this.happiness >= 40 && this.lifeState  == LifeStateEnum.baby.toString()) {
            this.lifeState = LifeStateEnum.adult.toString();
        } else if (this.lifeState == LifeStateEnum.adult.toString()) {
            if (this.dayLivedAsAdult >= 15) {
                this.lifeState = LifeStateEnum.old.toString();
            }
            this.dayLivedAsAdult++;
        } else if (this.lifeState == LifeStateEnum.old.toString()) {
            if (this.dayLivedAsOld >= 5) {
                this.isDead = true;
                return;
            }
            this.dayLivedAsOld++;
            this.disease();
        }
        if (this.dayWhitOutEating > 3) {
            this.happiness -= 5;
        }
        if (this.dayWithoutCleaning > 2) {
            this.happiness--;
            this.cleanness = false;
        }
        if (this.happiness <= 0) {
            this.isDead = true;
        }
    }

    public void disease() {
        Random random = new Random();
        int randomNumber = random.nextInt(3); 
        if (randomNumber == 0) {
            this.sick = true;
            System.out.println("Je suis malade :/");
        }
    }
}


