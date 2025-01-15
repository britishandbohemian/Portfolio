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
      boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
    }}>
      <p>{message}</p>
      <button onClick={onClose}>Close</button>
    </div>
  );
}
