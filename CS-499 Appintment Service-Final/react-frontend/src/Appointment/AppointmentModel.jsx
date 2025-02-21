// AppointmentModel.js
class AppointmentModel {
  constructor(appointmentID = "", appointmentDate = "", appointmentDescription = "") {
    this.appointmentID = appointmentID;
    this.appointmentDate = appointmentDate;
    this.appointmentDescription = appointmentDescription;
  }

  // Validation function to help ensure that input is in the proper format.
  validate() {
    const errors = [];

    if (!this.appointmentID || this.appointmentID.length > 10) {
      errors.push("Appointment ID must be 1-10 characters long.");
    }
    if (!this.appointmentDate || new Date(this.appointmentDate) < new Date()) {
      errors.push("Appointment date must be in the future.");
    }
    if (!this.appointmentDescription || this.appointmentDescription.length > 50) {
      errors.push("Appointment description must be 1-50 characters long.");
    }

    return errors.length > 0 ? errors : null;
  }
}

export default AppointmentModel;
