package people;

public class Client implements Person {
    private static int idCounter = 0;
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public Client(String lastName, String firstName, String phone, String email) {
        this.id = ++idCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void contactDetails() {
        System.out.println("Phone: " + this.phone);
        System.out.println("Email: " + this.email);
    }

    @Override
    public String toString() {
        return "Last Name: " + lastName +
                ", First Name: " + firstName +
                ", Phone: " + phone +
                ", Email: " + email;
    }
}
