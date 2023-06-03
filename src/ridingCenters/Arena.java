package ridingCenters;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arena arena = (Arena) o;
        return number == arena.number && surface == arena.surface;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, surface);
    }

    @Override
    public String toString() {
        return "Manege{" +
                "number=" + number +
                ", surface=" + surface +
                '}';
    }
}
