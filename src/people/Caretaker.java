package people;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Caretaker{" + super.toString() + ", " +
                "Stables=" + stables +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Caretaker caretaker = (Caretaker) o;
        return Objects.equals(stables, caretaker.stables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stables);
    }
}
