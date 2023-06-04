package people;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        System.out.println("Office: " + this.officeNumber);
        System.out.println("Our employee can be found at the office on the following days: ");
        for (String day : workDays){
            System.out.println("- " + day);
        }
    }

    @Override
    public void getAge(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthDate = LocalDate.parse(getBirthDate(), format);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        System.out.println();
        System.out.print(lastName + " " + firstName + " - " + period.getYears() + " years");
        LocalDate hireDate = LocalDate.parse(getHireDate(), format);
        Period period1 = Period.between(hireDate, currentDate);
        System.out.println(", our employee for " + period1.getYears() + " years");
    }

    @Override
    public String toString() {
        return "First Name: " + firstName +
                ", Last Name: " + lastName +
                ", Birthdate: " + birthDate +
                ", Phone: " + phone +
                ", Email: " + email +
                ", Hire Date: " + hireDate +
                ", Salary: " + salary + " ron" +
                ", Office Number: " + officeNumber +
                ", Work Days: " + workDays +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && salary == employee.salary && officeNumber == employee.officeNumber && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(birthDate, employee.birthDate) && Objects.equals(phone, employee.phone) && Objects.equals(email, employee.email) && Objects.equals(hireDate, employee.hireDate) && Objects.equals(workDays, employee.workDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, phone, email, hireDate, salary, officeNumber, workDays);
    }
}
