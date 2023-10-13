import logo from './logo.svg';
import './App.css';

import React, { useState, useEffect } from 'react';

function App() {
  const [apiString, setApiString] = useState('');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('http://localhost:8080/hello')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.text(); 
      })
      .then((data) => {
        setApiString(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
        setLoading(false);
      });
  }, []);

  return (
    <div>
      <h1>API String Response</h1>
      {loading ? (
        <p>Loading...</p>
      ) : apiString ? (
        <div>
          <h2>String from API:</h2>
          <p>{apiString}</p>
        </div>
      ) : (
        <p>No data available</p>
      )}
    </div>
  );
}

export default App;
