import React, { createContext, useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { database } from './firebaseConfig';
import { ref, get, child, remove, push } from 'firebase/database'; // Ensure push is imported
import Home from './views/Home';
import SignIn from './views/SignIn';
import ViewComponent from './views/ViewComponent';
import EditComponent from './views/EditComponent';
import AddComponent from './views/AddComponent';

export const ViewsDataContext = createContext(null);

const App = () => {
  const [viewsData, setViewsData] = useState([]);

  // Define fetchData within App so it can be reused
  const fetchData = async () => {
    const dbRef = ref(database, 'components'); // Adjust path as necessary
    get(dbRef).then((snapshot) => {
      if (snapshot.exists()) {
        const data = snapshot.val();
        const dataArray = Object.keys(data).map(key => ({
          id: key,
          ...data[key]
        }));
        setViewsData(dataArray);
      } else {
        console.log("No data available");
      }
    }).catch((error) => {
      console.error(error);
    });
  };

  useEffect(() => {
    fetchData();
  }, []);

  const addNewComponent = async (newComponent) => {
    try {
      await push(ref(database, 'components'), newComponent);
      console.log("New component added to Realtime Database");
      // Fetch updated data to refresh the UI
      fetchData();
    } catch (error) {
      console.error("Error adding new component: ", error);
    }
  };

  const deleteCard = async (id) => {
    await remove(ref(database, `components/${id}`))
      .then(() => {
        console.log("Component deleted successfully.");
        // Refresh the data to reflect the deletion in the UI
        fetchData();
      })
      .catch((error) => {
        console.error("Error deleting component: ", error);
      });
  };

  return (
    <Router basename="/Feed_App">
<ViewsDataContext.Provider value={{ viewsData, addNewComponent, deleteCard, fetchData }}>
        <Routes>
          <Route path="home" element={<Home />} />
          <Route index element={<SignIn />} />
          <Route path="view/:id" element={<ViewComponent />} />
          <Route path="edit/:id" element={<EditComponent />} />
          <Route path="create" element={<AddComponent />} />
          <Route path="signin" element={<Navigate replace to="/" />} />
        </Routes>
      </ViewsDataContext.Provider>
    </Router>
  );
};

export default App;
