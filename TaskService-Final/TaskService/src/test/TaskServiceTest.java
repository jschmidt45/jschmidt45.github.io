package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import taskService.TaskService;
import taskService.Task;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

class TaskServiceTest {
    TaskService taskService = new TaskService();

    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Test
    void testAddTask() {
        Date deadline = convertToDate(LocalDate.of(2025, 1, 15));
        Task sampleTask = new Task("320", "Finish milestone", "Complete the java program.", 1, deadline);
        taskService.addTask(sampleTask);
        assertEquals(sampleTask, taskService.getTask("320"));
    }

    @Test
    void testAddTaskWithDuplicateID() {
        Date deadline1 = convertToDate(LocalDate.of(2025, 1, 15));
        Date deadline2 = convertToDate(LocalDate.of(2025, 1, 20));

        Task sampleTask = new Task("320", "Finish milestone", "Complete the java program.", 1, deadline1);
        Task sampleTask2 = new Task("320", "Finish journal", "Complete module 4 journal.", 2, deadline2);

        taskService.addTask(sampleTask);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask(sampleTask2);
        });
        assertEquals("Task ID already exists", exception.getMessage());
    }

    @Test
    void testDeleteTask() {
        Date deadline = convertToDate(LocalDate.of(2025, 1, 15));
        Task sampleTask = new Task("320", "Finish milestone", "Complete the java program.", 1, deadline);
        taskService.addTask(sampleTask);
        taskService.deleteTask("320");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.getTask("320");
        });
        assertEquals("Task ID does not exist", exception.getMessage());
    }

    @Test
    void testUpdateTaskName() {
        Date deadline = convertToDate(LocalDate.of(2025, 1, 15));
        Task sampleTask = new Task("320", "Finish milestone", "Complete the java program.", 1, deadline);
        taskService.addTask(sampleTask);
        taskService.updateTaskName("320", "Finish journal");
        assertEquals("Finish journal", taskService.getTask("320").getTaskName());
    }

    @Test
    void testUpdateTaskDescription() {
        Date deadline = convertToDate(LocalDate.of(2025, 1, 15));
        Task sampleTask = new Task("320", "Finish milestone", "Complete the java program.", 1, deadline);
        taskService.addTask(sampleTask);
        taskService.updateTaskDescription("320", "Complete module 4 journal.");
        assertEquals("Complete module 4 journal.", taskService.getTask("320").getTaskDescription());
    }

    @Test
    void testGetTasksByHighestPriority() {
        Task highPriority = new Task("1", "Urgent Task", "Complete now.", 1, convertToDate(LocalDate.of(2025, 1, 15)));
        Task mediumPriority = new Task("2", "Moderate Task", "Complete soon.", 2, convertToDate(LocalDate.of(2025, 1, 18)));

        taskService.addTask(highPriority);
        taskService.addTask(mediumPriority);

        taskService.getTasksByHighestPriority();
    }

    @Test
    void testGetTasksByClosestDeadline() {
        Task earlyDeadline = new Task("1", "Early Task", "Complete ASAP.", 2, convertToDate(LocalDate.of(2025, 1, 10)));
        Task laterDeadline = new Task("2", "Later Task", "Complete later.", 1, convertToDate(LocalDate.of(2025, 1, 20)));

        taskService.addTask(earlyDeadline);
        taskService.addTask(laterDeadline);

        taskService.getTasksByClosestDeadline();
    }
}
