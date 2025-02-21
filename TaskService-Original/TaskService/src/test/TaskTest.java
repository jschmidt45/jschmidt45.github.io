package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taskService.Task;

class TaskTest {
	//Tests creating the new task object and the getter methods.
	@Test
	void testContactClass() {
		Task sampleTask = new Task("320", "Finish milestone", "Complete the java program with correct classes.");
		assertTrue(sampleTask.getTaskID().equals("320"));
		assertTrue(sampleTask.getTaskName().equals("Finish milestone"));
		assertTrue(sampleTask.getTaskDescription().equals("Complete the java program with correct classes."));
	}
	//Makes sure that an error is thrown if ID is null.
	@Test
	void taskIDIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(null, "Finish milestone", "Complete the java program with correct classes.");
		});		
	}
	//Makes sure that an error is thrown if the ID is too long.
	@Test
	void taskIDIsTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("IDNAMEISTOOLONG", "Finish milestone", "Complete the java program with correct classes.");
		});
	}
	//Makes sure that an error is thrown if the name is null.
	@Test
	void taskNameIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("320", null, "Complete the java program with correct classes.");
		});
	}
	//Makes sure that an error is thrown if the name is too long.
	@Test
	void taskNameIsTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("320", "Task Name is too long", "Complete the java program with correct classes.");
		});
	}
	//Makes sure that an error is thrown if the description is null.
	@Test
	void taskDescriptionIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("320", "Finish milestone", null);
		});
	}
	//Makes sure that an error is thrown if the description is too long.
	@Test
	void taskDescriptionIsTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("320", "Finish milestone", "Complete the java program with correct classes including Task, TaskService, TaskTest, and TaskServiceTest");
		});
	}
	//Tests the setter methods.
	@Test
	void testSetters() {
		Task sampleTask = new Task("320", "Finish milestone", "Complete the java program with correct classes.");
		
		sampleTask.setTaskName("320M4M");
		assertEquals("320M4M", sampleTask.getTaskName());
		
		sampleTask.setTaskDescription("Complete the test classes.");
		assertEquals("Complete the test classes.", sampleTask.getTaskDescription());
	}
}
