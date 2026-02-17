package com.airline.reservation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class FlightService {

    // All seat IDs (Business + Economy)
    private final Set<String> allSeats = new HashSet<>();

    // Permanently booked seats
    private final Set<String> bookedSeats = new HashSet<>();

    public FlightService() {

        // Business Class: Rows 1–3 (2-aisle-2)
        for (int row = 1; row <= 3; row++) {
            allSeats.add(row + "A");
            allSeats.add(row + "B");
            allSeats.add(row + "C");
            allSeats.add(row + "D");
        }

        // Economy Class: Rows 4–15 (3-aisle-3)
        for (int row = 4; row <= 15; row++) {
            allSeats.add(row + "A");
            allSeats.add(row + "B");
            allSeats.add(row + "C");
            allSeats.add(row + "D");
            allSeats.add(row + "E");
            allSeats.add(row + "F");
        }
    }

    // -----------------------------
    // GET AVAILABLE SEATS
    // -----------------------------
    public List<String> getAvailableSeats() {
        List<String> available = new ArrayList<>();

        for (String seat : allSeats) {
            if (!bookedSeats.contains(seat)) {
                available.add(seat);
            }
        }

        return available;
    }

    // -----------------------------
    // GET BOOKED SEATS
    // -----------------------------
    public List<String> getBookedSeats() {
        return new ArrayList<>(bookedSeats);
    }

    // -----------------------------
    // GET TOTAL SEATS
    // -----------------------------
    public int getTotalSeats() {
        return allSeats.size();
    }

    // -----------------------------
    // BOOK MULTIPLE SEATS
    // -----------------------------
    public synchronized String bookMultipleSeats(List<String> seats) {

        for (String seat : seats) {

            if (!allSeats.contains(seat)) {
                return "Invalid seat: " + seat;
            }

            if (bookedSeats.contains(seat)) {
                return "Seat already booked: " + seat;
            }
        }

        bookedSeats.addAll(seats);

        return "Seats booked successfully!";
    }

    public void resetBookings() {
        bookedSeats.clear();
    }
}
