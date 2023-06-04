package com.ynov.tamagotchi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deseria {
    public static Object deserializeData(String DataSer) {
        Object data = null;

        try (FileInputStream fileIn = new FileInputStream(DataSer);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            data = objectIn.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }
}
