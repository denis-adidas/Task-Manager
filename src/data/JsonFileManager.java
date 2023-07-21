package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.AppException;
import model.TaskList;

import java.io.*;

public class JsonFileManager {
    private final static String FILENAME_PREFIX = ".json";
    private static JsonFileManager instance = null;
    
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
    public static JsonFileManager getInstance() {
        if(instance == null) instance = new JsonFileManager();
        return instance;
    }
    public TaskList openTaskList(String name) {
        String filename = name + FILENAME_PREFIX;
        File dataFile = new File(filename);
        if(!dataFile.exists()) {
            return null;
        }
        return JsonFileManager.fromJson(filename, TaskList.class);
    }

    public void createTaskListAndSave(String name) {
        String filename = name + FILENAME_PREFIX;
        File dataFile = new File(filename);
        if(!dataFile.exists()) {
            JsonFileManager.toJson(filename, new TaskList());
        }
        else {
            throw new AppException("User data file already exists");
        }
    }
    public void saveTaskList(String name, TaskList taskList) {
        String userFilename = name + FILENAME_PREFIX;
        JsonFileManager.toJson(userFilename, taskList);
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
