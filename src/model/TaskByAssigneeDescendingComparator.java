package model;

import java.util.Comparator;


public class TaskByAssigneeDescendingComparator implements Comparator<Task> {
  
    @Override
    public int compare(Task firstTask, Task secondTask) {
       return secondTask.getAssignee().compareTo(firstTask.getAssignee());
    }
}
