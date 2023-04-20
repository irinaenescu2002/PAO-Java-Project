package people;

import java.util.ArrayList;
import java.util.List;

public class Caretaker extends Employee{
    private List<Integer> stables = new ArrayList<>();

    public Caretaker(String firstName, String lastName, String birthDate, String phone, String email, String hireDate, Integer salary, Integer officeNumber, List<String> workDays, List<Integer> stables) {
        super(firstName, lastName, birthDate, phone, email, hireDate, salary, officeNumber, workDays);
        this.stables = stables;
    }

    public List<Integer> getStables() {
        return stables;
    }

    public void setStables(List<Integer> stables) {
        this.stables = stables;
    }
}
