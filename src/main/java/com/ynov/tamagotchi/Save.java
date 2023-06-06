package com.ynov.tamagotchi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Save {
    String savePath = getClass().getResourceAsStream(null).toString();
    public static void serializeData(Object data, String DataSer) {
        try{
            FileOutputStream fileOut = new FileOutputStream(DataSer);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(data);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserializeData(String DataSer) {
        Object data = null;
        try { 
            FileInputStream fileIn = new FileInputStream(DataSer);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            data = objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}