package horses;

public class Horse {

    private static int idCounter = 0;
    private int id;
    private String name;
    private String breed;
    private String color;
    private Category category;
    private String birthDate;
    private int stable;

    public Horse(String name, String breed, String color, Category category, String birthDate, int stable) {
        this.id = ++idCounter;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.category = category;
        this.birthDate = birthDate;
        this.stable = stable;
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
}
