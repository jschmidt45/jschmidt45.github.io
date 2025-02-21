package taskService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class TaskUI {
    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);
    
    // TaskService instance to manage tasks
    private static final TaskService taskService = new TaskService();

    public static void main(String[] args) {
        // Main loop for the task manager menu
        while (true) {
            // Display the menu options
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task Name");
            System.out.println("3. Update Task Description");
            System.out.println("4. View Tasks by ID");
            System.out.println("5. View Tasks by Priority");
            System.out.println("6. View Tasks by Deadline");
            System.out.println("7. Delete Task");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            // Read user input and handle menu selection
            int choice = scanner.nextInt();
            scanner.nextLine(); // Continues to next line

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    updateTaskName();
                    break;
                case 3:
                    updateTaskDescription();
                    break;
                case 4:
                    taskService.getTasksByID();
                    break;
                case 5:
                    taskService.getTasksByHighestPriority();
                    break;
                case 6:
                    taskService.getTasksByClosestDeadline();
                    break;
                case 7:
                    deleteTask();
                    break;
                case 8:
                    System.out.println("Exiting Task Manager...");
                    scanner.close(); // Close scanner
                    return; // Exits the loop
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Calls the addTask method.
    private static void addTask() {
        System.out.print("Enter Task ID (max 10 chars): ");
        // Takes in user input
        String taskID = scanner.nextLine();

        System.out.print("Enter Task Name (max 20 chars): ");
        String taskName = scanner.nextLine();

        System.out.print("Enter Task Description (max 50 chars): ");
        String taskDescription = scanner.nextLine();

        System.out.print("Enter Task Priority (1 = High, 2 = Medium, 3 = Low): ");
        int taskPriority = scanner.nextInt();
        scanner.nextLine(); // Goes to next line

        System.out.print("Enter Task Deadline (YYYY-MM-DD): ");
        String deadlineInput = scanner.nextLine();
        Date taskDeadline = convertToDate(deadlineInput);

        // Try-catch statement for error handling
        try {
            // Create a new task
            Task task = new Task(taskID, taskName, taskDescription, taskPriority, taskDeadline);
            taskService.addTask(task);
            System.out.println("Task added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage()); // Displays error when an invalid input was entered
        }
    }

    // Method to update the task name.
    private static void updateTaskName() {
        System.out.print("Enter Task ID to update: ");
        String taskID = scanner.nextLine();

        System.out.print("Enter new Task Name: ");
        String newName = scanner.nextLine();
        // Try-catch statement for error handling
        try {
            taskService.updateTaskName(taskID, newName);
            System.out.println("Task name updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage()); // Displays error if the task ID doesn't exist or the entry is invalid.
        }
    }

    // Updates the task description
    private static void updateTaskDescription() {
        System.out.print("Enter Task ID to update: ");
        String taskID = scanner.nextLine();

        System.out.print("Enter new Task Description: ");
        String newDescription = scanner.nextLine();
        //Try-catch statement for error handling
        try {
            taskService.updateTaskDescription(taskID, newDescription);
            System.out.println("Task description updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage()); // Displays error if the task ID doesn't exist or the entry is invalid.
        }
    }

    // Method to delete a task
    private static void deleteTask() {
        System.out.print("Enter Task ID to delete: ");
        String taskID = scanner.nextLine();
      //Try-catch statement for error handling
        try {
            taskService.deleteTask(taskID);
            System.out.println("Task deleted successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage()); // Displays error if the task ID doesn't exist or the entry is invalid.
        }
    }

    // Converts the entered string to a date to be entered for the task.
    private static Date convertToDate(String dateStr) {
    	// Try-catch statement for error handling
        try {
            LocalDate localDate = LocalDate.parse(dateStr);
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            System.out.println("Invalid date format. Date must be entered as YYYY-MM-DD."); // Displays error if the entered date is in an incorrect format.
            return null;
        }
    }
}
