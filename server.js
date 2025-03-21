const express = require('express');
const path = require('path');
const app = express();
const PORT = process.env.PORT || 3000;

// Serve static files
app.use(express.static(path.join(__dirname, '/')));

// Serve pages directory
app.use('/pages', express.static(path.join(__dirname, 'pages')));

// Serve assets directory
app.use('/assets', express.static(path.join(__dirname, 'assets')));

// Redirect root to pages/index.html
app.get('/', (req, res) => {
  res.redirect('/pages/index.html');
});

// Handle other routes
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'pages/index.html'));
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}/`);
  console.log('Press Ctrl+C to quit');
});