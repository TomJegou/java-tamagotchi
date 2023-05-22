package com.ynov.tamagotchi.gameEngine;

import java.util.concurrent.TimeUnit;

public class gameEngine {
    final static int oneUnitTime = 1 ;
    


    public static void main(String args[])
    {
        System.out.println("Hello World");
        try {
            TimeUnit.SECONDS.sleep(oneUnitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        System.out.println("Hello World");
    }


}
