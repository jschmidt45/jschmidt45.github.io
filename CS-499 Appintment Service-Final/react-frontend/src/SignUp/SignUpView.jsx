import useSignUpViewModel from './SignUpViewModel';
import styles from './SignUp.module.css';

function SignUpView() {
  const { signUpModel, error, handleInputChange, handleSubmit } = useSignUpViewModel();
  // Very similar to login page with extra input box.
  return (
    <div className={styles.SignUp}>
      <header className="SignUp-header">
        <h1>Appointment Service</h1>
        <p>Created by John Schmidt</p>

        <h2>Create New Account:</h2>

        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <h4>Username:</h4>
            <input
              type="text"
              id="username"
              placeholder="Enter your username"
              value={signUpModel.username}
              onChange={(e) => handleInputChange("username", e.target.value)}
            />
          </div>
          <div className="input-group">
            <h4>Password:</h4>
            <input
              type="password"
              id="password"
              placeholder="Enter your password"
              value={signUpModel.password}
              onChange={(e) => handleInputChange("password", e.target.value)}
            />
          </div>
          <div className="input-group">
            <h4>Confirm Password:</h4>
            <input
              type="password"
              id="confirmPassword"
              placeholder="Confirm your password"
              value={signUpModel.confirmPassword}
              onChange={(e) => handleInputChange("confirmPassword", e.target.value)}
            />
          </div>
          <button className={styles.SignUpButton} type="submit">Create New Account</button>
        </form>

        {error && <p className="error">{error}</p>} {/* Display error messages */}
      </header>
    </div>
  );
}

export default SignUpView;
