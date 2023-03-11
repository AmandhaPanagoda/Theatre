public class Person {
    private String firstName,surname,email;

    /**
     * Constructs a new Person object with the specified first name, surname, and email.
     * @param firstName the first name of the person
     * @param surname the surname of the person
     * @param email the email of the person
     */
    public Person(String firstName,String surname,String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public String getSurname() {
        return this.surname;
    }
    public String getEmail() {
        return this.email;
    }
}
