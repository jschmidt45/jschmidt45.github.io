package taskService;

public class Task {
	
	//Initializes private variables
	private final String taskID;
	private String taskName;
	private String taskDescription;
	
	
	//Constructor
	public Task(String taskID, String taskName, String taskDescription) {
		//Throws an error if taskID is null or has a length over 10
		if (taskID == null || taskID.length() > 10) {
			throw new IllegalArgumentException("Error: Invalid task ID");	
		}
		//Throws an error if taskName is null or has a length over 20
		if (taskName == null || taskName.length() > 20) {
			throw new IllegalArgumentException("Error: Invalid task name.");
		}
		//Throws an error if taskDescription is null or has a length over 50
		if (taskDescription == null || taskDescription.length() > 50) {
			throw new IllegalArgumentException("Inalid task description");
		}
		
		//Assigns instance variables
		this.taskID = taskID;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		
	}
	//Getter methods
	public String getTaskID() {
		return taskID;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public String getTaskDescription() {
		return taskDescription;
	}
	//Setter methods with error handling
	public void setTaskName(String taskName) {
		if (taskName == null || taskName.length() > 20) {
			throw new IllegalArgumentException("Error: Invalid task name.");
		}
		this.taskName = taskName;
	}
	
	public void setTaskDescription(String taskDescription) {
		if (taskDescription == null || taskDescription.length() > 50) {
			throw new IllegalArgumentException("Inalid task description");
		}
		this.taskDescription = taskDescription;
	}
}
