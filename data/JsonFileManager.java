package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.Buffer;

public class JsonFileManager {
    private static Gson createGsonWithDataFormat() {
        return new GsonBuilder().setDateFormat("dd/MM/yyyy").setPrettyPrinting().create();
    }
    public static void toJson(String filename, Object data) {
        Gson gson = createGsonWithDataFormat();
        try (FileWriter writer = new FileWriter(filename)){
            gson.toJson(data, writer);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static <T extends Object> T fromJson(String filename, Class<T> type) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            Gson gson = createGsonWithDataFormat();
            return gson.fromJson(bufferedReader, type);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
