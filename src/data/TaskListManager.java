package data;

import model.*;

import java.util.ArrayList;
import java.util.Collections;

public class TaskListManager {
    private TaskList taskList = new TaskList();
    private final TaskQuery defaultTaskQuery = new TaskQuery("", TaskQuery.SORT_BY_TARGET_DATE, TaskQuery.SORT_ORDER_DESC, TaskQuery.FILTER_FIELD_STATUS, TaskQuery.FILTER_BY_ALL);
    private static TaskListManager instance = null;
    public static TaskListManager getInstance() {
        if (instance == null) instance = new TaskListManager();
        return instance;
    }
    private TaskListManager() {
    }
    public void newTasks() {
        taskList = new TaskList();
    }
    public void saveTasks() {
        JsonFileManager.getInstance().saveTaskList("autosave", taskList);
    }
    public void importTasks(String filename) {
        taskList = JsonFileManager.fromJson(filename, TaskList.class);

        if (taskList == null)
            taskList = new TaskList();

        taskList.getTasks().forEach((task) -> {
            task.setSelected(true);
        });
        queryTasks(getDefaultTaskQuery(), true);
    }
    public TaskList getTaskList() {
        return taskList;
    }
    public void queryTasks(TaskQuery taskQuery, boolean updateUI) {
        ArrayList<Task> tasks = taskList.getTasks();
        String textToSearch = taskQuery.getSearchText().toLowerCase();

        tasks.forEach((task) -> {
            task.setSelected(textToSearch.length() == 0 ? true : task.toString().toLowerCase().contains(textToSearch));
        });

        tasks.forEach((task) -> {
            if (task.isSelected()) {
                switch (taskQuery.getFilterName()) {
                    case TaskQuery.FILTER_FIELD_STATUS:
                        task.setSelected(taskQuery.getFilterLink().equals(TaskQuery.FILTER_BY_ALL) ? true :
                                taskQuery.getFilterLink().equals(TaskQuery.FILTER_BY_ACTIVE) ? !task.isCompleted() : task.isCompleted());
                        break;
                    case TaskQuery.FILTER_FIELD_TARGET_DATE:
                        task.setSelected((taskQuery.getFilterLink().equals(TaskQuery.FILTER_BY_ALL) ? true :
                                taskQuery.getFilterLink().equals(TaskQuery.FILTER_BY_TODAY) ? task.isDueToday(): taskQuery.getFilterLink().equals(TaskQuery.FILTER_BY_THIS_WEEK) ? task.isDueThisWeek() : taskQuery.getFilterLink().equals(TaskQuery.FILTER_BY_THIS_MONTH) ? task.isDueThisMonth() : task.isDueNextMonth()));
                        break;
                    case TaskQuery.FILTER_FIELD_ASSIGNEE:
                        task.setSelected(taskQuery.getFilterLink().equals(TaskQuery.FILTER_BY_ALL) ? true :
                                taskQuery.getFilterLink().equals(task.getAssignee()));
                        break;
                }
            }
        });
        if (taskQuery.getSortOrder().equals(TaskQuery.SORT_ORDER_ASC)) {
            switch (taskQuery.getSortBy())
            {
                case TaskQuery.SORT_BY_NAME:
                    Collections.sort(taskList.getTasks(), new TaskByNameAscendingComparator());
                    break;
                case TaskQuery.SORT_BY_ASSIGNEE:
                    Collections.sort(taskList.getTasks(), new TaskByAssigneeAscendingComparator());
                    break;
                case TaskQuery.SORT_BY_TARGET_DATE:
                    Collections.sort(taskList.getTasks(), new TaskByTargetDateAscendingComparator());
                    break;
                case TaskQuery.SORT_BY_TARGET_TIME:
                    Collections.sort(taskList.getTasks(), new TaskByTargetTimeAscendingComparator());
                    break;
            }
        }
        else {
            switch (taskQuery.getSortBy())
            {
                case TaskQuery.SORT_BY_NAME:
                    Collections.sort(taskList.getTasks(), new TaskByNameDescendingComparator());
                    break;
                case TaskQuery.SORT_BY_ASSIGNEE:
                    Collections.sort(taskList.getTasks(), new TaskByAssigneeDescendingComparator());
                    break;
                case TaskQuery.SORT_BY_TARGET_DATE:
                    Collections.sort(taskList.getTasks(), new TaskByTargetDateDescendingComparator());
                    break;
                case TaskQuery.SORT_BY_TARGET_TIME:
                    Collections.sort(taskList.getTasks(), new TaskByTargetTimeDescendingComparator());
                    break;
            }
        }
    }
    public void addTask(Task task) {
        taskList.addTask(task);
    }
    public void deleteTask(Task task) {
        taskList.removeTask(task);
    }
    public void repeatTask(Task task, int rep) {
        Task temp = task;
        if (rep == 1) {
            for (int i = 1; i < 8; i++) {
                temp = new Task(temp, task.getTargetDate().plusDays(i* 1));
                taskList.addTask(temp);
            }
        } else if (rep == 3) {
            for (int i = 0; i < 7; i++) {
                temp = new Task(temp, task.getTargetDate().plusDays(i * 3));
                taskList.addTask(temp);
            }
        } else {
            for (int i = 0; i < 7; i++) {
                temp = new Task(temp, task.getTargetDate().plusDays(i* 7));
                taskList.addTask(temp);
            }
        }
    }
    public ArrayList<String> getAssigneeList(boolean selectedOnly) {
        ArrayList<String> assigneeList = new ArrayList<>();
        for(Task task : taskList.getTasks()) {
            if(!assigneeList.contains(task.getAssignee()) && (!selectedOnly || task.isSelected())) {
                assigneeList.add(task.getAssignee());
            }
        }
        return assigneeList;
    }
    public TaskList getCompletedTask() {
        TaskList temp = new TaskList();
        for (Task x : taskList.getTasks()){
            if (x.isCompleted())
                temp.addTask(x);
        }
        return temp;
    }
    
    public TaskList getAssigneeList(String assig) {
        TaskList temp = new TaskList();
        for (Task x : taskList.getTasks()){
            if ( x.getAssignee() != null)
                if (x.getAssignee().equals(assig))
                    temp.addTask(x);
        }
        return temp;
    }
    
    public TaskList searchText(String text) {
        TaskList searchTask = new TaskList();
        for (Task x : taskList.getTasks()) {
            if (x.getName().contains(text)) {
                searchTask.addTask(x);
            }
        }
        return searchTask;
    }


    public TaskQuery getDefaultTaskQuery() {
        return defaultTaskQuery;
    }
}
