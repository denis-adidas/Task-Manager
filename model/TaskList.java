package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TaskList {
    @SerializedName("tasks")
    @Expose
    private final ArrayList<Task> tasks = new ArrayList<>();

    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }
    public void removeTask(Task task) {
        tasks.remove(task);
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
