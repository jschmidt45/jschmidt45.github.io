package appointmentService;

/* Imports necessary libraries for creating a hash map, which can be used to store information
* grouped by a unique key. In this case, taskID is the unique key.
*/
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class AppointmentService {
	//Initializes the map
	private Map<String, Appointment> appointments;
	
	public AppointmentService() {
		//Constructor
		this.appointments = new HashMap<>();
		
	}
	
	//Adds appointment by using the appointmentID, makes sure the appointment ID does not already exist
	public void addAppointment(Appointment appointment) {
		if (appointments.containsKey(appointment.getAppointmentID())) {
			throw new IllegalArgumentException("Appointment ID already exists");
		}
		appointments.put(appointment.getAppointmentID(), appointment);
	}
	//Deletes an appointment using the appointment ID, throws an error if the ID does not exist.
	public void deleteAppointment(String appointmentID) {
		if (!appointments.containsKey(appointmentID)) {
			throw new IllegalArgumentException("Appointment ID does not exist");
		}
		appointments.remove(appointmentID);
	}
	//Updates the appointment date. The setter method will throw an error it the date is null or in the past.
	public void updateAppointmentDate(String appointmentID, Date appointmentDate) {
		Appointment appointment = getAppointment(appointmentID);
		appointment.setAppointmentDate(appointmentDate);
	}
	//Updates the appointment description, the setter method will throw an error if the description is null or too long.
	public void updateAppointmentDescription(String appointmentID, String appointmentDescription) {
		Appointment appointment = getAppointment(appointmentID);
		appointment.setAppointmentDescription(appointmentDescription);
	}
	//Helper method for getting an appointment using appointment ID, throws and error if it does not exist.
	public Appointment getAppointment(String appointmentID) {
		Appointment appointment = appointments.get(appointmentID);
		if (appointment == null) {
			throw new IllegalArgumentException("Appointment ID does not exist");
		}
		return appointment;
	}
}
