package com.airline.reservation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.reservation.service.FlightService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // -----------------------------
    // GET AVAILABLE SEATS
    // -----------------------------
    @GetMapping("/seats")
    public List<String> getAvailableSeats() {
        return flightService.getAvailableSeats();
    }

    // -----------------------------
    // GET BOOKED SEATS
    // -----------------------------
    @GetMapping("/booked")
    public List<String> getBookedSeats() {
        return flightService.getBookedSeats();
    }

    // -----------------------------
    // GET TOTAL SEATS
    // -----------------------------
    @GetMapping("/total")
    public int getTotalSeats() {
        return flightService.getTotalSeats();
    }

    // -----------------------------
    // BOOK MULTIPLE SEATS
    // -----------------------------
    @PostMapping("/book")
    public String bookSeats(@RequestBody List<String> seats) {
        return flightService.bookMultipleSeats(seats);
    }

    // -----------------------------
    // RESET BOOKINGS
    // -----------------------------
    @PostMapping("/reset")
    public String resetBookings() {
        flightService.resetBookings();
        return "All bookings cleared!";
    }
}
