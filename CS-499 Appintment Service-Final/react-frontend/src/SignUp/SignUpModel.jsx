
// Simple validation for sign up
const SignUpModel = {
    validate: (username, password, confirmPassword) => {
      if (!username || !password || !confirmPassword) {
        return { valid: false, message: "All fields are required." };
      }
      if (password !== confirmPassword) {
        return { valid: false, message: "Passwords do not match." };
      }
      if (password.length < 6) {
        return { valid: false, message: "Password must be at least 6 characters long." };
      }
      return { valid: true, message: "Signup successful." };
    },
  };
  
  export default SignUpModel;
  