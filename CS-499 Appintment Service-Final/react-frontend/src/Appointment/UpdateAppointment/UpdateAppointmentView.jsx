import React, { useState } from 'react';
import styles from './UpdateAppointmentView.module.css';

/**
 * This modal allows users to update an appointment by modifying its date or description.
 * It validates the input and triggers the update function if the appointment exists.
 */
function UpdateAppointmentView({ onUpdateDate, onUpdateDescription, onCancel }) {
  // UseState to store the entered appointment ID
  const [appointmentID, setAppointmentID] = useState('');
  // UseState to track whether updating date or description
  const [updateType, setUpdateType] = useState('');
  // UseState to store the new value (date or description)
  const [newValue, setNewValue] = useState('');
  // UseState to handle errors
  const [error, setError] = useState(null);

  const handleUpdate = async () => {
    // Ensures all fields are filled
    if (!appointmentID || !updateType || !newValue) {
      // Sets error message
      setError('Please fill out all fields.');
      return;
    }

    try {
      // Calls the appropriate update
      if (updateType === 'date') {
        await onUpdateDate(appointmentID, newValue);
      } else if (updateType === 'description') {
        await onUpdateDescription(appointmentID, newValue);
      }

      setError(null); // Clear errors
      onCancel(); // Closes the modal after a successful update
    } catch {
      // Handle errors if the update operation fails
      setError('Failed to update the appointment. Either ID does not exist, or it belongs to another user.');
    }
  };

  // Returns the view
  return (
    <div className={styles.Modal}>
      <div className={styles.ModalContent}>
        <h2>Update Appointment</h2>

        {/* Input field for entering the appointment ID */}
        <p>Enter the Appointment ID to update:</p>
        <input
          type="text"
          value={appointmentID}
          onChange={(e) => setAppointmentID(e.target.value)}
          placeholder="Appointment ID"
          className={styles.Input}
        />

        {/* Dropdown for selecting update type (date or description) */}
        <p>What would you like to update?</p>
        <select
          value={updateType}
          onChange={(e) => setUpdateType(e.target.value)}
          className={styles.Select}
        >
          <option value="">Select an option</option>
          <option value="date">Date</option>
          <option value="description">Description</option>
        </select>

        {/* Input field for new value (date or description) */}
        <input
          type={updateType === 'date' ? 'date' : 'text'}
          value={newValue}
          onChange={(e) => setNewValue(e.target.value)}
          placeholder={updateType === 'date' ? 'YYYY-MM-DD' : 'Enter new description'}
          className={styles.Input}
        />

        {/* Display an error message if validation fails */}
        {error && <p className={styles.Error}>{error}</p>}

        <div className={styles.ModalActions}>
          <button className={styles.ConfirmButton} onClick={handleUpdate}>
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

export default UpdateAppointmentView;
