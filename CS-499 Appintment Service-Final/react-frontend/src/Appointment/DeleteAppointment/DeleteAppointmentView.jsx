import React, { useState } from 'react';
import styles from './DeleteAppointmentView.module.css';

/**
 * This modal allows users to delete an appointment by entering its ID.
 * It validates the input and triggers the delete function if the appointment exists.
 */
function DeleteAppointmentView({ onDelete, onCancel }) {
  // UseState to store the entered appointment ID
  const [appointmentID, setAppointmentID] = useState('');
  // UseState to handle errors
  const [error, setError] = useState(null);

  const handleDelete = async () => {
    // Ensures ID field is not empty
    if (!appointmentID || appointmentID.trim().length === 0) {
      // Sets error message
      setError('Please enter a valid Appointment ID.');
      return;
    }

    // Ensures ID field is under 10 characters
    if (appointmentID.length > 10) {
      // Sets error message
      setError('Appointment ID must be 10 characters or fewer.');
      return;
    }

    try {
      // Calls the onDelete function
      await onDelete(appointmentID);
      setError(null); // Clear any errors
      setAppointmentID(''); // Resets input
      onCancel(); // Closes the modal
    } catch {
      // Handle errors if the delete operation fails
      setError('Failed to delete appointment. Appointment ID does not exist or belongs to differet user.');
    }
  };
// Returns the view
  return (
    <div className={styles.Modal}>
      <div className={styles.ModalContent}>
        <h2>Delete Appointment</h2>
        <p>Enter the Appointment ID to delete:</p>
        
        <input
          type="text"
          value={appointmentID}
          onChange={(e) => setAppointmentID(e.target.value)}
          placeholder="Appointment ID"
          className={styles.Input}
        />

        {/* Display an error message if any validation fails */}
        {error && <p className={styles.Error}>{error}</p>}

        <div className={styles.ModalActions}>
          <button className={styles.ConfirmButton} onClick={handleDelete}>
            Confirm
          </button>
          
          <button className={styles.CancelButton} onClick={onCancel}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

export default DeleteAppointmentView;
