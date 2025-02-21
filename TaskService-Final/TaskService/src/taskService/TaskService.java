package taskService;

import java.util.Comparator;
import java.util.TreeMap;

public class TaskService {
    
    // TreeMaps for storing tasks with different sorting criteria
    private TreeMap<String, Task> tasksByID;
    private TreeMap<Task, String> tasksByPriority;
    private TreeMap<Task, String> tasksByDeadline;

    // Initializes the TreeMaps
    public TaskService() {
        // TreeMap for tasks sorted by taskID
        this.tasksByID = new TreeMap<>();

        // TreeMap for tasks sorted by priority, then by deadline
        this.tasksByPriority = new TreeMap<>(Comparator
            .comparingInt(Task::getTaskPriority) // Sets priority as primary comparator
            .thenComparing(Task::getTaskDeadline)); // Sets deadline as secondary comparator

        // TreeMap for tasks sorted by deadline, then by priority
        this.tasksByDeadline = new TreeMap<>(Comparator
            .comparing(Task::getTaskDeadline) // Sets deadline as primary comparator
            .thenComparingInt(Task::getTaskPriority)); // Sets priority as secondary comparator
    }

    // Method to add a task
    public void addTask(Task task) {
        if (tasksByID.containsKey(task.getTaskID())) {
            throw new IllegalArgumentException("Task ID already exists");
        }
        tasksByID.put(task.getTaskID(), task);
        tasksByPriority.put(task, task.getTaskID());
        tasksByDeadline.put(task, task.getTaskID());
    }

    // Method to delete a task
    public void deleteTask(String taskID) {
        Task task = tasksByID.get(taskID);
        if (task == null) {
            throw new IllegalArgumentException("Task ID does not exist");
        }
        tasksByID.remove(taskID);
        tasksByPriority.remove(task);
        tasksByDeadline.remove(task);
    }

    // Method to update task name
    public void updateTaskName(String taskID, String newTaskName) {
        Task task = getTask(taskID);
        task.setTaskName(newTaskName);
    }

    // Method to update task description
    public void updateTaskDescription(String taskID, String newTaskDescription) {
        Task task = getTask(taskID);
        task.setTaskDescription(newTaskDescription);
    }

    // Method to retrieve a task by ID
    public Task getTask(String taskID) {
        Task task = tasksByID.get(taskID);
        if (task == null) {
            throw new IllegalArgumentException("Task ID does not exist");
        }
        return task;
    }

    // Retrieve tasks sorted by task ID
    public void getTasksByID() {
        System.out.println("Tasks sorted by ID:");
        tasksByID.values().forEach(System.out::println);
    }

    // Retrieve tasks sorted by highest priority - then deadline
    public void getTasksByHighestPriority() {
        System.out.println("Tasks sorted by priority:");
        tasksByPriority.keySet().forEach(System.out::println);
    }

    // Retrieve tasks sorted by closest deadline - then priority
    public void getTasksByClosestDeadline() {
        System.out.println("Tasks sorted by deadline:");
        tasksByDeadline.keySet().forEach(System.out::println);
    }
}
