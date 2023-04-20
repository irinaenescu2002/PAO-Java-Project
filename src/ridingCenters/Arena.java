package ridingCenters;

public class Arena {
    private int number;
    private int surface;

    public Arena(int number, int surface) {
        this.number = number;
        this.surface = surface;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    @Override
    public String toString() {
        return "Manege{" +
                "number=" + number +
                ", surface=" + surface +
                '}';
    }
}
