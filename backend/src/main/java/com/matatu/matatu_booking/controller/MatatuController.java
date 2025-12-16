package com.matatu.matatu_booking.controller;


import org.springframework.web.bind.annotation.*;

import com.matatu.matatu_booking.entity.Matatu;
import com.matatu.matatu_booking.repository.MatatuRepository;

import java.util.List;

@RestController
@RequestMapping("/api/matatus")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class MatatuController {

    private final MatatuRepository matatuRepository;

    public MatatuController(MatatuRepository matatuRepository) {
        this.matatuRepository = matatuRepository;
    }

    /**
     * Get all matatus
     * GET /api/matatus
     */
    @GetMapping
    public List<Matatu> getAllMatatus() {
        return matatuRepository.findAll();
    }

    /**
     * Get a specific matatu by ID
     * GET /api/matatus/{id}
     */
    @GetMapping("/{id}")
    public Matatu getMatatuById(@PathVariable Long id) {
        return matatuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matatu not found"));
    }

    /**
     * Create a new matatu (for testing/admin purposes)
     * POST /api/matatus
     */
    @PostMapping
    public Matatu createMatatu(@RequestBody Matatu matatu) {
        return matatuRepository.save(matatu);
    }
}