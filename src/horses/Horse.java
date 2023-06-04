package horses;

import java.util.Objects;

public class Horse {

    private static int idCounter = 0;
    private int id;
    private String name;
    private String breed;
    private String sex;
    private String color;
    private Category category;
    private String birthDate;
    private int stable;

    public Horse(String name, String breed, String sex, String color, Category category, String birthDate, int stable) {
        this.id = ++idCounter;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.category = category;
        this.birthDate = birthDate;
        this.stable = stable;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getStable() {
        return stable;
    }

    public void setStable(int stable) {
        this.stable = stable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "{Name: " + name +
                ", Breed: " + breed +
                ", Sex: " + sex +
                ", Color: " + color +
                ", Category: " + category +
                ", BirthDate: " + birthDate +
                ", Stable: " + stable + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return id == horse.id && stable == horse.stable && Objects.equals(name, horse.name) && Objects.equals(breed, horse.breed) && Objects.equals(sex, horse.sex) && Objects.equals(color, horse.color) && category == horse.category && Objects.equals(birthDate, horse.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, sex, color, category, birthDate, stable);
    }
}
