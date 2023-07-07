package model;

import java.util.Comparator;

public class TaskByNameAscendingComparator implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task secondTask) {
        return firstTask.getName().compareTo(secondTask.getName());
    }
}
