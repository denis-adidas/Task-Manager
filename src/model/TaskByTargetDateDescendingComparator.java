package model;

import java.util.Comparator;

public class TaskByTargetDateDescendingComparator implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task secondTask) {
        return secondTask.getTargetDate().compareTo(firstTask.getTargetDate());
    }
}
