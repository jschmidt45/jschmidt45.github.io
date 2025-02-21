package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import taskService.Task;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

class TaskTest {
    
    // Converts LocalDate to Date
    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // Tests creating the new task object and the getter methods.
    @Test
    void testTaskClass() {
        Date deadline = convertToDate(LocalDate.of(2025, 1, 15));
        Task sampleTask = new Task("320", "Finish milestone", "Complete the java program.", 1, deadline);
        
        assertEquals("320", sampleTask.getTaskID());
        assertEquals("Finish milestone", sampleTask.getTaskName());
        assertEquals("Complete the java program.", sampleTask.getTaskDescription());
        assertEquals("High", sampleTask.getPriorityString());
        assertEquals(deadline, sampleTask.getTaskDeadline());
    }

    @Test
    void taskIDIsNull() {
        Date deadline = convertToDate(LocalDate.of(2025, 1, 15));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Finish milestone", "Complete the java program.", 1, deadline);
        });
    }

    @Test
    void taskIDIsTooLong() {
        Date deadline = convertToDate(LocalDate.of(2025, 1, 15));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Task("IDNAMEISTOOLONG", "Finish milestone", "Complete the java program.", 1, deadline);
        });
    }

    @Test
    void taskPriorityIsInvalid() {
        Date deadline = convertToDate(LocalDate.of(2025, 1, 15));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Task("320", "Finish milestone", "Complete the java program.", 4, deadline);
        });
    }

    @Test
    void taskDeadlineIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Task("320", "Finish milestone", "Complete the java program.", 1, null);
        });
    }

    @Test
    void testSetters() {
        Date deadline = convertToDate(LocalDate.of(2025, 1, 15));
        Task sampleTask = new Task("320", "Finish milestone", "Complete the java program.", 1, deadline);

        sampleTask.setTaskName("New Task Name");
        assertEquals("New Task Name", sampleTask.getTaskName());

        sampleTask.setTaskDescription("Updated description.");
        assertEquals("Updated description.", sampleTask.getTaskDescription());

        sampleTask.setTaskPriority(2);
        assertEquals("Medium", sampleTask.getPriorityString());

        Date newDeadline = convertToDate(LocalDate.of(2025, 2, 10));
        sampleTask.setTaskDeadline(newDeadline);
        assertEquals(newDeadline, sampleTask.getTaskDeadline());
    }
}
