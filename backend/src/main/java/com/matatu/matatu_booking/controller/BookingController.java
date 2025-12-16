package com.matatu.matatu_booking.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.matatu.matatu_booking.entity.Booking;
import com.matatu.matatu_booking.repository.BookingRepository;
import com.matatu.matatu_booking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Create a new booking
     * POST /api/booking/create
     */
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {

        // Check if seat is available
        boolean available = bookingService.isSeatAvailable(
                booking.getMatatu().getId(),
                booking.getSeatNumber()
        );

        if (!available) {
            return ResponseEntity.badRequest().body("Seat already booked");
        }

        // Save the booking
        Booking savedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(savedBooking);
    }

    /**
     * Get all bookings
     * GET /api/booking
     */
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Get bookings for a specific matatu
     * GET /api/booking/matatu/{matatuId}
     */
    @GetMapping("/matatu/{matatuId}")
    public List<Booking> getBookingsByMatatu(@PathVariable Long matatuId) {
        return bookingRepository.findByMatatuId(matatuId);
    }
}