package casestudy;

import java.util.Scanner;

public class AirlineReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Flight flight = new Flight("AI123", 10);

        while (true) {
            System.out.println("\nAirline Reservation System");
            System.out.println("1. Book Seat");
            System.out.println("2. Cancel Seat");
            System.out.println("3. Show Available Seats");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    // 🔹 SHOW ONLY AVAILABLE SEATS BEFORE BOOKING
                    flight.showAvailableSeats();

                    System.out.print("Enter seat number to book: ");
                    int bookSeat = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter passenger name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter passenger age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter passenger gender: ");
                    String gender = scanner.nextLine();

                    passenger passenger = new passenger(name, age, gender);
                    flight.bookSeat(bookSeat, passenger);
                    break;

                case 2:
                    System.out.print("Enter seat number to cancel: ");
                    int cancelSeat = scanner.nextInt();
                    flight.cancelSeat(cancelSeat);
                    break;

                case 3:
                    flight.showAvailableSeats();
                    break;

                case 4:
                    System.out.println("Exiting... Thank you for using the system!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
