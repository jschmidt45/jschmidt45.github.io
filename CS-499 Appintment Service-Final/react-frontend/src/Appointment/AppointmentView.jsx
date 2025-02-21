import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./AppointmentView.module.css";
import useAppointmentViewModel from "./AppointmentViewModel";
import DeleteAppointmentView from "./DeleteAppointment/DeleteAppointmentView";
import UpdateAppointmentView from "./UpdateAppointment/UpdateAppointmentView";

/**
 * AppointmentView component manages displaying user appointments,
 * creating new appointments, and handling updates and deletions.
 */
function AppointmentView() {
  const navigate = useNavigate();
  const loggedInUser = localStorage.getItem("loggedInUser");

  // State for controlling modals
  const [isDeleteModalOpen, setDeleteModalOpen] = useState(false);
  const [isUpdateModalOpen, setUpdateModalOpen] = useState(false);

  // Hooks for managing appointments and errors
  const {
    appointment,
    appointments,
    error,
    handleInputChange,
    handleSubmit,
    fetchAppointments,
    deleteAppointment,
    updateAppointmentDate,
    updateAppointmentDescription,
  } = useAppointmentViewModel();

  // Ensures user is logged in before showing appointments
  useEffect(() => {
    if (!loggedInUser) {
      navigate("/"); // Redirect to login if not logged in
    }
    fetchAppointments();
  }, [fetchAppointments, loggedInUser, navigate]);

  /**
   * Handles user logout by clearing session and navigating to login
   */
  const handleLogout = () => {
    localStorage.removeItem("loggedInUser");
    navigate("/"); // Redirect to login page
  };

  return (
    <div className={styles.AppointmentView}>
      <div className={styles.AppointmentContainer}>
        <header>
          <h1>Appointment Service</h1>
          <h2>Welcome, {loggedInUser}</h2>
        </header>

        {/* Form to create a new appointment */}
        <form onSubmit={handleSubmit}>
          <h2>Create a New Appointment</h2>
          <div className="input-group">
            <label>Appointment ID:</label>
            <input
              type="text"
              value={appointment.appointmentID}
              onChange={(e) => handleInputChange("appointmentID", e.target.value)}
              placeholder="Enter ID (max 10 chars)"
            />
          </div>
          <div className="input-group">
            <label>Appointment Date:</label>
            <input
              type="date"
              value={appointment.appointmentDate}
              onChange={(e) => handleInputChange("appointmentDate", e.target.value)}
            />
          </div>
          <div className="input-group">
            <label>Description:</label>
            <input
              type="text"
              value={appointment.appointmentDescription}
              onChange={(e) => handleInputChange("appointmentDescription", e.target.value)}
              placeholder="Enter description (max 50 chars)"
            />
          </div>
          <button className={styles.AddAppointmentButton} type="submit">
            Add Appointment
          </button>
          {error && <p className={styles.Error}>{error}</p>}
        </form>

        {/* Display user appointments */}
        <section>
          <h2>Your Appointments</h2>
          <ul>
            {appointments.map((appt) => (
              <li key={appt.appointmentID}>
                <strong>ID:</strong> {appt.appointmentID}, 
                <strong>Date:</strong> {appt.appointmentDate}, 
                <strong>Description:</strong> {appt.appointmentDescription}
              </li>
            ))}
          </ul>
        </section>

        {/* Action buttons at the bottom */}
        <div className={styles.ActionButtons}>
          <button className={styles.DeleteButton} onClick={() => setDeleteModalOpen(true)}>
            Delete Appointment
          </button>
          <button className={styles.UpdateButton} onClick={() => setUpdateModalOpen(true)}>
            Update Appointment
          </button>
        </div>

        {/* Delete Appointment Modal */}
        {isDeleteModalOpen && (
          <DeleteAppointmentView
            onDelete={(appointmentID) => {
              deleteAppointment(appointmentID);
              setDeleteModalOpen(false);
            }}
            onCancel={() => setDeleteModalOpen(false)}
          />
        )}

        {/* Update Appointment Modal */}
        {isUpdateModalOpen && (
          <UpdateAppointmentView
            onUpdateDate={(appointmentID, newDate) => updateAppointmentDate(appointmentID, newDate)}
            onUpdateDescription={(appointmentID, newDescription) =>
              updateAppointmentDescription(appointmentID, newDescription)
            }
            onCancel={() => setUpdateModalOpen(false)}
          />
        )}

        {/* Logout button */}
        <div className={styles.LogOutButtonContainer}>
          <button className={styles.LogOutButton} onClick={handleLogout}>
            Log Out
          </button>
        </div>
      </div>
    </div>
  );
}

export default AppointmentView;
