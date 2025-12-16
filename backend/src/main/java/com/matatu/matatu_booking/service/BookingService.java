package com.matatu.matatu_booking.service;

import org.springframework.stereotype.Service;

import com.matatu.matatu_booking.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

   
    public boolean isSeatAvailable(Long matatuId, Integer seatNumber) {
        return bookingRepository.findByMatatuId(matatuId)
                .stream()
                .noneMatch(b -> b.getSeatNumber() == seatNumber);
    }
}