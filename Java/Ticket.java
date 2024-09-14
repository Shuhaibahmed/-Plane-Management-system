import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private char row;
    private int seatNumber;
    private double price;
    private Person person;

    public Ticket(char row, int seatNumber, double price, Person person) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.price = price;
        this.person = person;

        
    }



    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void printTicketInfo() {

        System.out.println("Row   : " + row);
        System.out.println("Seat  : " + seatNumber);
        System.out.println("Price : " + "£"+ price);
        System.out.println("Person Information");
        person.printInfo();
    }

    public void save() {
        String filename = row + String.valueOf(seatNumber) + ".txt"; // Constructing filename based on row and seat number
        try (FileWriter myWriter = new FileWriter(filename);) {
            // Writing ticket information to the file
            myWriter.write("Row: " + row);

            myWriter.write("\nSeat: " + seatNumber);
            
            myWriter.write("\nPrice: " + price);
            
            myWriter.write("\nPerson Information:");
            
            myWriter.write("\nName: " + person.getName());
            
            myWriter.write("\nSurname: " + person.getSurname());
            
            myWriter.write("\nEmail: " + person.getEmail());
           
            myWriter.close();
            System.out.println("\nTicket information saved to " + filename);
            
        } catch (IOException e) {
            System.out.println("Error saving ticket information to file " );
            e.printStackTrace();
        }
    }

}
