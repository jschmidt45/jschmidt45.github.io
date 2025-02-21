package test;
//Imports necessary libraries
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import taskService.TaskService;
import taskService.Task;

class TaskServiceTest {
	TaskService taskService = new TaskService();
	//Tests the AddTask method
    @Test
    void testAddTask() {
    	Task sampleTask = new Task("320", "Finish milestone", "Complete the java program with correct classes.");
        taskService.addTask(sampleTask);
        assertEquals(sampleTask, taskService.getTask("320"));
    }
    //Makes sure a task can't be created with a duplicate ID.
    @Test
    void testAddTaskWithDuplicateID() {
    	Task sampleTask = new Task("320", "Finish milestone", "Complete the java program with correct classes.");
    	Task sampleTask2 = new Task("320", "Finish journal", "Complete module 4 journal assignment");
    	taskService.addTask(sampleTask);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	taskService.addTask(sampleTask2);
        });
        assertEquals("Task ID already exists", exception.getMessage());
    }
    //Tests the delete task method.
    @Test
    void testDeleteTask() {
    	Task sampleTask = new Task("320", "Finish milestone", "Complete the java program with correct classes.");
        taskService.addTask(sampleTask);
        taskService.deleteTask("320");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.getTask("320");
        });
        assertEquals("Task ID does not exist", exception.getMessage());
    }
    //Tests that DeleteTask throws an error when the task ID does not exist.
    @Test
    void testDeleteTaskWithNonExistentID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.deleteTask("350");
        });
        assertEquals("Task ID does not exist", exception.getMessage());
    }
    //Tests the updateTaskName method.
    @Test
    void testUpdateTaskName() {
    	Task sampleTask = new Task("320", "Finish milestone", "Complete the java program with correct classes.");
        taskService.addTask(sampleTask);
        taskService.updateTaskName("320", "Finish journal");
        assertEquals("Finish journal", taskService.getTask("320").getTaskName());
    }
    //Tests the updateTaskDescription method.
    @Test
    void testUpdateTaskDescription() {
    	Task sampleTask = new Task("320", "Finish milestone", "Complete the java program with correct classes.");
        taskService.addTask(sampleTask);
        taskService.updateTaskDescription("320", "Complete module 4 journal assignment");
        assertEquals("Complete module 4 journal assignment", taskService.getTask("320").getTaskDescription());
    }
    //Tests that the helper method throws an error when searching for an invalid ID.
    @Test
    void testGetTaskWithNonExistentID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.getTask("350");
        });
        assertEquals("Task ID does not exist", exception.getMessage());
    }
}
