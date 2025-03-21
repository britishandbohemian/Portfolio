const fs = require('fs-extra');
const path = require('path');

// Define paths
const sourceDir = path.join(__dirname);
const distDir = path.join(__dirname, 'dist');

// Clean dist directory
fs.emptyDirSync(distDir);
console.log('✓ Cleaned dist directory');

// Copy all static assets
fs.copySync(
  path.join(sourceDir, 'assets'),
  path.join(distDir, 'assets'),
  { overwrite: true }
);
console.log('✓ Copied assets directory');

// Copy pages
fs.copySync(
  path.join(sourceDir, 'pages'),
  path.join(distDir, 'pages'),
  { overwrite: true }
);
console.log('✓ Copied pages directory');

// Copy root files (excluding node_modules, package files, etc.)
const filesToCopy = [
  'index.html',
  'shared.css',
  'shared.js',
  'ico.png',
  '.htaccess'
];

filesToCopy.forEach(file => {
  const sourcePath = path.join(sourceDir, file);
  if (fs.existsSync(sourcePath)) {
    fs.copySync(sourcePath, path.join(distDir, file));
    console.log(`✓ Copied ${file}`);
  }
});

// Create a .nojekyll file to prevent GitHub Pages from using Jekyll
fs.writeFileSync(path.join(distDir, '.nojekyll'), '');
console.log('✓ Created .nojekyll file');

console.log('\n✅ Build completed successfully! Files are ready in the dist directory.');