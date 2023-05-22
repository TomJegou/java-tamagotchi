package com.ynov.tamagotchi.gameEngine;

import java.util.concurrent.TimeUnit;

public class gameEngine {
    final static int oneUnitTimeInSecond = 1 ;
    
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
        System.out.println("Hello World");
        wait(oneUnitTimeInSecond,1);
        System.out.println("Hello World");
    }

}
