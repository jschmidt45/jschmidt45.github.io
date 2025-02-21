package taskService;

import java.util.Date;

public class Task {
	
    // Private variables
    private final String taskID;
    private String taskName;
    private String taskDescription;
    private int taskPriority;
    private Date taskDeadline;

    // Constructors
    public Task(String taskID, String taskName, String taskDescription, int taskPriority, Date taskDeadline) {
        // Validate taskID
        if (taskID == null || taskID.length() > 10) {
            throw new IllegalArgumentException("Error: Invalid task ID");
        }
        // Ensures taskName is in expected format
        if (taskName == null || taskName.length() > 20) {
            throw new IllegalArgumentException("Error: Invalid task name.");
        }
        // Ensures taskDescription is in expected format
        if (taskDescription == null || taskDescription.length() > 50) {
            throw new IllegalArgumentException("Error: Invalid task description");
        }
        // Ensures taskPriority is 1-3
        if (taskPriority < 1 || taskPriority > 3) {
            throw new IllegalArgumentException("Priority must be 1 (High), 2 (Medium), or 3 (Low).");
        }
        // Ensures deadline is in expected format
        if (taskDeadline == null) {
            throw new IllegalArgumentException("Deadline cannot be null.");
        }

        // Instance variables
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskPriority = taskPriority;
        this.taskDeadline = new Date(taskDeadline.getTime());
    }

    // Getter methods
    public String getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public Date getTaskDeadline() {
        return new Date(taskDeadline.getTime()); // Defensive copy
    }

    // Returns priority by associated string
    public String getPriorityString() {
        switch (taskPriority) {
            case 1: return "High";
            case 2: return "Medium";
            case 3: return "Low";
            // Error handling
            default: return "Unknown";
        }
    }

    // Setter methods with validation
    // Sets taskName
    public void setTaskName(String taskName) {
        if (taskName == null || taskName.length() > 20) {
            throw new IllegalArgumentException("Error: Invalid task name.");
        }
        this.taskName = taskName;
    }

    // Sets taskDescription
    public void setTaskDescription(String taskDescription) {
        if (taskDescription == null || taskDescription.length() > 50) {
            throw new IllegalArgumentException("Error: Invalid task description");
        }
        this.taskDescription = taskDescription;
    }

    // Sets taskPriority
    public void setTaskPriority(int taskPriority) {
        if (taskPriority < 1 || taskPriority > 3) {
            throw new IllegalArgumentException("Priority must be 1 (High), 2 (Medium), or 3 (Low).");
        }
        this.taskPriority = taskPriority;
    }

    // Sets taskDeadline
    public void setTaskDeadline(Date taskDeadline) {
        if (taskDeadline == null) {
            throw new IllegalArgumentException("Deadline cannot be null.");
        }
        this.taskDeadline = new Date(taskDeadline.getTime()); // Defensive copy
    }

    // Returns task as string for console output
    @Override
    public String toString() {
        return "Task ID: " + taskID + ", Name: " + taskName + ", Priority: " + getPriorityString() +
               ", Deadline: " + taskDeadline + ", Description: " + taskDescription;
    }
}
