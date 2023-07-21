package model;

import java.util.Comparator;

public class TaskByTargetTimeDescendingComparator implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task secondTask) {
        return secondTask.getTargetTime().compareTo(firstTask.getTargetTime());
    }
}
