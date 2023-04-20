package people;

import java.util.List;

public class Trainer extends Employee {
    private List<String> skills;
    private String qualification;

    public Trainer(String firstName, String lastName, String birthDate, String phone, String email, String hireDate, Integer salary, Integer officeNumber, List<String> workDays, List<String> skills, String qualification) {
        super(firstName, lastName, birthDate, phone, email, hireDate, salary, officeNumber, workDays);
        this.skills = skills;
        this.qualification = qualification;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
