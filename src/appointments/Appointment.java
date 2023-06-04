package appointments;

import horses.Horse;
import people.Client;
import people.Employee;
import people.Trainer;
import ridingCenters.Arena;
import ridingCenters.RidingCenter;

public class Appointment {
    private RidingCenter ridingCenter;
    private Employee trainer;
    private Client client;
    private Horse horse;
    private String date;
    private String startHour;
    private String endHour;
    private int price;

    public Appointment(RidingCenter ridingCenter, Employee trainer, Client client, Horse horse, String date, String startHour, String endHour, int price) {
        this.ridingCenter = ridingCenter;
        this.trainer = trainer;
        this.client = client;
        this.horse = horse;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public RidingCenter getRidingCenter() {
        return ridingCenter;
    }

    public void setRidingCenter(RidingCenter ridingCenter) {
        this.ridingCenter = ridingCenter;
    }

    public Employee getTrainer() {
        return trainer;
    }

    public void setTrainer(Employee trainer) {
        this.trainer = trainer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "RidingCenter: " + ridingCenter.getName() +
                ", Client: " + client.getFirstName() + " " + client.getLastName() +
                ", Trainer: " + trainer.getFirstName() + " " + trainer.getLastName() + " (" + trainer.getEmail() + ")" +
                ", Horse: " + horse.getName() + " (" + horse.getBirthDate() + ") " +
                ", Date: " + date +
                ", Start Hour: " + startHour +
                ", End Hour: " + endHour +
                '}';
    }
}
