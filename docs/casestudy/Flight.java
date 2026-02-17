package casestudy;

import java.util.ArrayList;
import java.util.HashMap;

public class Flight {
    private String flightNumber;
    private int totalSeats;
    private ArrayList<Integer> bookedSeats;
    private HashMap<Integer, passenger> seatPassengerMap;

    public Flight(String flightNumber, int totalSeats) {
        this.flightNumber = flightNumber;
        this.totalSeats = totalSeats;
        this.bookedSeats = new ArrayList<>();
        this.seatPassengerMap = new HashMap<>();
    }

    public boolean bookSeat(int seatNumber, passenger passenger) {
        if (seatNumber > 0 && seatNumber <= totalSeats &&
                !bookedSeats.contains(seatNumber)) {
            bookedSeats.add(seatNumber);
            seatPassengerMap.put(seatNumber, passenger);
            System.out.println("Seat " + seatNumber + " booked successfully!");
            printTicket(seatNumber);
            return true;
        } else {
            System.out.println("Seat booking failed! Either the seat is already booked or invalid.");
            return false;
        }
    }

    public void cancelSeat(int seatNumber) {
        if (bookedSeats.contains(seatNumber)) {
            bookedSeats.remove(Integer.valueOf(seatNumber));
            seatPassengerMap.remove(seatNumber);
            System.out.println("Seat " + seatNumber + " canceled successfully.");
        } else {
            System.out.println("Cancellation failed! The seat was not booked.");
        }
    }

    public void showAvailableSeats() {
        System.out.println("\nAvailable seats:");
        for (int i = 1; i <= totalSeats; i++) {
            if (!bookedSeats.contains(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    private void printTicket(int seatNumber) {
        passenger p = seatPassengerMap.get(seatNumber);
        System.out.println("\n---------- Ticket ----------");
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Seat Number: " + seatNumber);
        System.out.println(p.getDetails());
        System.out.println("-----------------------------\n");
    }
}
