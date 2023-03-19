/**
 * The Theatre class represents a simple booking system for a theatre.
 * It allows users to view available seats, book seats, save and load seating formation, and display ticket information.
 * The class contains methods for initializing the theatre's seating arrangement, displaying available seats,
 * buying/cancelling tickets, saving and loading seating information to/from a file, and displaying ticket information sorted and unsorted.
 *
 * This class uses the following classes:
 * @see Ticket
 * @see Person
 *
 * @author Amandha Panagoda
 * @version 1.0
 * @since 10.02.2023
 */

import java.io.*;
import java.util.*;

public class Theatre {
    //3 arrays to store the seats
    private static final int[] row1 = new int[12];
    private static final int[] row2 = new int[16];
    private static final int[] row3 = new int[20];

    //Array List for storing ticket information
    private static final ArrayList<Ticket> ticketList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------------------------------------");
        print_border("W E L C O M E   T  O   T H E   N E W   T H E A T R E !");
        set_seats_unoccupied(); //sets all seats to unoccupied
        Scanner input = new Scanner(System.in);
        int menuOption = -1;

        while(menuOption != 0) {
            try {
                print_border("M E N U");
                System.out.println("\t1) Buy a ticket\n" +
                        "\t2) Print seating area\n" +
                        "\t3) Cancel ticket\n" +
                        "\t4) List available seats\n" +
                        "\t5) Save to file\n" +
                        "\t6) Load from file\n" +
                        "\t7) Print ticket information and total price\n" +
                        "\t8) Sort tickets by price\n" +
                        "\t\t0) Quit");
                System.out.println("Enter option:");
                menuOption = input.nextInt();
                if(menuOption == 0) break;
                System.out.println("----------------------------------------------------------------------------------------------");
                menu_options(menuOption);
                System.out.println("----------------------------------------------------------------------------------------------");
            } catch(InputMismatchException e) {
                System.out.println("Invalid Input. Please enter a number!");
                input.nextLine(); // consume the invalid input to avoid an infinite loop
            }
        }
        System.out.println("Option 0 entered. Exiting program...");
    }

    /**
     * Sets all seats in all the rows as unoccupied.
     * All the elements in three arrays row1,row,row3 are set to 0.
     */
    private static void set_seats_unoccupied() {
        for(int i=0; i<12; i++) {
            row1[i] = 0;
        }
        for(int i=0; i<16; i++) {
            row2[i] = 0;
        }
        for(int i=0; i<20; i++) {
            row3[i] = 0;
        }
    }

    /**
     * Displays menu options
     * @param menuOption is the option tha user chooses from the menu
     */
    private static void menu_options(int menuOption) {
        switch (menuOption) {
            case 1:
                print_border("Buy Ticket"); //buy_ticket method
                buy_ticket();
                break;
            case 2:
                print_border("Seating Areas and Stage"); //print_seating_area method
                print_seating_area();
                break;
            case 3:
                print_border("Cancel Ticket"); //cancel_ticket method
                cancel_ticket();
                break;
            case 4:
                print_border("Available Seats");  //show_available method
                show_available();
                break;
            case 5:
                save(); //save method -> saves seat allocation details
                break;
            case 6:
                load(); //loads the saved seat details from the file
                break;
            case 7:
                print_border("Ticket Information"); //prints the ticket information unsorted
                show_tickets_info();
                break;
            case 8:
                print_border("Sorted(price) Ticket Information"); // prints the ticket information sorted
                sort_tickets();
                break;
            default:
                System.out.println("Invalid Option. Please choose an option from 0-8");
        }
    }

    /**
     * Create a border around the text that is passed.
     * @param text the text that should have a border around it.
     * reference -> <a href="https://herongyang.com/Unicode/Block-U2500-Box-Drawing.html">Unicodes</a>
     */
    private static void print_border(String text) {
        // characters are copied from box unicodes
        char horizontal = '─';
        char top_left_corner ='┌';
        char top_right_corner = '┐';
        char vertical = '│';
        char bottom_right_corner ='┘';
        char bottom_left_corner = '└';

        int horizontal_length = text.length() + 10;
        System.out.print(top_left_corner);
        for(int i=0; i<horizontal_length; i++){
            System.out.print(horizontal);
        }
        System.out.print(top_right_corner);
        System.out.println();
        System.out.println(vertical+"     "+text+"     "+vertical);

        System.out.print(bottom_left_corner);
        for(int i=0; i<horizontal_length; i++){
            System.out.print(horizontal);
        }
        System.out.print(bottom_right_corner);
        System.out.println();
    }

    /**
     * Allows a user to purchase a ticket for a theater by inputting a row number and seat number.
     * If the selected seat is not already occupied, the seat will be reserved and the user will be notified.
     * If an invalid row or seat number is entered, the user will be prompted to enter a valid number.
     * catches InputMismatchException if the user inputs a non-numeric value for the row or seat number
     */
    private static void buy_ticket() { 
        Scanner input = new Scanner(System.in);
        int rowNumber;
        int seatNumber;
        boolean purchaseTicket = false;

        while(true) {
            try {
                System.out.print("Enter row number: ");
                rowNumber = input.nextInt(); //get the row number
                if(rowNumber < 1 || rowNumber > 3){
                    System.out.println("Invalid row number. Please select a row number from 1-3\n");
                    continue;
                }
                System.out.print("Enter seat number: ");
                seatNumber = input.nextInt(); //get seat number

                if(rowNumber == 1) { //1st Row seats
                    if(seatNumber >= 1 && seatNumber <= 12) {
                        if(row1[seatNumber-1] == 0) {
                            row1[seatNumber-1] = 1;
                            purchaseTicket = true;
                        }
                        break;
                    } else {
                        System.out.println("Invalid seat number. Please select a seat number from 1-12");
                    }
                } else if(rowNumber == 2) { //2nd Row seats
                    if(seatNumber >= 1 && seatNumber <= 16) {
                        if(row2[seatNumber-1] == 0) {
                            row2[seatNumber-1] = 1;
                            purchaseTicket = true;
                        }
                        break;
                    } else {
                        System.out.println("Invalid seat number. Please select a seat number from 1-16");
                    }
                } else { //3rd Row seats
                    if(seatNumber >= 1 && seatNumber <= 20) {
                        if(row3[seatNumber-1] == 0) {
                            row3[seatNumber-1] = 1;
                            purchaseTicket = true;
                        }
                        break;
                    } else {
                        System.out.println("Invalid seat number. Please select a seat number from 1-20");
                    }
                }
            } catch(InputMismatchException e) {
                System.out.println("Please enter a number!");
                System.out.println();
                input.nextLine();
            }
        }
        if(purchaseTicket) { //if the seat is available purchaseTicket is true
            reserve_ticket(rowNumber,seatNumber); //buy the ticket after entering personal information
        } else {
            System.out.println("Seat already occupied!");
        }
    }

    /**
     * Prompts the user to enter personal details and ticket price to reserve a ticket with the specified row and seat number.
     * @param rowNumber the row number of the seat to be reserved
     * @param seatNumber the seat number to be reserved
     */
    private static void reserve_ticket(int rowNumber,int seatNumber) {
        Scanner input = new Scanner(System.in);
        System.out.println("Seat is available! Please enter the details to buy the ticket.\n");
        String firstName = readName("First Name", input);
        String surname = readName("Last Name", input);
        String email = readEmail(input);
        double ticketPrice = readTicketPrice(input);

        System.out.println("\nTicket purchase successful!\nSeat number "+seatNumber+" in Row number "+rowNumber+" reserved!");
        Person person = new Person(firstName, surname, email);
        Ticket ticket = new Ticket(rowNumber, seatNumber, ticketPrice, person);
        ticketList.add(ticket);
    }

    /**
     * Reads the name of a person (both first and last)
     * @param nameType the type of name being read, such as "firstname" or "surname"
     * @param input the Scanner object used to read input
     * @return the name entered by the user, converted to uppercase
     */
    private static String readName(String nameType, Scanner input) {
        while(true) {
            System.out.print(nameType + ": ");
            String name = input.nextLine().toUpperCase();

            if(!name.matches("[a-zA-Z]*")) {
                System.out.println("Name should only contain letters.\n");
                continue;
            }
            return name;
        }
    }

    /**
     * Reads an email address from the console input and validates its format.
     * @param input the Scanner object used to read input
     * @return the email address entered by the user
     */
    private static String readEmail(Scanner input) {
        while(true) {
            System.out.print("Email: ");
            String email = input.nextLine();
            if(!isValidEmailAddress(email)) {
                System.out.println("Invalid email address!\n");
                continue;
            }
            return email;
        }
    }

    /**
     * Reads the price of a ticket from the user and ensures it is at least £10.
     * @param input the Scanner object used to read input
     * @return the price of the ticket
     * catches InputMismatchException if the user enters a non-numeric value
     */
    private static double readTicketPrice(Scanner input) {
        while(true) {
            try {
                System.out.print("Enter ticket price (min £10): ");
                double ticketPrice = input.nextDouble();
                if(ticketPrice < 10) {
                    System.out.println("Minimum ticket price is £10");
                } else {
                    return ticketPrice;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Price! Please enter a number.\n");
                input.nextLine();
            }
        }
    }

    /**
     * Checks whether the given email address is valid or not.
     * @param email the email address to be validated
     * @return true if the email address is valid, false otherwise
     */
    private static boolean isValidEmailAddress(String email) {
        //reference -> https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Prints a graphical representation of the seating area of the theatre
     * Shows which seats are occupied and which seats are unoccupied.
     * The occupied seats are represented by "X", while unoccupied seats are represented by "O".
     */
    private static void print_seating_area() {
        System.out.println("            *********\n            * STAGE *\n            *********");
        System.out.print("         ");
        for(int n=0; n < row1.length; n++){
            if(n==6){
                System.out.print("  ");
            }
            System.out.print(row1[n]==1 ? "X" : "O");
        }
        System.out.println();
        System.out.print("       ");
        for(int n=0; n < row2.length; n++){
            if(n==8){
                System.out.print("  ");
            }
            System.out.print(row2[n]==1 ? "X" : "O");
        }
        System.out.println();
        System.out.print("     ");
        for(int n=0; n < row3.length; n++){
            if(n==10){
                System.out.print("  ");
            }
            System.out.print(row3[n]==1 ? "X" : "O");
        }
        System.out.println();
        System.out.println("\nX - occupied\tO - unoccupied");
    }

    /**
     * Allows the user to cancel a ticket by selecting the row and seat number of the ticket to be cancelled.
     * If the row and seat number are valid and correspond to an already reserved seat, the seat is marked as unoccupied
     and removed from the ticketList. If the seat is not already reserved, an error message is displayed.
     *catches InputMismatchException if the input is not an integer.
     */
    private static void cancel_ticket() {
        Scanner input = new Scanner(System.in);
        int rowNumber, seatNumber;
        boolean cancelledTicket = false;

        while(true) {
            System.out.println("Enter row number: ");
            rowNumber = input.nextInt();
            if(rowNumber<1 || rowNumber>3) {
                System.out.println("Invalid row number. Please enter a row number between 1-3\n");
                continue;
            }
            System.out.println("Enter seat number: ");
            seatNumber = input.nextInt();

            if(rowNumber == 1) {
                if(seatNumber >= 1 && seatNumber <=12) {
                    if(row1[seatNumber-1] == 1) {
                        row1[seatNumber-1] = 0;
                        cancelledTicket = true;
                    } 
                    break;
                } else {
                    System.out.println("Invalid seat number. Please enter a seat number between 1-12\n");
                }
            } else if(rowNumber == 2) {
                if(seatNumber >= 1 && seatNumber <=16) {
                    if(row2[seatNumber-1] == 1) {
                        row2[seatNumber-1] = 0;
                        cancelledTicket = true;
                    } 
                    break;
                } else {
                    System.out.println("Invalid seat number. Please enter a seat number between 1-16\n");
                }
            } else {
                if(seatNumber >= 1 && seatNumber <=20) {
                    if(row3[seatNumber-1] == 1) {
                        row3[seatNumber-1] = 0;
                        cancelledTicket = true;
                    } 
                    break;
                } else {
                    System.out.println("Invalid seat number. Please enter a seat number between 1-20\n");
                }
            }
        }

        if(cancelledTicket) {
            for (Ticket t : ticketList) {
                if (t.getRow() == rowNumber && t.getSeat() == seatNumber) {
                    ticketList.remove(t);
                    break;
                }
            }
            System.out.printf("You successfully cancelled the ticket for seat number %d in row %d.\n",seatNumber,rowNumber);
        } else {
            System.out.println("The seat is not yet reserved!");
        }
    }

    /**
     * Displays the available seats in each row in a list like format
     */
    private static void show_available() {
        System.out.print("\nSeats available in Row 1: ");
        for(int i=0; i<row1.length; i++) {
            if(row1[i] == 0){
                System.out.print((i+1)+"  ");
            }
        }
        System.out.print("\nSeats available in Row 2: ");
        for(int i=0; i<row2.length; i++) {
            if(row2[i] == 0){
                System.out.print((i+1)+"  ");
            }
        }
        System.out.print("\nSeats available in Row 3: ");
        for(int i=0; i<row3.length; i++) {
            if(row3[i] == 0){
                System.out.print((i+1)+"  ");
            }
        }
        System.out.println();
    }

    /**
     * Saves the current seating arrangement to a file named "seatingData.txt".
     * Uses a FileWriter and PrintWriter to write the contents of the row1, row2 and row3 arrays to the file.
     * catches IOException if an error occurs while writing to the file
     * reference -> <a href="https://www.tutorialspoint.com/How-to-store-the-contents-of-arrays-in-a-file-using-Java">File Handling</a>
     */
    private static void save() {
        try {
            FileWriter fw = new FileWriter("seatingData.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (int j : row1) {
                pw.println(j);
            }
            for (int j : row2) {
                pw.println(j);
            }
            for (int j : row3) {
                pw.println(j);
            }
            pw.close();
            System.out.println("Successfully saved the seat information in seatingData.txt file");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An unexpected error occurred! "+e.getMessage());
        }
    }

    /**
     * Loads the seat information from a file and updates the seating data.
     * catches IOException if there is an error reading the file
     */
    private static void load() {
        try {
            FileInputStream fis = new FileInputStream("seatingData.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String row1String = br.readLine();
            String row2String = br.readLine();
            String row3String = br.readLine();

            String[] row1Array = row1String.split(",");
            String[] row2Array = row2String.split(",");
            String[] row3Array = row3String.split(",");

            for (int i = 0; i < row1Array.length; i++) {
                row1[i] = Integer.parseInt(row1Array[i]);
            }
            for (int i = 0; i < row2Array.length; i++) {
                row2[i] = Integer.parseInt(row2Array[i]);
            }
            for (int i = 0; i < row3Array.length; i++) {
                row3[i] = Integer.parseInt(row3Array[i]);
            }

            br.close();
            fis.close();
            System.out.println("Successfully updated seat information from the file!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Displays information about all the purchased tickets and their total price
     * The seat number, row number along with the person details are shown
     * The method print the information of each ticket using the Ticket class's print() method.
     * Unsorted
     */
    private static void show_tickets_info() {
        double total = 0;
        for (Ticket ticketInfo : ticketList) {
            ticketInfo.print();
            total += ticketInfo.getPrice();
            System.out.println();
        }
        System.out.printf("Total Price of all Tickets: £%.2f",total);
        System.out.println();
    }

    /**
     * This method sorts the ticketList by ticket price in ascending order.
     * Prints the information of each ticket in the sorted order.
     */
    private static void sort_tickets() {
        // Selection Sort is used to sort the array list
        List<Ticket> sortedTicketList = new ArrayList<>(Theatre.ticketList);
        for (int i = 0; i < sortedTicketList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sortedTicketList.size(); j++) {
                if (sortedTicketList.get(j).getPrice() < sortedTicketList.get(minIndex).getPrice()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(sortedTicketList, i, minIndex);
            }
        }
        for(Ticket ticket: sortedTicketList) {
            ticket.print();
            System.out.println();
        }
    }

    /**
     * Swaps two elements in a list.
     * @param sortedTicketList the list containing the elements to swap
     * @param i the index of the first element to swap
     * @param j the index of the second element to swap
     */
    private static void swap(List<Ticket> sortedTicketList, int i, int j) {
        Ticket temp = sortedTicketList.get(i);
        sortedTicketList.set(i, sortedTicketList.get(j));
        sortedTicketList.set(j, temp);
    }
}

