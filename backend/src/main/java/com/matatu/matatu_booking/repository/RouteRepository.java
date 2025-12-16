package com.matatu.matatu_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matatu.matatu_booking.entity.Route;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    // Custom query method for route search
    List<Route> findByStartLocationAndEndLocation(String start, String end);
}