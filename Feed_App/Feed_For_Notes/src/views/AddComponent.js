import React, { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ViewsDataContext } from '../App';
import { getDatabase, ref, push } from 'firebase/database'; // Import Realtime Database functionalities

const AddComponent = () => {
  const { updateViewsData } = useContext(ViewsDataContext); // Assuming you adapt updateViewsData for Realtime Database
  const navigate = useNavigate();
  
  // State for the new component's details
  const [newName, setNewName] = useState('');
  const [newImportance, setNewImportance] = useState('');
  const [newPriority, setNewPriority] = useState('');

  const handleSubmit = async (e) => { 
    e.preventDefault();
    const newComponent = {
      name: newName,
      whyImportant: newImportance,
      priority: newPriority,
    };

    try {
      // Reference to your Realtime Database, 'components' node
      const db = getDatabase();
      const componentsRef = ref(db, 'components');
      
      // Add a new component to the 'components' list
      await push(componentsRef, newComponent);

      console.log("New component added to Realtime Database");
      // Optionally update local state to reflect the addition
      navigate('/home'); // navigate back to the home page upon successful addition
    } catch (error) {
      console.error("Error adding new component: ", error);
    }
  };

  return (
    <div id="content">
      {/* Navigation and Header */}
      <section className="navTop">
        <div style={{ marginBottom: '5rem' }}>
          <header>
            <nav>
              <button onClick={() => navigate('/home')} style={{ display: 'flex', alignItems: 'center', fontWeight: 'bold',color:'black' }}>
                <span className="material-symbols-outlined">chevron_left</span> <h5 style={{fontWeight:'bold'}}>Home </h5>
              </button>
              <h1 className="logo">Feed</h1>
            </nav>
          </header>
        </div>
      </section>

      {/* Form for adding a new note */}
      <section style={{ display: 'flex', flexDirection: 'column', justifyContent: 'space-evenly', alignItems: 'center' }}>
        <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', justifyContent: 'space-evenly', alignItems: 'center'}}>
          <div style={{ margin: '10px', width: '100%' }}>
            <label htmlFor="name">Name:</label>
            <input type="text" id="name" name="name" value={newName} onChange={(e) => setNewName(e.target.value)} required style={{ backgroundColor: 'black', color: 'white', border: 'none', height: '28px', width: '100%' }} />
          </div>
          <div style={{ margin: '10px', width: '100%' }}>
            <label htmlFor="whyImportant">Why is it important:</label>
            <textarea id="whyImportant" name="whyImportant" value={newImportance} onChange={(e) => setNewImportance(e.target.value)} required style={{ backgroundColor: 'black', color: 'white', border: 'none', height: '20vh', width: '100%', resize: 'none' }} />
          </div>             

          <div style={{ margin: '10px', width: '100%' }}>
  <label htmlFor="priority">Priority:</label>
  <select
    id="priority"
    name="priority"
    value={newPriority}
    onChange={(e) => setNewPriority(e.target.value)}
    required
    style={{ backgroundColor: 'black', color: 'white', border: 'none', height: '28px', width: '100%',fontSize:'15px' }}
  >
    <option value="">Select Priority</option>
    <option value="High">High</option>
    <option value="Medium">Medium</option>
    <option value="Low">Low</option>
  </select>
</div>


          <button type="submit" style={{ height: '50px', width: '50px', backgroundColor: 'black', borderRadius: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center', marginTop: '20px' }}>
            <span className="material-symbols-outlined" style={{color:'white'}}>check</span>
          </button>
        </form>
      </section>
    </div>
  );
};

export default AddComponent;
