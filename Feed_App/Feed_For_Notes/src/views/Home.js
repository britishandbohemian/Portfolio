import React, { useContext } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { ViewsDataContext } from '../App';

const Home = () => {
  const { viewsData, deleteCard } = useContext(ViewsDataContext);
  const navigate = useNavigate();
  const location = useLocation();
  const { username } = location.state || {};

  const handleLogout = () => {
    navigate('/signin');
  };

  // Adjusted to use the deleteCard function from context
  const handleDelete = async (id) => {
    try {
      await deleteCard(id);
      console.log(`Component with ID ${id} deleted successfully.`);
    } catch (error) {
      console.error("Error deleting component: ", error);
    }
  };



  return (
    <div id="content">
      <section className="navTop" style={{ marginBottom: '5rem' }}>
        <header>
          <nav>
<button>
<span onClick={handleLogout} style={{ cursor: 'pointer',display:'flex',alignItems:'center' }}>
  <span className="material-symbols-outlined">chevron_left</span> <h5 style={{ fontWeight: 'bold' }}>Logout</h5>
</span>
</button>
            <h1 className="logo">Feed</h1>
          </nav>
        </header>
      </section>

      <section style={{ padding: '1rem', margin: ' auto 0rem' }}>
        <div style={{ margin: '3rem auto', padding: '0rem', backgroundColor: 'rgb(0, 0, 0)', marginBottom: '0' }}>

          <div style={{ padding: '2rem' }}>
            <h1 style={{ fontSize: '5vh', fontWeight: 'bold', color: 'white' }}>Welcome</h1>

            <h5 style={{ fontSize: '15px', color: 'white' }}>{username}</h5>
          </div>

          <hr style={{ padding: '0', margin: '0', height: '2.5rem', width: '100%' }} className="gradient" />
        </div>

        <div className="container">
          {viewsData.map((view, index) => (

            <div>
<div key={view.id} className="card-container" style={{ 
  display: 'flex', 
  flexDirection: 'column', 
  justifyContent: 'space-between', 
  height: '100%', 
  marginBottom: '20px', // Adjust as needed for spacing between cards
  backgroundColor: '#fff', // Background color for visibility
  boxShadow: '0px 0px 10px rgba(0,0,0,0.1)', // Optional shadow for aesthetics
  padding: '20px', // Inner padding
  borderRadius: '8px' // Rounded corners
}}>
  <Link to={`/view/${view.id}`} state={{ item: view }} className="card" style={{ 
    textDecoration: 'none', 
    marginBottom: '10px' // Space between the content and the hr
  }}>
    <h1 style={{ fontWeight: 'bold' }}>{view.name}</h1>
    <h4 style={{ fontWeight: '100' }}>{view.whyImportant}</h4>
    <h5>Priority</h5>
  </Link>
  <span className="material-symbols-outlined" style={{ 
    cursor: 'pointer', 
    color: 'black', 
    alignSelf: 'flex-end', // Align close icon to the right
    marginBottom: '10px' // Space between the icon and the hr
  }} onClick={() => handleDelete(view.id)}>
    thumb_up
  </span>
  <hr style={{ 
    margin: '0', // Remove any default margin
    height: '2px', // Height of the hr line
    backgroundColor: 'red', // Color of the hr line
    border: 'none', // Remove any default border
    alignSelf: 'stretch' // Ensure it takes the full width
  }} />
</div>

            </div>


            
          ))}
        </div>







      </section>
      {/* Floating Action Button for Adding a New Note */}
      <Link to="/create" style={{
        position: 'fixed',
        right: '20px',
        bottom: '20px',
        backgroundColor: 'black', // Example blue color
        color: 'white',
        padding: '15px',
        borderRadius: '50%',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        textDecoration: 'none',
        fontSize: '24px',
        boxShadow: '0 4px 8px rgba(0,0,0,0.2)',
        zIndex: '1000', // Ensure it floats above other content
      }}>
        <span className="material-symbols-outlined">add</span>
      </Link>
    </div>
  );
};

export default Home;
