package com.ynov.tamagotchi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Seria {
    public static void serializeData(Object data, String DataSer) {
        try (FileOutputStream fileOut = new FileOutputStream(DataSer);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}