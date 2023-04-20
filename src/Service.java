import ridingCenters.Location;
import ridingCenters.RidingCenter;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private List<RidingCenter> ridingCenters = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();

    public void printMenu(){
        System.out.println("MENU");
        System.out.println("0. Exit");
        System.out.println("1. Add a new location");
        System.out.println("2. Show all locations");
        System.out.println("3. Add a new riding center");
        System.out.println("4. Show details about all riding centers");
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
}
