import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String type;  
    double price;
    boolean isAvailable;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = true; 
    }

    public void displayRoom() {
        System.out.println("Room Number: " + roomNumber + ", Type: " + type + ", Price: $" + price + ", Available: " + isAvailable);
    }
}

class Reservation {
    String guestName;
    Room room;
    int nights;
    double totalCost;

    public Reservation(String guestName, Room room, int nights) {
        this.guestName = guestName;
        this.room = room;
        this.nights = nights;
        this.totalCost = room.price * nights;
    }

    public void displayReservation() {
        System.out.println("\n--- Booking Details ---");
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Number: " + room.roomNumber);
        System.out.println("Room Type: " + room.type);
        System.out.println("Number of Nights: " + nights);
        System.out.println("Total Cost: $" + totalCost);
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 100));
        rooms.add(new Room(102, "Deluxe", 150));
        rooms.add(new Room(103, "Suite", 200));
        rooms.add(new Room(104, "Standard", 100));
        rooms.add(new Room(105, "Suite", 200));
    }
    public static void displayAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms) {
            if (room.isAvailable) {
                room.displayRoom();
            }
        }
    }

    public static void makeReservation() {
        System.out.print("\nEnter your name: ");
        String guestName = sc.nextLine();

        displayAvailableRooms();
        System.out.print("\nEnter room number to book: ");
        int roomNumber = sc.nextInt();
        System.out.print("Enter number of nights: ");
        int nights = sc.nextInt();
        sc.nextLine();  // Consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            selectedRoom.isAvailable = false;
            Reservation reservation = new Reservation(guestName, selectedRoom, nights);
            reservations.add(reservation);
            System.out.println("\nBooking successful!");
            reservation.displayReservation();
            processPayment(reservation);
        } else {
            System.out.println("Room is not available or does not exist.");
        }
    }
    public static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("\n--- All Reservations ---");
            for (Reservation reservation : reservations) {
                reservation.displayReservation();
            }
        }
    }

    public static void processPayment(Reservation reservation) {
        System.out.println("\n--- Payment Processing ---");
        System.out.println("Total Amount: $" + reservation.totalCost);
        System.out.print("Enter payment amount: ");
        double payment = sc.nextDouble();
        if (payment >= reservation.totalCost) {
            System.out.println("Payment successful! Change: $" + (payment - reservation.totalCost));
        } else {
            System.out.println("Payment failed. Insufficient amount.");
        }
    }

    public static void main(String[] args) {
        initializeRooms();

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View all reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();  

            switch (option) {
                case 1:
                    displayAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        sc.close();
    }
}
