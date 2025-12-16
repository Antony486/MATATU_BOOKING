package com.matatu.matatu_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matatu.matatu_booking.entity.Booking;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Find all bookings for a specific matatu (for seat availability check)
    List<Booking> findByMatatuId(Long matatuId);
}