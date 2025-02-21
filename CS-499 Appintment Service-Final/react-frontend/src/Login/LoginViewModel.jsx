import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

/**
 * Manages the login process, handles form state, and sends login requests to the backend.
 */
function useLoginViewModel() {
  // Initializes state for login form inputs
  const [loginModel, setLoginModel] = useState({ username: "", password: "" });

  // State to hold any error messages
  const [error, setError] = useState(null);

  // Hook for navigation
  const navigate = useNavigate();

  /**
   * Handles input changes in the form fields
   * @param {string} field - The name of the field being updated
   * @param {string} value - The new value entered in the field
   */
  const handleInputChange = (field, value) => {
    setLoginModel((prev) => ({ ...prev, [field]: value }));
  };

  /**
   * Handles form submission for user login
   * @param {Event} event
   */
  const handleSubmit = async (event) => {
    event.preventDefault(); // Prevents page reload on form submission

    try {
      console.log("Attempting login with:", loginModel); // Debugging

      // Sends a POST request to login the user in the backend
      const response = await axios.post("http://localhost:8080/api/users/login", loginModel);

      console.log("Response:", response); // Debugging

      // If response indicates login was successful
      if (response.status === 200 && response.data.message === "Login successful") {
        localStorage.setItem("loggedInUser", loginModel.username);
        console.log("Login successful, redirecting to /appointments");
        navigate("/appointments"); // Redirects user to appointment page
      } else {
        setError("Invalid credentials. Please try again.");
      }
    } catch (error) {
      console.error("Login request error:", error); // Debugging
      setError(error.response?.data?.message || "An error occurred while logging in.");
    }
  };

  // Returns state and functions for managing login
  return { loginModel, error, handleInputChange, handleSubmit };
}

export default useLoginViewModel;
