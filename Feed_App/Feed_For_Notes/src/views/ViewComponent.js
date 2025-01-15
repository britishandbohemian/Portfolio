import React, { useState, useEffect } from 'react';
import { useLocation, Link } from 'react-router-dom';

const ViewComponent = () => {
  const location = useLocation();
  const { item } = location.state; // Accessing the passed item
  const [imagePath, setImagePath] = useState('');

  // Define your images paths
  const images = [
    '/image1.jpg',
    '/image2.jpg',
    '/image3.jpg',
  ];

  useEffect(() => {
    // Select an image from the list each time the component mounts
    const randomIndex = Math.floor(Math.random() * images.length);
    // Construct the path relative to the public folder
    setImagePath(process.env.PUBLIC_URL + images[randomIndex]);
  }, [item?.id]); // Re-run the effect when the item id changes

  if (!item) {
    return <div>Item not found</div>;
  }

  return (
    <div id="content">
      <section className="navTop">
        <header>
          <nav>
          <Link style={{ display: 'flex', alignItems: 'center', fontWeight: 'bold', color: "black" }} to={"/home"}>
              <span className="material-symbols-outlined">chevron_left</span> <h5 style={{fontWeight:'bold'}}>Home</h5>
            </Link>
            <h1 className="logo">Feed</h1>
          </nav>
        </header>
      </section>

      <section style={{ display: 'grid',gridTemplateRows:'60% auto', flexDirection: 'column', alignItems: 'center' ,height:'100vh',}}>
      <img 
          src={imagePath} 
          alt="Dynamic"
          style={{ 
            height: '60vh', // Or '100vh' if you want it to cover the full viewport height
            width: '100%',
            objectFit: 'cover' // This will cover the area without stretching the image
          }} 
        />
        <div style={{ backgroundColor: 'white', width: '100%', position: 'relative',border:'#6c6c6c3b 1px solid' }}>
          <Link to={`/edit/${item.id}`} state={{ item }} style={{
            borderRadius: '100%',
            width: '50px',
            height: '50px',
            position: 'absolute',
            top: '-25px',
            right: '5%',
            backgroundColor: 'white',
            boxShadow: '0px 4px 4px rgba(0, 0, 0, 0.25)',
            textDecoration: 'none',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
          }}>
            <span className="material-symbols-outlined" style={{ color: 'black', fontSize: '24px' }}>edit</span>
          </Link>
          <div className="text" style={{ padding: '1rem' }}>
            <h1 style={{fontWeight:'bold'}}>{item.name}</h1>
            <h2 style={{ fontWeight: '100', fontSize: '16px' }}>{item.whyImportant}</h2>
          </div>
          <div style={{ backgroundColor: 'red', padding: '9px', margin: '0' }}></div>
        </div>
      </section>
    </div>
  );
};

export default ViewComponent;
