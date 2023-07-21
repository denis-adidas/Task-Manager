package model;

import java.util.Comparator;

public class TaskByAssigneeAscendingComparator implements Comparator<Task> {
  
    @Override
    public int compare(Task firstTask, Task secondTask) {
       return firstTask.getAssignee().compareTo(secondTask.getAssignee());
    }
}
