import java.util.*;

public class Theatre {
    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");

        int row1[] = new int[12];
        int row2[] = new int[16];
        int row3[] = new int[20];

        for(int i=0; i<=12; i++) {
            row1[i] = 0;
        }
        for(int i=0; i<=16; i++) {
            row2[i] = 0;
        }
        for(int i=0; i<=20; i++) {
            row3[i] = 0;
        }

        Scanner input = new Scanner(System.in);
        int menuOption;
        do {
            System.out.println("1) Buy a ticket\n\t2) Print seating area\n\t3) Cancel ticket\n\t4) List available seats\n\t5) Save to file\n\t6) Load from file\n\t7) Print ticket information and total price\n\t8) Sort tickets by price\n\t\t0) Quit");
            System.out.println("Enter option:");
            menuOption = input.nextInt();

            switch (menuOption) {
                case 1:
                    //buy_ticket method
                    buy_ticket(row1, row2, row3);
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                default:
                    System.out.println("Invalid Option");
            }
        } while (menuOption != 0);
        System.out.println("Option 0 entered. Exiting program...");
    }

    private static void buy_ticket(int row1[], int row2[], int row3[]) {
        //option number 1
        Scanner input = new Scanner(System.in);
        int rowNumber, seatNumber;
        System.out.println("Enter row number: ");
        rowNumber = input.nextInt();
        System.out.println("Enter seat number: ");
        seatNumber = input.nextInt();

        if(rowNumber == 1) {
            if(seatNumber >= 1 && seatNumber <=12) {
                if(row1[seatNumber-1] == 0) {
                    row1[seatNumber-1] = 1;
                    System.out.println("Ticket purchase successful!");
                } else {
                    System.out.println("Seat already occupied!");
                }
            } else {
                System.out.println("Invalid seat number. Please enter a seat number between 1-12");
            }
        } else if(rowNumber == 2) {
            if(seatNumber >= 1 && seatNumber <=16) {
                if(row2[seatNumber-1] == 0) {
                    row2[seatNumber-1] = 1;
                    System.out.println("Ticket purchase successful!");
                } else {
                    System.out.println("Seat already occupied!");
                }
            } else {
                System.out.println("Invalid seat number. Please enter a seat number between 1-16");
            }
        } else if(rowNumber == 3) {
            if(seatNumber >= 1 && seatNumber <=20) {
                if(row3[seatNumber-1] == 0) {
                    row3[seatNumber-1] = 1;
                    System.out.println("Ticket purchase successful!");
                } else {
                    System.out.println("Seat already occupied!");
                }
            } else {
                System.out.println("Invalid seat number. Please enter a seat number between 1-20");
            }
        } else {
            System.out.println("Invalid row number. Please enter a row number between 1-3");
        }
    }
}
