class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    /**
     * Creates a new Ticket object with the specified row number, seat number, price, and person information.
     * @param row the row number of the seat
     * @param seat the seat number in the row
     * @param price the price of the ticket
     * @param person the person information associated with the ticket
     */
    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public int getRow() {
        return this.row;
    }
    public int getSeat() {
        return this.seat;
    }
    public double getPrice() { return this.price; }

    /**
     * Prints the information of a ticket, including the first name, last name, email, row number, seat number, and ticket price.
     */
    public void print() {
        System.out.println("First Name: " + person.getFirstName());
        System.out.println("Last Name: " + person.getSurname());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Row number: " + row);
        System.out.println("Seat number: " + seat);
        System.out.println("Ticket Price: £" + price);
    }
}



