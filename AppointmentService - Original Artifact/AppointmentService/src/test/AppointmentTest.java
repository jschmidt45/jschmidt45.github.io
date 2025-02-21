package test;
//Imports necessary libraries
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Calendar;
import appointmentService.Appointment;



class AppointmentTest {
	//Tests creating the new appointment object and the getter methods.
	@Test
	void testAppointmentClass() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		Appointment sampleAppointment = new Appointment("320", date1, "Time to complete class homework.");
		assertTrue(sampleAppointment.getAppointmentID().equals("320"));
		assertEquals(date1, sampleAppointment.getAppointmentDate());
		assertTrue(sampleAppointment.getAppointmentDescription().equals("Time to complete class homework."));				
	}
	//Makes sure an error is thrown if the ID is null.
	@Test
	void appoinmentIDIsNull() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(null, date1, "Time to complete class homework.");
		});	
	}
	//Makes sure an error is thrown if the ID is too long.
	@Test
	void appointmentIDIsTooLong() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("AppointmentIDIsTooLong", date1, "Time to complete class homework.");
		});	
	}
	//Makes sure an error is thrown if the date is in the past.
	@Test
	void appointmentDateIsInThePast() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 1, 1, 0, 0);
		Date date1 = calendar.getTime();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("320", date1, "Time to complete class homework.");
		});	
	}
	//Makes sure an error is thrown if the date is null.
	@Test
	void appointmentDateIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("320", null, "Time to complete class homework.");
		});	
	}
	//Makes sure an error is thrown if the entered description is too long.
	@Test
	void appointmentDescriptionIsTooLong() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("320", date1, "Time to complete class homework, including items such as the milestone, journal, and starting on the project.");
		});	
	}
	//Makes sure an error is thrown if the description is null.
	@Test
	void appointmentDescriptionIsNull() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("320", date1, null);
		});	
	}
	//Makes sure the setter methods work correctly.
	@Test
	void testSetters() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.JUNE, 30, 12, 0, 0);
		Date date1 = calendar.getTime();
		calendar.set(2024, Calendar.JULY, 30, 12, 0, 0);
		Date date2 = calendar.getTime();
		Appointment sampleAppointment = new Appointment("320", date1, "Time to complete class homework.");
		
		sampleAppointment.setAppointmentDate(date2);
		assertEquals(date2, sampleAppointment.getAppointmentDate());
		
		sampleAppointment.setAppointmentDescription("Complete current milestone");
		assertEquals("Complete current milestone", sampleAppointment.getAppointmentDescription());
	}
}
