import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

/**
 * Manages the signup process, handles form state, input validation,
 * and sends user registration requests to the backend.
 */
function useSignUpViewModel() {
  // Initializes the state for the signup form inputs
  const [signUpModel, setSignUpModel] = useState({
    username: "",
    password: "",
    confirmPassword: "",
  });

  // State to hold any error messages
  const [error, setError] = useState(null);

  // Hook for navigation
  const navigate = useNavigate();

  /**
   * Validates username and password inputs before sending them to the backend
   * @param {string} username
   * @param {string} password
   * @param {string} confirmPassword
   * @returns {string|null} - Returns an error message if validation fails, otherwise null
   */
  const validateInputs = (username, password, confirmPassword) => {
    if (!/^[a-zA-Z0-9_]{4,15}$/.test(username)) {
      return "Username must be 4-15 characters and contain only letters, numbers, or underscores.";
    }
    if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/.test(password)) {
      return "Password must be at least 6 characters and include at least 1 letter and 1 number.";
    }
    if (password !== confirmPassword) {
      return "Passwords do not match.";
    }
    return null;
  };

  /**
   * Handles input changes in the form fields
   * @param {string} field - The name of the field being updated
   * @param {string} value - The new value entered in the field
   */
  const handleInputChange = (field, value) => {
    setSignUpModel((prevModel) => ({ ...prevModel, [field]: value }));
  };

  /**
   * Handles the form submission for user signup
   * @param {Event} event
   */
  const handleSubmit = async (event) => {
    event.preventDefault(); // Prevents page reload on form submission

    // Validate inputs before making API call
    const validationError = validateInputs(
      signUpModel.username,
      signUpModel.password,
      signUpModel.confirmPassword
    );

    if (validationError) {
      setError(validationError); // Displays validation error
      return;
    }

    try {
      // Sends a POST request to register the user in the backend
      await axios.post("http://localhost:8080/api/users/signup", {
        username: signUpModel.username,
        password: signUpModel.password,
      });

      setError(null); // Clears any previous errors
      console.log("Signup successful. Redirecting to login...");

      // Redirects user to the login page after successful signup
      navigate("/");
    } catch (error) {
      setError(error.response?.data?.message || "Error signing up. Please try again.");
    }
  };

  // Returns state and functions for managing signup
  return { signUpModel, error, handleInputChange, handleSubmit };
}

export default useSignUpViewModel;
