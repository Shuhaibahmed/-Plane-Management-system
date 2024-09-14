
import java.util.InputMismatchException;
import java.util.Scanner;
public class W2055091_PlaneManagement {
    static int[] seats_num_A = new int[14];
    static int[] seats_num_B = new int[12];
    static int[] seats_num_c = new int[12];
    static int[] seats_num_d = new int[14];
    static Ticket[] array_tickets =new Ticket[100];
    static int ticketcount =0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println();
        System.out.println("-Welcome to the Plane Management application-\n");
        initializeSeats();
        displayMenu();
    }
    static void initializeSeats() {
        for (int i = 0; i < seats_num_A.length; i++) {
            seats_num_A[i] = 0;
        }
        for (int i = 0; i < seats_num_B.length; i++) {
            seats_num_B[i] = 0;
        }
        for (int i = 0; i < seats_num_c.length; i++) {
            seats_num_c[i] = 0;
        }
        for (int i = 0; i < seats_num_d.length; i++) {
            seats_num_d[i] = 0;
        }
    }

    static void displayMenu() {                                              //method is displayMenu print & option step
        while (true) {
            System.out.println("********************************************\n");
            System.out.println("*                  Menu                     *");
            System.out.println("\n********************************************");
            System.out.println("1. Buy a seat");
            System.out.println("2. Cancel a seat");
            System.out.println("3. Find first available seat");
            System.out.println("4. Show seating plan");
            System.out.println("5. Print tickets info");
            System.out.println("6. Search ticket");
            System.out.println("0. Exit");
            System.out.println("********************************************");
            System.out.print("Enter your choice: ");
            
            try{
            
                int option = scanner.nextInt();

                switch (option) {
                    case 0:
                        System.out.println("Thank you for using the Plane Management System...");
                        System.exit(0);
                        break;
                    case 1:
                        buySeat();
                        break;
                    case 2:
                        cancelSeat();
                        break;
                    case 3:
                        findFirstAvailable();
                        break;
                    case 4:
                        showSeatingPlan();
                        break;
                    case 5:
                        printTicketsInfo();
                        break;
                    case 6:
                        searchTicket();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }    
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();                                              // Clear the invalid input from the scanner
            }

        }
    }
    static void buySeat() {                                                                   //method is buy Seat print
        while (true) {
            try
               {System.out.println("--------------------------------");
                System.out.println("            buy Seat");
                System.out.println("--------------------------------");
                System.out.print("Enter row letter (A-D): ");
                char row = Character.toUpperCase(scanner.next().charAt(0));
                System.out.print("Enter seat number: ");
                int seatNumber = scanner.nextInt();

                int[] seats = getSeatsArray(row);

                if (isValidSeat(row, seatNumber)) {
                    if (seats[seatNumber - 1] == 0) {
                        System.out.print("Enter First Name: ");
                        String name = scanner.next();
                        System.out.print("Enter surname   : ");
                        String surname = scanner.next();
                        System.out.print("Enter email     : ");
                        String email = scanner.next();

                        Person person = new Person(name, surname, email);
                        Ticket ticket = new Ticket( row, seatNumber, calculatePrice(row,seatNumber), person);
                        array_tickets[ticketcount++]=ticket;
                        Ticket save_ticket = new Ticket( row, seatNumber, calculatePrice(row,seatNumber), person);
                        save_ticket.save();

                        seats[seatNumber - 1] = 1;                                                  // Mark seat as sold
                        System.out.println("Seat successfully sold!");
                        break;
                    } else {
                        System.out.println("Seat already sold. Please choose another seat.\n");
                    }
                } else {
                    System.out.println("Invalid row or seat number. Please try again.\n");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid row or seat number. Please try again.\n");
                scanner.next();                                              // Clear the invalid input from the scanner
            }

        }
    }

    static void cancelSeat() {                                                             //method is cancel seat print
            try {
                System.out.println("--------------------------------");
                System.out.println("          CancelSeat");
                System.out.println("--------------------------------");
                System.out.print("Enter row letter (A-D): ");
                char row = Character.toUpperCase(scanner.next().charAt(0));
                System.out.print("Enter seat number: ");
                int seatNumber = scanner.nextInt();

                int[] seats = getSeatsArray(row);

                if (isValidSeat(row, seatNumber)) {
                    if (seats[seatNumber - 1] == 1) {
                        seats[seatNumber - 1] = 0;                                       // Mark seat as available again
                        
                        System.out.println("Seat successfully cancelled and made available again!\n");
                    } else {
                        System.out.println("Seat is already available. Please enter a sold seat to cancel.\n");
                    }
                } 
                
                else {
                    System.out.println("Invalid row or seat number. Please try again.\n");
                }
            
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid row or seat number. Please try again.\n");
                scanner.next();                                              // Clear the invalid input from the scanner
            }
    }

    static void findFirstAvailable() {                                            //method is find First Available seat
        System.out.println("-----------------------------------------");
        System.out.println("       find First Available seat");
        System.out.println("-----------------------------------------\n");
        for (char i = 'A'; i< 'E'; i ++) {
            int[] seats_ = firstavailabseats(i);
            int j = 0;
            while (j<seats_.length) {
                if (seats_[j] == 0) {
                   System.out.println("Found a seat in row " + i + " seat number is " + Integer.valueOf(j+1));
                   break; 
                }
                j++;
            }

        }
        System.out.println("\n-----------------------------------------\n");
    }

    static void showSeatingPlan() {                                               //method is Seating Plan option print
        System.out.println("--------------------------------");
        System.out.println("          Seating Plan");
        System.out.println("--------------------------------\n");
        System.out.print(" A  ");
        printSeatRow(seats_num_A);
        System.out.print(" B  ");
        printSeatRow(seats_num_B);
        System.out.print(" C  ");
        printSeatRow(seats_num_c);
        System.out.print(" D  ");
        printSeatRow(seats_num_d);
    }
    static void printSeatRow(int[] seats) {                                         //method is Show seating plan option
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                System.out.print("O ");                                                                // Available seat
            } else {
                System.out.print("X ");                                                                     // Sold seat
            }
        }
        System.out.println();                                                                    // Move to the next row
    } 

    static boolean isValidSeat(char row, int seatNumber) {                                       // chek the Valid Seat?
        switch (row) {
            case 'A':
                return (seatNumber >= 1 && seatNumber <= 14);
            case 'B':
                return (seatNumber >= 1 && seatNumber <= 12);
            case 'C':
                return (seatNumber >= 1 && seatNumber <= 12);
            case 'D':
                return (seatNumber >= 1 && seatNumber <= 14);
            default:
                return false;
        }
    }
    
    static int[] getSeatsArray(char row) {                                                  //method is get Seats Array
        switch (row) {
            case 'A':
                return seats_num_A;
            case 'B':
                return seats_num_B;
            case 'C':
                return seats_num_c;
            case 'D':
                return seats_num_d;
            default:
                return null;
        }
    }

    static int[] firstavailabseats(char i) {                                 //method is get first availab seats option
        switch (i) {
            case 'A':
                return seats_num_A;
            case 'B':
                return seats_num_B;
            case 'C':
                return seats_num_c;
            case 'D':
                return seats_num_d;
            default:
                return null;
        }
    }

    static double calculatePrice(char row,int seatNumber){                           //method that calculatePrice option
        switch (seatNumber) {
            case 1,2,3,4,5:
                return 200.00;
            case 6,7,8,9:
                return 150.00;
            default:
                return 180.00;
            }
    }

    static void  printTicketsInfo() {                                 //method that run print_ticket_info in menu option
        System.out.println("------------------------------------------------------");
        System.out.println("      Tickets information and total sales");
        System.out.println("------------------------------------------------------");

        double totalAmount = 0; // Initialize total amount

        // Iterate over each row
        for (char i = 'A'; i <= 'D'; i++) {
            int[] seats_ = firstavailabseats(i);

                                                                                    // Iterate over each seat in the row
            for (int j = 0; j < seats_.length; j++) {
                if (seats_[j] == 1) {                                                       // Check if the seat is sold
                    for (Ticket ticket : array_tickets) {
                                                                  // Check if the ticket matches the row and seat number
                        if (ticket != null && ticket.getRow() == i && ticket.getSeatNumber() == j + 1) {
                                                                            // Print the information of the found ticket
                            System.out.println("Ticket Information:");
                            ticket.printTicketInfo();
                            totalAmount += ticket.getPrice();                        // Add ticket price to total amount
                            System.out.println();
                        }
                    }
                }
            }
        }
                                                             // Print total amount after printing all ticket information
        System.out.println("Total Amount Of Sales: £" + totalAmount);
        System.out.println("\n--------------------------------\n");
    }
    
    static void searchTicket() {                                                       //method is search Ticket option
        try {
            System.out.println("--------------------------------");
            System.out.println("          Search Ticket");
            System.out.println("--------------------------------\n");
            System.out.print("Enter row letter (A-D): ");
            char row = Character.toUpperCase(scanner.next().charAt(0));
            System.out.print("Enter seat number: ");
            int seatNumber = scanner.nextInt();
            int[] seats = getSeatsArray(row);
            if (isValidSeat(row, seatNumber)) {
                if (seats[seatNumber - 1] == 0) {

                    System.out.println("This seat is available");
                                                                 // Iterate through each ticket in the tickets ArrayList
                }
                else {
                    System.out.println("Seat already sold.Here required informations about that ticket's buyer.\n");
                    for (Ticket ticket : array_tickets) {
                                                         // Check if the ticket matches the provided row and seat number
                        if (ticket.getRow() == row && ticket.getSeatNumber() == seatNumber) {
                                                                            // Print the information of the found ticket
                            System.out.println("Ticket Information");
                            ticket.printTicketInfo();
                            break;                                             // Exit the loop once the ticket is found
                    
                        }
                    }
                }
            }
            else {
                System.out.println("Invalid row or seat number. Please try again.\n");
            }
        }
        catch (InputMismatchException e) {
        System.out.println("Invalid row or seat number. Please try again.\n");
        scanner.next();                                                                                             // Clear the invalid input from the scanner
        }
        System.out.println("--------------------------------\n");
    }
}