package com.ynov.tamagotchi;

import com.ynov.tamagotchi.Tamagotchi;
import java.util.Scanner;

public class start {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu Tamagotchi !");
        System.out.print("Entrez le nom de votre Tamagotchi : ");
        String tamagotchiName = scanner.nextLine();

        Tamagotchi tamagotchi = new Tamagotchi();
        tamagotchi.setName(tamagotchiName);

        boolean gameRunning = true;
        while (gameRunning) {
            System.out.println("\nQue voulez-vous faire avec " + tamagotchi.getName() + " ?");
            System.out.println("1. Manger");
            System.out.println("2. Jouer");
            System.out.println("3. Quitter");

            System.out.print("Votre choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    tamagotchi.Eat();
                    System.out.println(tamagotchi.getName() + " a mangé !");
                    break;
                case 2:
                    tamagotchi.Play();
                    System.out.println(tamagotchi.getName() + " a joué !");
                    break;
                case 3:
                    gameRunning = false;
                    System.out.println("Merci d'avoir joué !");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }

            System.out.println("Bonheur : " + tamagotchi.getHappiness());
            System.out.println("Faim : " + tamagotchi.getHunger());
            System.out.println("Malade : " + tamagotchi.isSick());
        }

        scanner.close();
    }
}