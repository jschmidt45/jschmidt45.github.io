package taskService;

/* Imports necessary libraries for creating a hash map, which can be used to store information
* grouped by a unique key. In this case, taskID is the unique key.
*/
import java.util.HashMap;
import java.util.Map;
		
public class TaskService {
	//Initializes the map
	private Map<String, Task> tasks;
	
    public TaskService() {
    	//Constructor
        this.tasks = new HashMap<>();
    }
    //Adds task by using the taskID, makes sure the task ID does not already exist.
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskID())) {
            throw new IllegalArgumentException("Task ID already exists");
        }
        tasks.put(task.getTaskID(), task);
    }
    //Deletes a task based off of taskID, checks to make sure the task exists.
    public void deleteTask(String taskID) {
        if (!tasks.containsKey(taskID)) {
            throw new IllegalArgumentException("Task ID does not exist");
        }
        tasks.remove(taskID);
    }
    //Updates the taskName user a setter method.
    public void updateTaskName(String taskID, String taskName) {
        Task task = getTask(taskID);
        task.setTaskName(taskName);
    }
    //Updates the taskDescription using a setter method.
    public void updateTaskDescription(String taskID, String taskDescription) {
    	Task task = getTask(taskID);
    	task.setTaskDescription(taskDescription);
    }
    //Method to obtain a task using taskID.
    public Task getTask(String taskID) {
        Task task = tasks.get(taskID);
        if (task == null) {
            throw new IllegalArgumentException("Task ID does not exist");
        }
        return task;
    }
}
