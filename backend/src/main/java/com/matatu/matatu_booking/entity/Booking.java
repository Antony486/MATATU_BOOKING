package com.matatu.matatu_booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String user;
    
    private Integer seatNumber;   // ✅ Integer NOT int
    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn(name = "matatu_id")
    private Matatu matatu;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    // Constructors
    public Booking() {
    }

    // ✅ CHANGE THIS: int → Integer
    public Booking(String user, Integer seatNumber, LocalDate bookingDate, Matatu matatu, Route route) {
        this.user = user;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.matatu = matatu;
        this.route = route;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    // ✅ CHANGE THIS: int → Integer
    public Integer getSeatNumber() {
        return seatNumber;
    }

    // ✅ CHANGE THIS: int → Integer
    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Matatu getMatatu() {
        return matatu;
    }

    public void setMatatu(Matatu matatu) {
        this.matatu = matatu;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}