import { useState, useCallback, useEffect } from "react";
import axios from "axios";
import AppointmentModel from "./AppointmentModel";

function useAppointmentViewModel() {
  const [appointment, setAppointment] = useState(new AppointmentModel());
  const [appointments, setAppointments] = useState([]);
  const [error, setError] = useState(null);
  const loggedInUser = localStorage.getItem("loggedInUser");

  const handleInputChange = (field, value) => {
    setAppointment((prev) => new AppointmentModel(
      field === "appointmentID" ? value : prev.appointmentID,
      field === "appointmentDate" ? value : prev.appointmentDate,
      field === "appointmentDescription" ? value : prev.appointmentDescription
    ));
  };
  

  const fetchAppointments = useCallback(async () => {
    if (!loggedInUser) {
      setError("User not authenticated.");
      return;
    }

    try {
      const response = await axios.get(`http://localhost:8080/api/appointments/user/${loggedInUser}`);
      setAppointments(response.data);
    } catch (err) {
      setError("Error fetching appointments.");
    }
  }, [loggedInUser]);

  useEffect(() => {
    fetchAppointments();
  }, [fetchAppointments]);

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    // Create a new instance of AppointmentModel before validating
    const newAppointment = new AppointmentModel(
      appointment.appointmentID,
      appointment.appointmentDate,
      appointment.appointmentDescription
    );
  
    const validationErrors = newAppointment.validate(); // Call validate() on the correct instance
    if (validationErrors) {
      setError(validationErrors.join(", ")); // Display validation errors
      return;
    }
  
    if (!loggedInUser) {
      setError("User is not logged in.");
      return;
    }
  
    try {
      await axios.post(`http://localhost:8080/api/appointments/${loggedInUser}`, {
        appointmentID: newAppointment.appointmentID,
        appointmentDate: newAppointment.appointmentDate,
        appointmentDescription: newAppointment.appointmentDescription,
        username: loggedInUser, // Ensure appointment is linked to the logged-in user
      });
  
      setAppointments((prev) => [...prev, newAppointment]); // Add validated appointment
      setAppointment(new AppointmentModel()); // Reset form
      setError(null);
      fetchAppointments(); // Refresh appointments
    } catch (err) {
      setError(err.response?.data?.message || "Error adding appointment.");
    }
  };

  const deleteAppointment = async (appointmentID) => {
    try {
      await axios.delete(`http://localhost:8080/api/appointments/${loggedInUser}/${appointmentID}`);
      setAppointments((prev) => prev.filter((appt) => appt.appointmentID !== appointmentID));
      setError(null);
    } catch (err) {
      setError("Error deleting appointment.");
    }
  };

  // Update appointment date
  const updateAppointmentDate = async (appointmentID, newDate) => {
    try {
      await axios.put(
        `http://localhost:8080/api/appointments/${loggedInUser}/${appointmentID}/date`,
        { date: newDate }
      );
      setAppointments((prev) =>
        prev.map((appt) =>
          appt.appointmentID === appointmentID ? { ...appt, appointmentDate: newDate } : appt
        )
      );
      setError(null);
    } catch (err) {
      setError(err.response?.data?.message || "Error updating appointment date.");
    }
  };

  // Update appointment description
  const updateAppointmentDescription = async (appointmentID, newDescription) => {
    try {
      await axios.put(
        `http://localhost:8080/api/appointments/${loggedInUser}/${appointmentID}/description`,
        { description: newDescription }
      );
      setAppointments((prev) =>
        prev.map((appt) =>
          appt.appointmentID === appointmentID ? { ...appt, appointmentDescription: newDescription } : appt
        )
      );
      setError(null);
    } catch (err) {
      setError(err.response?.data?.message || "Error updating appointment description.");
    }
  };

  return {
    appointment,
    appointments,
    error,
    handleInputChange,
    handleSubmit,
    fetchAppointments,
    deleteAppointment,
    updateAppointmentDate,
    updateAppointmentDescription,
  };
}

export default useAppointmentViewModel;
