package com.matatu.matatu_booking.controller;

import org.springframework.web.bind.annotation.*;

import com.matatu.matatu_booking.repository.BookingRepository;
import com.matatu.matatu_booking.repository.MatatuRepository;
import com.matatu.matatu_booking.repository.RouteRepository;

import java.util.*;

@RestController
@RequestMapping("/api/database")
public class DatabaseController {

    private final BookingRepository bookingRepository;
    private final MatatuRepository matatuRepository;
    private final RouteRepository routeRepository;

    public DatabaseController(BookingRepository bookingRepository,
                             MatatuRepository matatuRepository,
                             RouteRepository routeRepository) {
        this.bookingRepository = bookingRepository;
        this.matatuRepository = matatuRepository;
        this.routeRepository = routeRepository;
    }

    @GetMapping("/all")
    public Map<String, Object> getAllData() {
        Map<String, Object> allData = new HashMap<>();
        
        allData.put("bookings", bookingRepository.findAll());
        allData.put("matatus", matatuRepository.findAll());
        allData.put("routes", routeRepository.findAll());
        
        return allData;
    }
}