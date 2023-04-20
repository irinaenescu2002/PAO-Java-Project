import ridingCenters.Location;
import ridingCenters.RidingCenter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        Scanner scanner = new Scanner(System.in);

        String command;

        do {
            service.printMenu();
            System.out.println("Insert your command: ");
            command = scanner.next();
            System.out.println();

            switch (command) {
                case "1" -> {
                    System.out.println("Please enter details about the new location!");
                    System.out.println("Country: ");
                    String country = scanner.next();
                    System.out.println("City: ");
                    String city = scanner.next();
                    System.out.println("Street: ");
                    String street = scanner.next();
                    System.out.println("Number: ");
                    String number = scanner.next();
                    System.out.println("Postal Code: ");
                    String postalCode = scanner.next();

                    service.addLocation(new Location(country, city, street, number, postalCode));
                    System.out.println();
                    System.out.println("Location added successfully!");
                    System.out.println();
                    break;

                }
                case "2" -> {
                    System.out.println("These are all the locations where you can find our riding centers!");
                    service.showLocation();
                    System.out.println();
                    break;
                }
                case "3" -> {
                    System.out.println("Before introducing a new riding center make sure that there is a location for it!");
                    System.out.println();
                    System.out.println("Please enter details about the location!");
                    System.out.println("Country: ");
                    String country = scanner.next();
                    System.out.println("City: ");
                    String city = scanner.next();
                    System.out.println("Street: ");
                    String street = scanner.next();
                    System.out.println("Number: ");
                    String number = scanner.next();
                    System.out.println("Postal Code: ");
                    String postalCode = scanner.next();
                    System.out.println();
                    Location _location = new Location(country, city, street, number, postalCode);

                    if (!service.checkLocation(_location)){
                        System.out.println("This location is not in the database. Do you want to add it and continue?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        String decision;
                        decision = scanner.next();

                        if (decision.equals("1")){
                            service.addLocation(_location);
                            System.out.println();
                            System.out.println("Location added successfully!");
                            System.out.println();
                            System.out.println("Now you can enter details about the new riding center!");
                            System.out.println("Name: ");
                            String name = scanner.next();
                            System.out.println("Number of arenas: ");
                            String _arenaNumber = scanner.next();
                            int arenaNumber = Integer.parseInt(_arenaNumber);
                            System.out.println("Number of stables: ");
                            String _stableNumber = scanner.next();
                            int stableNumber = Integer.parseInt(_stableNumber);
                            System.out.println("Start Program: ");
                            String startProgram = scanner.next();
                            System.out.println("End program: ");
                            String endProgram = scanner.next();
                            System.out.println();

                            service.addRidingCenter(new RidingCenter(name, _location, arenaNumber, stableNumber, startProgram, endProgram));
                            System.out.println("Riding center added successfully!");
                            System.out.println();
                            break;

                        } else {
                            System.out.println("If you entered the wrong location, run the command to add a new riding center again!");
                            System.out.println();
                            break;
                        }
                    } else {
                        System.out.println("Now enter details about the new riding center!");
                        System.out.println("Name: ");
                        String name = scanner.next();
                        System.out.println("Number of arenas: ");
                        String _arenaNumber = scanner.next();
                        int arenaNumber = Integer.parseInt(_arenaNumber);
                        System.out.println("Number of stables: ");
                        String _stableNumber = scanner.next();
                        int stableNumber = Integer.parseInt(_stableNumber);
                        System.out.println("Start Program: ");
                        String startProgram = scanner.next();
                        System.out.println("End program: ");
                        String endProgram = scanner.next();

                        service.addRidingCenter(new RidingCenter(name, _location, arenaNumber, stableNumber, startProgram, endProgram));
                        System.out.println("Riding center added successfully!");
                        System.out.println();
                        break;
                    }
                }
                case "4" -> {
                    System.out.println("These are our riding centers!");
                    service.showRidingCenters();
                    System.out.println();
                    break;
                }
            }
        } while (!command.equals("0"));

    }

}