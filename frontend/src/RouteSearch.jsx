 
import React, { useState } from 'react';

function RouteSearch() {
  const [routes, setRoutes] = useState([]);
  const [startLocation, setStartLocation] = useState('Nairobi');
  const [endLocation, setEndLocation] = useState('Mombasa');
  const [loading, setLoading] = useState(false);

  const searchRoutes = async () => {
    setLoading(true);
    try {
      const res = await fetch(
        `http://localhost:8080/api/routes/search?start=${startLocation}&end=${endLocation}`
      );
      const data = await res.json();
      setRoutes(data);
    } catch (error) {
      console.error('Error fetching routes:', error);
      alert('Failed to fetch routes');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '800px', margin: '0 auto' }}>
      <h2>Search Routes</h2>
      
      <div style={{ marginBottom: '20px' }}>
        <input
          type="text"
          placeholder="Start Location"
          value={startLocation}
          onChange={(e) => setStartLocation(e.target.value)}
          style={{ padding: '10px', marginRight: '10px', width: '200px' }}
        />
        
        <input
          type="text"
          placeholder="End Location"
          value={endLocation}
          onChange={(e) => setEndLocation(e.target.value)}
          style={{ padding: '10px', marginRight: '10px', width: '200px' }}
        />
        
        <button 
          onClick={searchRoutes}
          disabled={loading}
          style={{ 
            padding: '10px 20px', 
            backgroundColor: '#4CAF50', 
            color: 'white',
            border: 'none',
            cursor: 'pointer'
          }}
        >
          {loading ? 'Searching...' : 'Search Routes'}
        </button>
      </div>

      <div>
        {routes.length === 0 ? (
          <p>No routes found. Try searching!</p>
        ) : (
          routes.map(route => (
            <div 
              key={route.id}
              style={{
                border: '1px solid #ddd',
                padding: '15px',
                marginBottom: '10px',
                borderRadius: '5px',
                backgroundColor: '#f9f9f9'
              }}
            >
              <strong>{route.startLocation}</strong> → <strong>{route.endLocation}</strong>
              <br />
              <span style={{ color: '#4CAF50', fontSize: '18px', fontWeight: 'bold' }}>
                Ksh {route.fare}
              </span>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default RouteSearch;