import com.google.gson.Gson;
import data.JsonFileManager;
import model.Task;
import model.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        TaskList testList = new TaskList();
        String fileName = "save.json";
        testList = JsonFileManager.fromJson(fileName, TaskList.class);

        Task test = new Task(2, "test2", LocalTime.now(), LocalDate.now());
        testList.addTask(test);

        for (Object x : testList.getTasks()) {
            System.out.println(x.toString());
        }

        JsonFileManager.toJson(fileName, testList);

    }
}