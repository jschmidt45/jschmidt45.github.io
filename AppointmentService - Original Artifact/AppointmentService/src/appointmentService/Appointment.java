package appointmentService;
//Imports necessary library for using dates.
import java.util.Date;

public class Appointment {
	//Initializes variables
	private final String appointmentID;
	private Date appointmentDate;
	private String appointmentDescription;
	//Constructor
	public Appointment(String appointmentID, Date appointmentDate, String appointmentDescription) {
		//If statement that throws an error if the appointmentID is null or longer than 10 characters.
		if (appointmentID == null || appointmentID.length() > 10) {
			throw new IllegalArgumentException("Error: Invalid appointment ID");
		}
		//If statement that throws an error if the appointmentDate is null or before today's date.
		if (appointmentDate == null || appointmentDate.before(new Date())) {
			throw new IllegalArgumentException("Error: Invalid appointment date");
		}
		//If statement that throws an error if the appointmentDescription is null or longer than 50 characters.
		if (appointmentDescription == null || appointmentDescription.length() > 50) {
			throw new IllegalArgumentException("Error: Invalid appointment description");
		}
		
		//Assigns instance variables
		this.appointmentID = appointmentID;
		this.appointmentDate = appointmentDate;
		this.appointmentDescription = appointmentDescription;
	}
	
	//Getter Methods
	public String getAppointmentID() {
		return appointmentID;
	}
	
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	
	public String getAppointmentDescription() {
		return appointmentDescription;
	}
	//Setter methods with error handling
	public void setAppointmentDate(Date appointmentDate) {
		if (appointmentDate == null || appointmentDate.before(new Date())) {
			throw new IllegalArgumentException("Error: Invalid appointment date");
		}
		this.appointmentDate = appointmentDate;
	}
	
	public void setAppointmentDescription(String appointmentDescription) {
		if (appointmentDescription == null || appointmentDescription.length() > 50) {
			throw new IllegalArgumentException("Error: Invalid appointment description");
		}
		this.appointmentDescription = appointmentDescription;
	}
}
