import horses.Category;
import horses.Horse;
import people.Caretaker;
import people.Client;
import people.Trainer;
import ridingCenters.Location;
import ridingCenters.RidingCenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                case "5" -> {
                    System.out.println("Please enter details about the new client!");
                    System.out.println("Last Name: ");
                    String lastName = scanner.next();
                    System.out.println("First Name: ");
                    String firstName = scanner.next();
                    System.out.println("Phone: ");
                    String phone = scanner.next();
                    System.out.println("Email: ");
                    String email = scanner.next();

                    service.addClient(new Client(lastName, firstName, phone, email));
                    System.out.println("Client added successfully!");
                    System.out.println();
                    break;
                }
                case "6" -> {
                    System.out.println("Enter the ID of the riding center to which you want to add this horse.");
                    System.out.println("Riding Center ID: ");
                    String ridingCenterId = scanner.next();
                    RidingCenter ridingCenter = service.getRidingCenterById(Integer.parseInt(ridingCenterId));
                    if (ridingCenter == null){
                        System.out.println("There is no riding center with this ID!\n");
                        break;
                    }
                    else {
                        System.out.println("Please enter details about the new horse!");
                        System.out.println("Name: ");
                        String name = scanner.next();
                        System.out.println("Breed: ");
                        String breed = scanner.next();
                        System.out.println("Color: ");
                        String color = scanner.next();
                        System.out.println("Stable: ");
                        String stable = scanner.next();
                        System.out.println("BirthDate: ");
                        String birthDate = scanner.next();
                        System.out.println("Please enter the number of the category!\n 1.DRESSAGE \n 2.SHOWJUMPING \n 3.RIDING");
                        System.out.println("Category: ");
                        String categoryId = scanner.next();
                        Category category = null;
                        switch (categoryId){
                            case "1" -> {
                                category = Category.DRESSAGE;
                            }
                            case "2" -> {
                                category = Category.SHOWJUMPING;
                            }
                            case "3" -> {
                                category = Category.RIDING;
                            }
                        }

                        service.addHorseToRidingCenter(new Horse(name, breed, color, category, birthDate, Integer.parseInt(stable)), Integer.parseInt(ridingCenterId));
                        System.out.println("\nHorse added successfully!");
                        System.out.println();
                        break;

                    }
                }
                case "7" -> {
                    System.out.println("Enter the ID of the riding center to which you want to add this horse.");
                    System.out.println("Riding Center ID: ");
                    String ridingCenterId = scanner.next();
                    RidingCenter ridingCenter = service.getRidingCenterById(Integer.parseInt(ridingCenterId));
                    if (ridingCenter == null){
                        System.out.println("There is no riding center with this ID!\n");
                        break;
                    }
                    else {
                        System.out.println("Please enter details about the new employee!");
                        System.out.println("Last Name: ");
                        String lastName = scanner.next();
                        System.out.println("First Name: ");
                        String firstName = scanner.next();
                        System.out.println("BirthDate: ");
                        String birthDate = scanner.next();
                        System.out.println("Phone: ");
                        String phone = scanner.next();
                        System.out.println("Email: ");
                        String email = scanner.next();
                        System.out.println("Hire Date: ");
                        String hireDate = scanner.next();
                        System.out.println("Salary: ");
                        String salary = scanner.next();
                        System.out.println("Office Number: ");
                        String officeNumber = scanner.next();
                        System.out.println("Work Days: ");
                        String workDays = scanner.next();
                        System.out.println("Please enter the number of the employee type!\n 1.TRAINER\n 2.CARETAKER");
                        System.out.println("Employee Type: ");
                        String type = scanner.next();
                        switch (type){
                            case "1" -> {
                                System.out.println("Skills: ");
                                String skills = scanner.next();
                                System.out.println("Qualification: ");
                                String qualification = scanner.next();

                                Trainer newEmployee = new Trainer(firstName, lastName, birthDate, phone,
                                        email, hireDate, Integer.parseInt(salary), Integer.parseInt(officeNumber)
                                        , Arrays.stream(workDays.split(",")).toList(), Arrays.stream(skills.split(",")).toList(), qualification);

                                service.addEmployeeToRidingCenter(newEmployee, Integer.parseInt(ridingCenterId));
                            }
                            case "2" -> {
                                System.out.println("Stables: ");
                                String stables = scanner.next();
                                String [] splitStables = stables.split(",");
                                System.out.println(splitStables);
                                List<Integer> goodStables = new ArrayList<>();
                                for (String stable : splitStables){
                                    goodStables.add(Integer.parseInt(stable));
                                }
                                System.out.println(goodStables);

                                Caretaker newEmployee = new Caretaker(firstName, lastName, birthDate, phone,
                                        email, hireDate, Integer.parseInt(salary), Integer.parseInt(officeNumber)
                                        , Arrays.stream(workDays.split(",")).toList(), goodStables);

                                service.addEmployeeToRidingCenter(newEmployee, Integer.parseInt(ridingCenterId));
                            }
                        }
                    }
                }
                case "9" -> {
                    System.out.println("These are all the clients of our riding centers!");
                    service.showClients();
                    System.out.println();
                    break;
                }
            }
        } while (!command.equals("0"));
    }
}