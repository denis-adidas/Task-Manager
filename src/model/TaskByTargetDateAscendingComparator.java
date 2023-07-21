package model;

import java.util.Comparator;

public class TaskByTargetDateAscendingComparator implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task secondTask) {
        return firstTask.getTargetDate().compareTo(secondTask.getTargetDate());
    }
}
