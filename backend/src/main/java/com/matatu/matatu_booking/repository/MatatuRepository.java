package com.matatu.matatu_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matatu.matatu_booking.entity.Matatu;

@Repository
public interface MatatuRepository extends JpaRepository<Matatu, Long> {
    // Custom query methods can be added here if needed
    // For example: Optional<Matatu> findByRegistration(String registration);
}