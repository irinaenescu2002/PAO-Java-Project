package ridingCenters;

import horses.Horse;
import people.Employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RidingCenter {
    private static int idCounter = 0;
    private int id;
    private String name;
    private Location location;
    private int arenaNumber;
    private int stableNumber;
    private String startProgram;
    private String endProgram;
    private HashSet<Arena> arenas = new HashSet<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Horse> horses = new ArrayList<>();

    public RidingCenter(String name, Location location, int arenaNumber, int stableNumber, String startProgram, String endProgram) {
        this.id = ++idCounter;
        this.name = name;
        this.location = location;
        this.arenaNumber = arenaNumber;
        this.stableNumber = stableNumber;
        this.startProgram = startProgram;
        this.endProgram = endProgram;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public String getStartProgram() {
        return startProgram;
    }

    public void setStartProgram(String startProgram) {
        this.startProgram = startProgram;
    }

    public String getEndProgram() {
        return endProgram;
    }

    public void setEndProgram(String endProgram) {
        this.endProgram = endProgram;
    }

    public int getArenaNumber() {
        return arenaNumber;
    }

    public void setArenaNumber(int arenaNumber) {
        this.arenaNumber = arenaNumber;
    }

    public int getStableNumber() {
        return stableNumber;
    }

    public void setStableNumber(int stableNumber) {
        this.stableNumber = stableNumber;
    }

    public HashSet<Arena> getArenas() {
        return arenas;
    }

    public void setArenas(HashSet<Arena> arenas) {
        this.arenas = arenas;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }

    @Override
    public String toString() {

        return "Name: " + name + "\n" +
                "   Location (" + location + ")" + "\n"+
                "   Open: " + startProgram +
                ", Close: " + endProgram + "\n" +
                "   Horses: " + horses + "\n" +
                "   Employees: " + employees + "\n" +
                "   Arenas: " + arenas;
    }
}
