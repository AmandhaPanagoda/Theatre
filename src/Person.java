public class Person {
    private String firstName,surname,email;

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
