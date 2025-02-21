import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Login/LoginView";
import SignUpView from "./SignUp/SignUpView";
import AppointmentView from "./Appointment/AppointmentView";

// App is used to set the routes of the application navigation.
// Begins with the login element at '/'
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<SignUpView />} />
        <Route path="/appointments" element={<AppointmentView />} /> {/* Appointment Service View */}
      </Routes>
    </Router>
  );
}

export default App;
