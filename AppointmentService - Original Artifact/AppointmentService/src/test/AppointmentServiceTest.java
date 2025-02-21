package test;
//Imports necessary libraries
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import appointmentService.AppointmentService;
import appointmentService.Appointment;

class AppointmentServiceTest {
	AppointmentService appointmentService = new AppointmentService();
	//Tests the addAppointment method.
	@Test
	void testAddAppointment() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		Appointment sampleAppointment = new Appointment("320", date1, "Time to complete class homework.");
		appointmentService.addAppointment(sampleAppointment);
		assertEquals(sampleAppointment, appointmentService.getAppointment("320"));
	}
	//Makes sure an appointment can't be added with a duplicate ID.
	@Test
	void testAddAppointmentWithDuplicateID() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		Appointment sampleAppointment = new Appointment("320", date1, "Time to complete class homework.");
		Appointment sampleAppointment2 = new Appointment("320", date1, "Time to complete class homework.");
		appointmentService.addAppointment(sampleAppointment);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			appointmentService.addAppointment(sampleAppointment2);
        });
        assertEquals("Appointment ID already exists", exception.getMessage());
	}
	//Tests the delete appointment method.
	@Test
	void testDeleteAppointment() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		Appointment sampleAppointment = new Appointment("320", date1, "Time to complete class homework.");
		appointmentService.addAppointment(sampleAppointment);
		appointmentService.deleteAppointment("320");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.getAppointment("320");
        });
        assertEquals("Appointment ID does not exist", exception.getMessage());
	}
	//Tests that delete appointment throws an error if the appointment ID does not exist
	@Test
	void testDeleteAppointmentWithNonExistenID() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			appointmentService.deleteAppointment("350");
		});
		assertEquals("Appointment ID does not exist", exception.getMessage());
	}
	//Tests that the update appointment method works correctly
	@Test
	void testUpdateAppointment() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		calendar.set(2024, Calendar.JULY, 30, 12, 0, 0);
		Date date2 = calendar.getTime();
		Appointment sampleAppointment = new Appointment("320", date1, "Time to complete class homework.");
		appointmentService.addAppointment(sampleAppointment);
		appointmentService.updateAppointmentDate("320", date2);
		assertEquals(date2, sampleAppointment.getAppointmentDate());
	}
	//Tests that the update appointment description works correctly
	@Test
	void testUpdateAppointmentDescription() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		Appointment sampleAppointment = new Appointment("320", date1, "Time to complete class homework.");
		appointmentService.addAppointment(sampleAppointment);
		appointmentService.updateAppointmentDescription("320", "Finish the current milestone.");
		assertEquals("Finish the current milestone.", sampleAppointment.getAppointmentDescription());
	}
	//Tests that the helper method throws an error when searching for an invalid ID
	@Test
	void testGetAppointmentWithNonExistentID() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			appointmentService.getAppointment("350");
		});
		assertEquals("Appointment ID does not exist", exception.getMessage());
	}
}
