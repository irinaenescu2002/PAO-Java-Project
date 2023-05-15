import horses.Category;
import horses.Horse;
import people.Client;
import ridingCenters.Location;
import ridingCenters.RidingCenter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private List<RidingCenter> ridingCenters = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    public void printMenu(){
        System.out.println("MENU");
        System.out.println("0. Exit");
        System.out.println("1. Add a new location");
        System.out.println("2. Show all locations");
        System.out.println("3. Add a new riding center");
        System.out.println("4. Show details about all riding centers");
        System.out.println("5. Add a new client");
        System.out.println("6. Add a new horse to a riding center");
        System.out.println("7. Add a new employee to a riding center");
        System.out.println("8. Add a new arena to a riding center");
        System.out.println("9. Show details about all clients");
        System.out.println();
    }

    public void addLocation(Location _location){

        locations.add(_location);
    }

    public void showLocation(){
        int i = 0;
        for (Location location : locations){
            i = i + 1;
            System.out.println(i + ". " + location);
        }
    }

    public Boolean checkLocation(Location _location){
        for (Location location : locations){
            if (location.equals(_location))
                return Boolean.TRUE;
        }
        return  Boolean.FALSE;
    }

    public void addRidingCenter(RidingCenter _ridingCenter){

        ridingCenters.add(_ridingCenter);
    }

    public void showRidingCenters(){
        int i = 0;
        for (RidingCenter ridingCenter : ridingCenters){
            i = i + 1;
            System.out.println(i + ". " + ridingCenter);
        }
    }

    public RidingCenter getRidingCenterById(int myId){
        for (RidingCenter ridingCenter : ridingCenters){
            if (ridingCenter.getId() == myId)
                return ridingCenter;
        }
        return null;
    }

    public void addHorseToRidingCenter(Horse _horse, int ridingCenterId) {
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterId);
        List <Horse> horses = ridingCenter.getHorses();
        horses.add(_horse);
        horses = horses.stream().sorted(Comparator.comparing(Horse::getName)).collect(Collectors.toList());
        ridingCenter.setHorses(horses);
    }

    public void addClient(Client client) {
        clients.add(client);
        clients = clients.stream().sorted(Comparator.comparing(Client::getLastName).thenComparing(Client::getFirstName)).collect(Collectors.toList());
    }

    public void showClients() {
        int i = 0;
        for (Client client : clients){
            i = i + 1;
            System.out.println(i + ". " + client);
        }
    }
}
