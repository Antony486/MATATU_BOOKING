package com.matatu.matatu_booking.controller;


import org.springframework.web.bind.annotation.*;

import com.matatu.matatu_booking.entity.Route;
import com.matatu.matatu_booking.repository.RouteRepository;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class RouteController {

    private final RouteRepository routeRepository;

    public RouteController(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    /**
     * Search for routes by start and end location
     * GET /api/routes/search?start=Nairobi&end=Mombasa
     */
    @GetMapping("/search")
    public List<Route> searchRoutes(
        @RequestParam String start,
        @RequestParam String end
    ) {
        return routeRepository.findByStartLocationAndEndLocation(start, end);
    }

    /**
     * Get all routes
     * GET /api/routes
     */
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    /**
     * Create a new route (for testing/admin purposes)
     * POST /api/routes
     */
    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return routeRepository.save(route);
    }
}