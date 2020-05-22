package com.hadenwatne.realrockets.storage;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StorageController {
    public static final String fileName = "data.realrockets";

    public static void saveToFile(String path, String data){
        byte[] bytes = data.getBytes();

        try {
            File f = new File(path + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(f);

            if(!f.exists())
                f.createNewFile();

            fos.write(bytes);
            fos.flush();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String loadFromFile(String path){
        try {
            File f = new File(path);

            if(!f.exists())
                f.mkdir();
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            int data;
            File f = new File(path + File.separator + fileName);
            FileInputStream fis = new FileInputStream(f);
            StringBuilder json = new StringBuilder();

            while((data = fis.read()) != -1){
                json.append((char) data);
            }

            fis.close();

            return json.toString();
        } catch (Exception e){
            Bukkit.getLogger().warning("Could not load RealRockets data file.");
        }

        return null;
    }
}
