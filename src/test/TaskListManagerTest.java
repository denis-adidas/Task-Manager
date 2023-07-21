package test;
import data.TaskListManager;
import model.Task;
import model.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListManagerTest {
    private TaskListManager taskListManager;

    @BeforeEach
    public void setUp() {
        taskListManager = TaskListManager.getInstance();
    }

    @Test
    public void testNewTasks() {
        taskListManager.newTasks();
        TaskList taskList = taskListManager.getTaskList();
        assertNotNull(taskList);
        assertTrue(taskList.getTasks().isEmpty());
    }

    @Test
    public void testAddTask() {
        Task task = new Task(1, "Task 1", LocalTime.now(), LocalDate.now());
        taskListManager.addTask(task);
        TaskList taskList = taskListManager.getTaskList();
        assertTrue(taskList.getTasks().contains(task));
        taskListManager.deleteTask(task);
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task(1, "Task 1", LocalTime.now(), LocalDate.now());
        taskListManager.addTask(task);
        taskListManager.deleteTask(task);
        TaskList taskList = taskListManager.getTaskList();
        assertFalse(taskList.getTasks().contains(task));
        taskListManager.deleteTask(task);
    }

    @Test
    public void testRepeatTask() {
        Task task = new Task(1, "Repeat task", LocalTime.now(), LocalDate.now());
        taskListManager.addTask(task);
        taskListManager.repeatTask(task, 3);
        TaskList taskList = taskListManager.getTaskList();
        assertEquals(8, taskList.getTasks().size());
    }

    @Test
    public void testGetAssigneeList() {
        Task task1 = new Task(1, "Task 1", LocalTime.now(), LocalDate.now());
        task1.setAssignee("John");
        Task task2 = new Task(1, "Task 2", LocalTime.now(), LocalDate.now());
        task2.setAssignee("Jane");
        Task task3 = new Task(1, "Task 3", LocalTime.now(), LocalDate.now());
        task3.setAssignee("John");
        taskListManager.addTask(task1);
        taskListManager.addTask(task2);
        taskListManager.addTask(task3);


        assertEquals(2, taskListManager.getAssigneeList(false).size());
        assertTrue(taskListManager.getAssigneeList(false).contains("John"));
        assertTrue(taskListManager.getAssigneeList(false).contains("Jane"));

        taskListManager.deleteTask(task1);
        taskListManager.deleteTask(task2);
        taskListManager.deleteTask(task3);
    }

    @Test
    public void testGetCompletedTask() {
        Task task1 = new Task(1, "Task 1", LocalTime.now(), LocalDate.now());
        task1.setCompleted(true);
        Task task2 = new Task(1, "Task 2", LocalTime.now(), LocalDate.now());
        task2.setCompleted(false);
        Task task3 = new Task(1, "Task 3", LocalTime.now(), LocalDate.now());
        task3.setCompleted(true);
        taskListManager.addTask(task1);
        taskListManager.addTask(task2);
        taskListManager.addTask(task3);

        TaskList completedTasks = taskListManager.getCompletedTask();
        assertEquals(2, completedTasks.getTasks().size());
        assertTrue(completedTasks.getTasks().contains(task1));
        assertTrue(completedTasks.getTasks().contains(task3));

        taskListManager.deleteTask(task1);
        taskListManager.deleteTask(task2);
        taskListManager.deleteTask(task3);
    }

    @Test
    public void testGetAssigneeListByName() {
        Task task1 = new Task(1, "Task 1", LocalTime.now(), LocalDate.now());
        task1.setAssignee("John");
        Task task2 = new Task(1, "Task 2", LocalTime.now(), LocalDate.now());
        task2.setAssignee("Jane");
        Task task3 = new Task(1, "Task 3", LocalTime.now(), LocalDate.now());
        task3.setAssignee("John");
        taskListManager.addTask(task1);
        taskListManager.addTask(task2);
        taskListManager.addTask(task3);

        TaskList assigneeTasks = taskListManager.getAssigneeList("John");
        assertEquals(2, assigneeTasks.getTasks().size());
        assertTrue(assigneeTasks.getTasks().contains(task1));
        assertTrue(assigneeTasks.getTasks().contains(task3));
        taskListManager.deleteTask(task1);
        taskListManager.deleteTask(task2);
        taskListManager.deleteTask(task3);
    }

    @Test
    public void testSearchText() {
        Task task1 = new Task(1, "Task 1", LocalTime.now(), LocalDate.now());
        task1.setName("Meeting");
        Task task2 = new Task(1, "Task 2", LocalTime.now(), LocalDate.now());
        task2.setName("Presentation");
        Task task3 = new Task(1, "Task 3", LocalTime.now(), LocalDate.now());
        task3.setName("Meeting with clients");
        taskListManager.addTask(task1);
        taskListManager.addTask(task2);
        taskListManager.addTask(task3);

        TaskList searchResult = taskListManager.searchText("Meeting");
        assertEquals(2, searchResult.getTasks().size());
        assertTrue(searchResult.getTasks().contains(task1));
        assertTrue(searchResult.getTasks().contains(task3));

        taskListManager.deleteTask(task1);
        taskListManager.deleteTask(task2);
        taskListManager.deleteTask(task3);
    }
}
