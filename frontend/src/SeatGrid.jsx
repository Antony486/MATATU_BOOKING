 
import React, { useState, useEffect } from 'react';

function SeatGrid({ matatuId, onSeatSelect }) {
  const [seats, setSeats] = useState([]);
  const [bookedSeats, setBookedSeats] = useState([]);
  const [selectedSeat, setSelectedSeat] = useState(null);

  useEffect(() => {
    // Generate 14 seats (typical matatu capacity)
    const seatNumbers = Array.from({ length: 14 }, (_, i) => i + 1);
    setSeats(seatNumbers);

    // Fetch already booked seats for this matatu
    fetchBookedSeats();
  }, [matatuId]);

  const fetchBookedSeats = async () => {
    try {
      const res = await fetch(`http://localhost:8080/api/booking/matatu/${matatuId}`);
      const bookings = await res.json();
      const booked = bookings.map(b => b.seatNumber);
      setBookedSeats(booked);
    } catch (error) {
      console.error('Error fetching booked seats:', error);
    }
  };

  const handleSeatClick = (seatNumber) => {
    if (bookedSeats.includes(seatNumber)) {
      alert('This seat is already booked!');
      return;
    }
    setSelectedSeat(seatNumber);
    if (onSeatSelect) {
      onSeatSelect(seatNumber);
    }
  };

  const getSeatStyle = (seatNumber) => {
    const baseStyle = {
      width: '60px',
      height: '60px',
      margin: '5px',
      border: '2px solid #333',
      borderRadius: '5px',
      cursor: 'pointer',
      fontSize: '16px',
      fontWeight: 'bold'
    };

    if (bookedSeats.includes(seatNumber)) {
      return { ...baseStyle, backgroundColor: '#ff4444', color: 'white', cursor: 'not-allowed' };
    }
    if (selectedSeat === seatNumber) {
      return { ...baseStyle, backgroundColor: '#4CAF50', color: 'white' };
    }
    return { ...baseStyle, backgroundColor: '#fff', color: '#333' };
  };

  return (
    <div style={{ padding: '20px' }}>
      <h3>Select Your Seat</h3>
      <div style={{ 
        display: 'grid', 
        gridTemplateColumns: 'repeat(4, 1fr)',
        maxWidth: '300px',
        gap: '5px'
      }}>
        {seats.map(seat => (
          <button
            key={seat}
            onClick={() => handleSeatClick(seat)}
            style={getSeatStyle(seat)}
            disabled={bookedSeats.includes(seat)}
          >
            {seat}
          </button>
        ))}
      </div>
      
      <div style={{ marginTop: '20px' }}>
        <div><span style={{ backgroundColor: '#fff', padding: '5px', border: '1px solid #333' }}>Available</span></div>
        <div><span style={{ backgroundColor: '#4CAF50', padding: '5px', color: 'white' }}>Selected</span></div>
        <div><span style={{ backgroundColor: '#ff4444', padding: '5px', color: 'white' }}>Booked</span></div>
      </div>
      
      {selectedSeat && (
        <p style={{ marginTop: '10px', fontSize: '18px', fontWeight: 'bold' }}>
          Selected Seat: {selectedSeat}
        </p>
      )}
    </div>
  );
}

export default SeatGrid;