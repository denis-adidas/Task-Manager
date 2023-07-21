package test;

import data.JsonFileManager;
import model.AppException;
import model.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class IOTest {

    @Test
    void toJson_ValidData_SuccessfullyWritesToFile() {
        String filename = "test.json";
        TaskList taskList = new TaskList();


        JsonFileManager.toJson(filename, taskList);
    }

    @Test
    void openTaskList_NonExistingFile_ReturnsNull() {
        String filename = "nonexistent.json";

        TaskList taskList = JsonFileManager.getInstance().openTaskList(filename);

        assertNull(taskList);
    }

    @Test
    void createTaskListAndSave_NonExistingFile_CreatesAndSavesTaskList() {
        String filename = "new.json";
        String taskListName = "New Task List";


        JsonFileManager.getInstance().createTaskListAndSave(taskListName);
    }

    @Test
    void saveTaskList_ValidData_SuccessfullySavesTaskList() {
        String filename = "existing.json";
        TaskList taskList = new TaskList();
        JsonFileManager.toJson(filename, taskList);
        String taskListName = "Existing Task List";

        JsonFileManager.getInstance().saveTaskList(taskListName, taskList);
    }

    @Test
    void fromJson_ValidFile_ReturnsDeserializedObject() {
        String filename = "test.json";
        TaskList expectedTaskList = new TaskList();
        JsonFileManager.toJson(filename, expectedTaskList);

        TaskList actualTaskList = JsonFileManager.fromJson(filename, TaskList.class);

        assertNotNull(actualTaskList);
    }

    @Test
    void fromJson_NonExistingFile_ReturnsNull() {
        String filename = "nonexistent.json";

        TaskList taskList = JsonFileManager.fromJson(filename, TaskList.class);

        assertNull(taskList);
    }
    @Test
    void fromJson_InvalidDataFormat_ReturnsNull() {
        String filename = "invalidDataFormat.json";

        TaskList taskList = JsonFileManager.fromJson(filename, TaskList.class);

        assertNull(taskList);
    }
}
