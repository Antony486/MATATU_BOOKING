import React, { useState } from 'react';
import SeatGrid from './SeatGrid';

function BookingForm() {
  const [user, setUser] = useState('');
  const [matatuId, setMatatuId] = useState(1);
  const [routeId, setRouteId] = useState(1);
  const [selectedSeat, setSelectedSeat] = useState(null);
  const [bookingConfirmed, setBookingConfirmed] = useState(false);
  const [confirmedBooking, setConfirmedBooking] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleBooking = async () => {
    if (!user || !selectedSeat) {
      alert('Please enter your name and select a seat');
      return;
    }

    const bookingData = {
      username: user,
      seatNumber: parseInt(selectedSeat),
      bookingDate: new Date().toISOString().split('T')[0],
      matatu: { 
        id: parseInt(matatuId)
      },
      route: { 
        id: parseInt(routeId)
      }
    };

    console.log('Sending booking data:', bookingData);
    setLoading(true);

    try {
      const response = await fetch('http://localhost:8080/api/booking/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(bookingData),
      });

      if (response.ok) {
        const result = await response.json();
        setConfirmedBooking(result);
        setBookingConfirmed(true);
      } else {
        const error = await response.text();
        alert('Booking failed: ' + error);
      }
    } catch (error) {
      console.error('Error creating booking:', error);
      alert('Failed to create booking: ' + error.message);
    } finally {
      setLoading(false);
    }
  };

  if (bookingConfirmed && confirmedBooking) {
    return (
      <div style={{ 
        padding: '40px', 
        maxWidth: '600px', 
        margin: '0 auto',
        textAlign: 'center',
        border: '2px solid #4CAF50',
        borderRadius: '10px',
        backgroundColor: '#f0f9f0'
      }}>
        <h2 style={{ color: '#4CAF50' }}>✓ Booking Confirmed!</h2>
        <div style={{ fontSize: '18px', marginTop: '20px' }}>
          <p><strong>Passenger:</strong> {confirmedBooking.username}</p>
          <p><strong>Seat Number:</strong> {confirmedBooking.seatNumber}</p>
          <p><strong>Matatu ID:</strong> {confirmedBooking.matatu.id}</p>
          <p><strong>Booking Date:</strong> {confirmedBooking.bookingDate}</p>
        </div>
        <button 
          onClick={() => {
            setBookingConfirmed(false);
            setUser('');
            setSelectedSeat(null);
          }}
          style={{
            marginTop: '20px',
            padding: '10px 20px',
            backgroundColor: '#4CAF50',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer',
            fontSize: '16px'
          }}
        >
          Make Another Booking
        </button>
      </div>
    );
  }

  return (
    <div style={{ padding: '20px', maxWidth: '800px', margin: '0 auto' }}>
      <h2>Book Your Seat</h2>
      
      <div style={{ marginBottom: '20px' }}>
        <input
          type="text"
          placeholder="Enter your name"
          value={user}
          onChange={(e) => setUser(e.target.value)}
          style={{ 
            padding: '10px', 
            width: '300px', 
            fontSize: '16px',
            borderRadius: '5px',
            border: '1px solid #ccc'
          }}
        />
      </div>

      <div style={{ marginBottom: '20px' }}>
        <label style={{ fontWeight: 'bold' }}>Matatu ID: </label>
        <input
          type="number"
          value={matatuId}
          onChange={(e) => setMatatuId(parseInt(e.target.value) || 1)}
          min="1"
          style={{ padding: '5px', marginLeft: '10px', width: '80px' }}
        />
        
        <label style={{ marginLeft: '20px', fontWeight: 'bold' }}>Route ID: </label>
        <input
          type="number"
          value={routeId}
          onChange={(e) => setRouteId(parseInt(e.target.value) || 1)}
          min="1"
          style={{ padding: '5px', marginLeft: '10px', width: '80px' }}
        />
      </div>

      <SeatGrid 
        matatuId={matatuId} 
        onSeatSelect={setSelectedSeat}
      />

      <button
        onClick={handleBooking}
        disabled={!selectedSeat || !user || loading}
        style={{
          marginTop: '20px',
          padding: '15px 30px',
          backgroundColor: (selectedSeat && user && !loading) ? '#4CAF50' : '#ccc',
          color: 'white',
          border: 'none',
          borderRadius: '5px',
          cursor: (selectedSeat && user && !loading) ? 'pointer' : 'not-allowed',
          fontSize: '18px',
          fontWeight: 'bold'
        }}
      >
        {loading ? 'Booking...' : 'Confirm Booking'}
      </button>
    </div>
  );
}

export default BookingForm;
