class Ticket {
    private int row, seat;
    private double price;
    private Person person;

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


    public void print() {
        System.out.println("First Name: " + person.getFirstName());
        System.out.println("Last Name: " + person.getSurname());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Row number: " + row);
        System.out.println("Seat number: " + seat);
        System.out.println("Ticket Price: £" + price);
    }
}



