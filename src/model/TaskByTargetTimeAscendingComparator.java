package model;

import java.util.Comparator;

public class TaskByTargetTimeAscendingComparator implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task secondTask) {
        return firstTask.getTargetTime().compareTo(secondTask.getTargetTime());
    }
}
