package horses;

public enum Category {
    DRESSAGE("Dressage is a form of horse riding performed in exhibition and competition, as well as an art sometimes pursued solely for the sake of mastery. As an equestrian sport defined by the International Equestrian Federation, dressage is described as the highest expression of horse training where horse and rider are expected to perform from memory a series of predetermined movements."),
    SHOWJUMPING ("In showjumping, athletes ride a horse over a set of jumps in numerical order, scoring penalties (also known as faults) if they knock down any jumps or the horse refuses to go over a jump. The winner is generally the athlete with the lowest score in the fastest time. It is a test of a rider’s skill and a horse’s ability."),
    RIDING ("Horseback riding is the art of riding a horse and controlling the animal's movement and speed with maximum effectiveness and minimum efforts. To become a good rider most people start by obtaining a general knowledge of horses.");

    private String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
