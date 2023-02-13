import java.util.*;

public class Person {
    private String firstName,surname,email;

    public Person() {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.print("Enter your first name: ");
            this.firstName = input.nextLine().toUpperCase();

            System.out.print("Enter your surname: ");
            this.surname = input.nextLine().toUpperCase();

            //reference on using RegEx & matches() -> https://www.javatpoint.com/java-regex
            if(!surname.matches( "[a-zA-Z]*" ) || !firstName.matches("[a-zA-Z]*")){
                System.out.println("Name should only contain letters.\nEnter your first name and surname in the relevant field.\n");
            } else {
                break;
            }
        }

        while(true) {
            System.out.print("Enter your email: ");
            this.email = input.nextLine();
            if(!isValidEmailAddress(email)){
                System.out.println("Invalid email address!\n");
            } else {
                break;
            }
        }
    }

    //reference -> https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
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
