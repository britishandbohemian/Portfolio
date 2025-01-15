import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate instead of useHistory

// Define Popup component here
function Popup({ message, onClose }) {
  return (
    <div style={{
      position: 'fixed',
      top: '50%',
      left: '50%',
      transform: 'translate(-50%, -50%)',
      backgroundColor: 'white',
      padding: '20px',
      zIndex: 100,
      border: '1px solid black',
      boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)', // Slightly stronger shadow
    }}>
      <p style={{ fontSize: '14px', marginBottom: '1rem' }}>{message}</p> {/* Decreased font size */}
      {/* Position "X" button at the center bottom */}
      <button onClick={onClose} style={{
        position: 'absolute',
        bottom: '10px', // Adjust distance from bottom
        left: '50%',
        transform: 'translateX(-50%)',
        fontSize: '14px', // Match the font size decrease
        cursor: 'pointer',
      }}>
        X {/* Changed button label to "X" */}
      </button>
    </div>

  );
}

function SignIn() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [showPopup, setShowPopup] = useState(false); // For popup visibility
  const [errorMessage, setErrorMessage] = useState(''); // Error message to display
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();

    if (!username.trim()) {
      // If the username is empty, show the popup with an error message
      setErrorMessage("Username is required!");
      setShowPopup(true);
      return;
    }

    // If validation passes, navigate to /home
    navigate('/home', { state: { username: username } });
  };
  return (
    <section className="signin bg-home ">
      <div id="imgtop" className="">
        <h1 className="logo" style={{ color: "white", marginBottom: '10px', background:'black', padding:'2rem' }}>Feed</h1>
      </div>

      <div className='inputsDiv'>
        <form className="signinfrm" onSubmit={handleSubmit}>
          <div id="inputs">
            <div className='inputlabel'>
              <label style={{color:'white',background:'black',padding:'10px'}} htmlFor="username">Username</label>
              <input
                id="username"
                name="username"
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            <div className='inputlabel'>
              <label  style={{color:'white',background:'black',padding:'10px'}} htmlFor="password">Password</label>
              <input
                id="password"
                name="password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <div className='btnenter'>
              <button type="submit" className="btns">
                <span className="material-icons">arrow_forward</span>
              </button>
            </div>
          </div>
        </form>
      </div>

      {showPopup && <Popup message={errorMessage} onClose={() => setShowPopup(false)} />}
    </section>
  );
}

export default SignIn;
