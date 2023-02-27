import java.io.*;
import java.util.*;

public class Theatre {
    static int[] row1 = new int[12];
    static int[] row2 = new int[16];
    static int[] row3 = new int[20];
    static ArrayList<Ticket> ticketList = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------");
        print_border("W E L C O M E   T  O   T H E   N E W   T H E A T R E !");

        for(int i=0; i<12; i++) {
            row1[i] = 0;
        }
        for(int i=0; i<16; i++) {
            row2[i] = 0;
        }
        for(int i=0; i<20; i++) {
            row3[i] = 0;
        }

        while(true) {
            try {
                Scanner input = new Scanner(System.in);
                int menuOption;
                //add a nice first impression.
                System.out.println("\t1) Buy a ticket\n\t2) Print seating area\n\t3) Cancel ticket\n\t4) List available seats\n\t5) Save to file\n\t6) Load from file\n\t7) Print ticket information and total price\n\t8) Sort tickets by price\n\t\t0) Quit");
                System.out.println("Enter option:");
                menuOption = input.nextInt();
                System.out.println("----------------------------------------------------------------------------------------------");
                while(menuOption != 0) {
                    switch (menuOption) {
                        case 1:
                            //buy_ticket method
                            print_border("Buy Ticket");
                            buy_ticket();
                            break;
                        case 2:
                            //print_seating_area method
                            print_border("Seating Areas and Stage");
                            print_seating_area();
                            break;
                        case 3:
                            //cancel_ticket method
                            print_border("Cancel Ticket");
                            cancel_ticket();
                            break;
                        case 4:
                            //show_available method
                            print_border("Available Seats");
                            show_available();
                            break;
                        case 5:
                            //save method -> saves seat allocation details
                            save();
                            break;
                        case 6:
                            load();
                            break;
                        case 7:
                            show_tickets_info();
                            break;
                        case 8:

                        default:
                            System.out.println("Invalid Option. Please choose an option from 0-8");
                    }
                    System.out.println("----------------------------------------------------------------------------------------------");
                    System.out.println("\t1) Buy a ticket\n\t2) Print seating area\n\t3) Cancel ticket\n\t4) List available seats\n\t5) Save to file\n\t6) Load from file\n\t7) Print ticket information and total price\n\t8) Sort tickets by price\n\t\t0) Quit");
                    System.out.println("Enter option:");
                    menuOption = input.nextInt();
                    System.out.println("----------------------------------------------------------------------------------------------");
                }
                System.out.println("Option 0 entered. Exiting program...");
                break;
            } catch(InputMismatchException e) {
                System.out.println("Invalid Input. Please enter a number!"); 
            }
        }
    }
    private static void print_border(String text) {
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
    private static void buy_ticket() { 
        Scanner input = new Scanner(System.in);
        int rowNumber, seatNumber;
        String firstName,surname,email;
        double ticketPrice;
        boolean purchaseTicket = false;

        while(true) {
            try {
                System.out.print("Enter row number: ");
                rowNumber = input.nextInt();
                if(rowNumber<1 || rowNumber>3){
                    System.out.println("Invalid row number. Please select a row number from 1-3\n");            
                    continue;
                }
                System.out.print("Enter seat number: ");
                seatNumber = input.nextInt();

                if(rowNumber == 1) { //1st Row seats
                    if(seatNumber >= 1 && seatNumber <=12) {
                        ticketPrice = 10;
                        if(row1[seatNumber-1] == 0) {
                            row1[seatNumber-1] = 1;
                            purchaseTicket = true;
                        } else {
                            System.out.println("Seat already occupied!");
                        }
                        break;
                    } else {
                        System.out.println("Invalid seat number. Please select a seat number from 1-12");
                    }
                } else if(rowNumber == 2) { //2nd Row seats
                    if(seatNumber >= 1 && seatNumber <=16) {
                        ticketPrice = 20;
                        if(row2[seatNumber-1] == 0) {
                            row2[seatNumber-1] = 1;
                            purchaseTicket = true;
                        } else {
                            System.out.println("Seat already occupied!");
                        }
                        break;
                    } else {
                        System.out.println("Invalid seat number. Please select a seat number from 1-16");
                    }
                } else { //3rd Row seats
                    ticketPrice = 30;
                    if(seatNumber >= 1 && seatNumber <=20) {
                        if(row3[seatNumber-1] == 0) {
                            row3[seatNumber-1] = 1;
                            purchaseTicket = true;
                        } else {
                            System.out.println("Seat already occupied!");
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

        if(purchaseTicket == true) {
          System.out.println("Seat is available! Please enter the details to buy the ticket.\n");
          input.nextLine();
          while(true) {
              System.out.print("First Name: ");
              firstName = input.nextLine().toUpperCase();

              if(!firstName.matches("[a-zA-Z]*")) {
                System.out.println("Name should only contain letters.\n");
                continue;
              }
              break;
            }
          while(true) {
              System.out.print("Last Name: ");
              surname = input.nextLine().toUpperCase();

              if(!surname.matches("[a-zA-Z]*")) {
                System.out.println("Name should only contain letters.\n");
                continue;
              }
              break;
            }
          while(true) {
              System.out.print("Email: ");
              email = input.nextLine();
              if(!isValidEmailAddress(email)) {
                System.out.println("Invalid email address!\n");
                continue;
              }
              break;
            }
          
          System.out.println("\nTicket purchase successful!\nSeat number "+seatNumber+" in Row number "+rowNumber+" reserved!");
          Person person = new Person(firstName, surname, email);
          Ticket ticket = new Ticket(rowNumber, seatNumber, ticketPrice, person);
          ticketList.add(ticket);
        }
    }
    private static boolean isValidEmailAddress(String email) {
        //reference -> https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
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
    private static void cancel_ticket() {
        Scanner input = new Scanner(System.in);
        int rowNumber, seatNumber;
        boolean cancelledTicket = false;

        while(true) {
            System.out.println("Enter row number: ");
            rowNumber = input.nextInt();
            if(rowNumber<1 || rowNumber>3){
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
    private static void save() {
        //reference -> https://www.tutorialspoint.com/How-to-store-the-contents-of-arrays-in-a-file-using-Java
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
    private static void show_tickets_info() {
        double total = 0;
        for (Ticket ticketInfo : ticketList) {
            ticketInfo.print();
            total += ticketInfo.getPrice();
            System.out.println();
        }
        System.out.println("Total Price of all Tickets: £"+total);
    }
    }

