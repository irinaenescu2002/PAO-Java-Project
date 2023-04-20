package appointments;

import horses.Horse;
import people.Client;
import people.Trainer;
import ridingCenters.Arena;
import ridingCenters.RidingCenter;

public class Appointment {
    private RidingCenter ridingCenter;
    private Trainer trainer;
    private Client client;
    private Horse horse;
    private Arena arena;
    private String startHour;
    private String endHour;

    public Appointment(RidingCenter ridingCenter, Trainer trainer, Client client, Horse horse, Arena arena, String startHour, String endHour) {
        this.ridingCenter = ridingCenter;
        this.trainer = trainer;
        this.client = client;
        this.horse = horse;
        this.arena = arena;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public RidingCenter getRidingCenter() {
        return ridingCenter;
    }

    public void setRidingCenter(RidingCenter ridingCenter) {
        this.ridingCenter = ridingCenter;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
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

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
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
}
