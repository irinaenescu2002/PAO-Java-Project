package people;

import java.util.ArrayList;
import java.util.List;

public class Employee implements Person{
    private static int idCounter = 0;
    private int id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phone;
    private String email;
    private String hireDate;
    private int salary;
    private int officeNumber;
    private List<String> workDays = new ArrayList<>();

    public Employee(String firstName, String lastName, String birthDate, String phone, String email, String hireDate, Integer salary, Integer officeNumber, List<String> workDays) {
        this.id = ++idCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.hireDate = hireDate;
        this.salary = salary;
        this.officeNumber = officeNumber;
        this.workDays = workDays;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    public List<String> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(List<String> workDays) {
        this.workDays = workDays;
    }

    @Override
    public void contactDetails() {
        System.out.println("Phone: " + this.phone);
        System.out.println("Email: " + this.email);
        System.out.print("Office: " + this.officeNumber);
        System.out.println("Our employee can be found at the office on the following days: ");
        for (String day : workDays){
            System.out.println("- " + day);
        }
    }
}
