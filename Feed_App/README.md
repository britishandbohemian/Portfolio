
# Feed App

This project is a React-based application designed to demonstrate the integration of a static React app with Firebase for backend services, and deployment on GitHub Pages.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them:

- Node.js
- npm (Node Package Manager)
- Git

### Installing

A step by step series of examples that tell you how to get a development env running:

1. **Clone the repository**

   ```bash
   git clone https://github.com/britishandbohemian/Feed_App.git
   ```

2. **Navigate to the project directory**

   ```bash
   cd Feed_App
   ```

3. **Install dependencies**

   ```bash
   npm install
   ```

4. **Start the development server**

   ```bash
   npm start
   ```

   This runs the app in the development mode. Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

### Firebase Setup

1. **Create a Firebase project** in the Firebase console.

2. **Register your app** with Firebase in the project settings.

3. **Add Firebase SDK** and initialize Firebase in your app. You can find the config object in your Firebase project settings.

   ```javascript
   import firebase from 'firebase/app';
   import 'firebase/firestore'; // If using Firestore
   import 'firebase/auth'; // If using Firebase Authentication

   const firebaseConfig = {
     apiKey: "YOUR_API_KEY",
     authDomain: "YOUR_AUTH_DOMAIN",
     projectId: "YOUR_PROJECT_ID",
     storageBucket: "YOUR_STORAGE_BUCKET",
     messagingSenderId: "YOUR_MESSAGING_SENDER_ID",
     appId: "YOUR_APP_ID"
   };

   // Initialize Firebase
   firebase.initializeApp(firebaseConfig);
   ```

### Deploying to GitHub Pages

1. **Install the `gh-pages` package**

   ```bash
   npm install --save gh-pages
   ```

2. **Add deployment scripts** in your `package.json`:

   ```json
   "scripts": {
     "start": "react-scripts start",
     "build": "react-scripts build",
     "deploy": "gh-pages -d build",
     "predeploy": "npm run build"
   },
   "homepage": "https://britishandbohemian.github.io/Feed_App/"
   ```

3. **Deploy your application**

   ```bash
   npm run deploy
   ```

   This command builds the app for production to the `build` folder and deploys it to GitHub Pages.

## Built With

* [React](https://reactjs.org/) - The web framework used
* [Firebase](https://firebase.google.com/) - Backend and API
* [GitHub Pages](https://pages.github.com/) - Deployment

## Contributing

Please read [CONTRIBUTING.md](https://github.com/britishandbohemian/Feed_App/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
