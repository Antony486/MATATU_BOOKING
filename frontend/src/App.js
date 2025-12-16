 
import React, { useState } from 'react';
import RouteSearch from './RouteSearch';
import BookingForm from './BookingForm';
import './App.css';

function App() {
  const [activeTab, setActiveTab] = useState('search');

  return (
    <div className="App">
      <header style={{
        backgroundColor: '#4CAF50',
        color: 'white',
        padding: '20px',
        textAlign: 'center'
      }}>
        <h1>🚐 Matatu Booking System</h1>
      </header>

      <div style={{ 
        display: 'flex', 
        justifyContent: 'center', 
        marginTop: '20px',
        gap: '10px'
      }}>
        <button
          onClick={() => setActiveTab('search')}
          style={{
            padding: '10px 20px',
            backgroundColor: activeTab === 'search' ? '#4CAF50' : '#ddd',
            color: activeTab === 'search' ? 'white' : '#333',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer',
            fontSize: '16px'
          }}
        >
          Search Routes
        </button>
        
        <button
          onClick={() => setActiveTab('booking')}
          style={{
            padding: '10px 20px',
            backgroundColor: activeTab === 'booking' ? '#4CAF50' : '#ddd',
            color: activeTab === 'booking' ? 'white' : '#333',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer',
            fontSize: '16px'
          }}
        >
          Book a Seat
        </button>
      </div>

      <main style={{ marginTop: '20px' }}>
        {activeTab === 'search' && <RouteSearch />}
        {activeTab === 'booking' && <BookingForm />}
      </main>
    </div>
  );
}

export default App;