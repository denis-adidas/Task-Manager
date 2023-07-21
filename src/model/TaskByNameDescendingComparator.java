package model;

import java.util.Comparator;

public class TaskByNameDescendingComparator implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task secondTask) {
        return secondTask.getName().compareTo(firstTask.getName());
    }
}
