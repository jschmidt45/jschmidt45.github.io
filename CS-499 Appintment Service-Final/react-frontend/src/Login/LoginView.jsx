import styles from './LoginView.module.css';
import { Link } from "react-router-dom";
import useLoginViewModel from './LoginViewModel';

function Login() {
  // Uses login view model to assign variables
  const { loginModel, error, handleInputChange, handleSubmit } = useLoginViewModel();

  return (
    // HTML for layout
    <div className={styles.Login}>
      <header className={styles.LoginHeader}>
        <h1 className={styles.LoginTitle}>Appointment Service</h1>
        <p>Created by John Schmidt</p>

        <h2>Login:</h2>
        {/* Form submission is handled by handleSubmit - defined in ViewModel */}
        <form onSubmit={handleSubmit}> 
          <div className="input-group">
            <h4>Username:</h4>
            <input
              type="text"
              id="username"
              placeholder="Enter your username"
              value={loginModel.username}
              onChange={(e) => handleInputChange("username", e.target.value)} 
            /> {/* Updates username in ViewModel */}
          </div>
          <div className="input-group">
            <h4>Password:</h4>
            <input
              type="password"
              id="password"
              placeholder="Enter your password"
              value={loginModel.password}
              onChange={(e) => handleInputChange("password", e.target.value)}
            /> {/* Updates password in ViewModel */}
          </div>
          <button className={styles.LoginButton} type="submit">Login</button> {/* Submits form */}
        </form>

        {error && <p className="error">{error}</p>} {/* Display error messages */}

        <h5 className="signup-link">
          {/* Uses Link from react-router-dom to navigate to the signup page */}
          <Link to="/signup">New User? Sign up here!</Link>
        </h5>
      </header>
    </div>
  );
}

export default Login;
