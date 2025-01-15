// firebaseConfig.js
import { initializeApp } from 'firebase/app';
import { getDatabase } from 'firebase/database';

const firebaseConfig = {
  apiKey: "AIzaSyBfCpzyMnSC351_O2wIF_KZT-SLkrVyga4",
  authDomain: "britishandbohemianapi.firebaseapp.com",
  databaseURL: "https://britishandbohemianapi-default-rtdb.firebaseio.com/",
  projectId: "britishandbohemianapi",
  storageBucket: "britishandbohemianapi.appspot.com",
  messagingSenderId: "389844027661",
  appId: "1:389844027661:web:15f85960b6650e08dd0844",
  measurementId: "G-GN9R31TKH8"
};

const app = initializeApp(firebaseConfig);
const database = getDatabase(app); // Reference to the Realtime Database

export { database };