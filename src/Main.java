import appointments.Appointment;
import exceptions.InvalidContactDetails;
import exceptions.InvalidStable;
import horses.Category;
import horses.Horse;
import people.Caretaker;
import people.Client;
import people.Employee;
import people.Trainer;
import ridingCenters.Arena;
import ridingCenters.Location;
import ridingCenters.RidingCenter;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        Scanner scanner = new Scanner(System.in);
        service.addRecords();
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
                }
                case "2" -> {
                    System.out.println("These are all the locations where you can find our riding centers!");
                    service.showLocation();
                    System.out.println();
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


                        } else {
                            System.out.println("If you entered the wrong location, run the command to add a new riding center again!");
                            System.out.println();
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
                    }
                }
                case "4" -> {
                    System.out.println("These are our riding centers!\n");
                    service.showRidingCenters();
                    System.out.println();
                }
                case "5" -> {
                    System.out.println("Please enter details about the new client!");
                    System.out.println("Last Name: ");
                    String lastName = scanner.next();
                    System.out.println("First Name: ");
                    String firstName = scanner.next();
                    System.out.println("Birth Date: ");
                    String birthDate = scanner.next();
                    System.out.println("Phone: ");
                    String phone = scanner.next();
                    System.out.println("Email: ");
                    String email = scanner.next();

                    try {
                        service.checkContactDetails(phone, email);
                        service.addClient(new Client(lastName, firstName, birthDate, phone, email));
                        System.out.println("\nClient added successfully!");
                    } catch (InvalidContactDetails ex){
                        System.out.println(ex.getMessage());
                        System.out.println();
                    }
                    System.out.println();
                }
                case "6" -> {
                    System.out.println("Enter the ID of the riding center to which you want to add this horse.");
                    System.out.println("Riding Center ID: ");
                    String ridingCenterId = scanner.next();
                    RidingCenter ridingCenter = service.getRidingCenterById(Integer.parseInt(ridingCenterId));
                    if (ridingCenter == null){
                        System.out.println("There is no riding center with this ID!\n");
                    }
                    else {
                        try {
                            System.out.println("Please enter details about the new horse!");
                            System.out.println("Name: ");
                            String name = scanner.next();
                            System.out.println("Breed: ");
                            String breed = scanner.next();
                            System.out.println("Sex: ");
                            String sex = scanner.next();
                            System.out.println("Color: ");
                            String color = scanner.next();
                            System.out.println("Stable: ");
                            String stable = scanner.next();
                            service.checkExistentStable(Integer.parseInt(stable), ridingCenter);
                            service.checkFreeStable(Integer.parseInt(stable), ridingCenter);
                            System.out.println("BirthDate: ");
                            String birthDate = scanner.next();
                            System.out.println("Please enter the number of the category!\n 1.DRESSAGE \n 2.SHOWJUMPING \n 3.RIDING");
                            System.out.println("Category: ");
                            String categoryId = scanner.next();
                            Category category = null;
                            switch (categoryId) {
                                case "1" -> category = Category.DRESSAGE;
                                case "2" -> category = Category.SHOWJUMPING;
                                case "3" -> category = Category.RIDING;
                            }

                            service.addHorseToRidingCenter(new Horse(name, breed, sex, color, category, birthDate, Integer.parseInt(stable)), Integer.parseInt(ridingCenterId));
                            System.out.println("\nHorse added successfully!");
                            System.out.println();
                        } catch (InvalidStable ex){
                            System.out.println(ex.getMessage());
                            System.out.println();
                        }
                    }
                }
                case "7" -> {
                    System.out.println("Enter the ID of the riding center to which you want to add this employee.");
                    System.out.println("Riding Center ID: ");
                    String ridingCenterId = scanner.next();
                    RidingCenter ridingCenter = service.getRidingCenterById(Integer.parseInt(ridingCenterId));
                    if (ridingCenter == null){
                        System.out.println("There is no riding center with this ID!\n");
                    }
                    else {
                        try {
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
                            service.checkContactDetails(phone, email);
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
                                    String[] splitStables = stables.split(",");
                                    System.out.println(Arrays.toString(splitStables));
                                    List<Integer> goodStables = new ArrayList<>();
                                    for (String stable : splitStables) {
                                        goodStables.add(Integer.parseInt(stable));
                                    }
                                    System.out.println(goodStables);

                                    Caretaker newEmployee = new Caretaker(firstName, lastName, birthDate, phone,
                                            email, hireDate, Integer.parseInt(salary), Integer.parseInt(officeNumber)
                                            , Arrays.stream(workDays.split(",")).toList(), goodStables);

                                    service.addEmployeeToRidingCenter(newEmployee, Integer.parseInt(ridingCenterId));
                                }
                            }
                        } catch (InvalidContactDetails ex){
                            System.out.println(ex.getMessage());
                            System.out.println();
                        }

                        System.out.println("\nEmployee added successfully!\n");
                    }
                }
                case "8" -> {
                    System.out.println("Enter the ID of the riding center to which you want to add this arena.");
                    System.out.println("Riding Center ID: ");
                    String ridingCenterId = scanner.next();
                    RidingCenter ridingCenter = service.getRidingCenterById(Integer.parseInt(ridingCenterId));
                    if (ridingCenter == null){
                        System.out.println("There is no riding center with this ID!\n");
                    }
                    else {
                        System.out.println("Please enter details about the new arena!");
                        System.out.println("Number: ");
                        String number = scanner.next();
                        System.out.println("Surface: ");
                        String surface = scanner.next();
                        service.addArenaToRidingCenter(new Arena(Integer.parseInt(number), Integer.parseInt(surface)), Integer.parseInt(ridingCenterId));

                        System.out.println();
                        System.out.println("Arena added successfully!");
                        System.out.println();
                    }
                }
                case "9" -> {
                    System.out.println("These are all the clients of our riding centers!");
                    service.showClients();
                    System.out.println();
                }
                case "10" -> {
                    try {
                        System.out.println("Please enter details about the appointment! ");
                        System.out.println("Client First Name: ");
                        String clientFirstName = scanner.next();
                        System.out.println("Client Last Name: ");
                        String clientLastName = scanner.next();
                        Client client = service.getClientByName(clientFirstName, clientLastName);
                        if (client == null){
                            String clientID = scanner.next();
                            client = service.getClientByID(Integer.parseInt(clientID));
                        }
                        System.out.println("Riding Center ID: ");
                        String ridingCenterID = scanner.next();
                        RidingCenter ridingCenter = service.getRidingCenterById(Integer.parseInt(ridingCenterID));
                        System.out.println("Trainer First Name: ");
                        String trainerFirstName = scanner.next();
                        System.out.println("Trainer Last Name: ");
                        String trainerLastName = scanner.next();
                        Employee trainer = service.getTrainerByName(trainerFirstName, trainerLastName, Integer.parseInt(ridingCenterID));
                        if (trainer == null) {
                            String trainerID = scanner.next();
                            trainer = service.getTrainerByID(Integer.parseInt(trainerID), Integer.parseInt(ridingCenterID));
                        }
                        System.out.println("Horse Name: ");
                        String horseName = scanner.next();
                        Horse horse = service.getHorseByName(horseName, Integer.parseInt(ridingCenterID));
                        if (horse == null) {
                            String horseId = scanner.next();
                            horse = service.getHorseById(Integer.parseInt(horseId), Integer.parseInt(ridingCenterID));
                        }
                        System.out.println("Date: ");
                        String date = scanner.next();
                        System.out.println("Start hour: ");
                        String start = scanner.next();
                        System.out.println("End Hour: ");
                        String end = scanner.next();
                        System.out.println("Price: ");
                        String price = scanner.next();
                        service.addAppointment(new Appointment(ridingCenter, trainer, client, horse, date, start, end, Integer.parseInt(price)));
                        System.out.println("\nAppointment added successfully!\n");
                    } catch (NoSuchElementException ex){
                        System.out.println("\nNo horse, trainer or client with that name at that riding center!\n");
                    }
                }
                case "11" -> {
                    System.out.println("These are all the appointments of our riding centers!\n");
                    service.showAppointments();
                    System.out.println();
                }
                case "12" -> {
                    try {
                        System.out.println("Please enter the client name!");
                        System.out.println("First Name: ");
                        String clientFirstName = scanner.next();
                        System.out.println("Last Name: ");
                        String clientLastName = scanner.next();
                        Client client = service.getClientByName(clientFirstName, clientLastName);
                        if (client == null){
                            String clientID = scanner.next();
                            client = service.getClientByID(Integer.parseInt(clientID));
                        }
                        System.out.println("What do you wanna change? \n 1. Phone number \n 2. Email \n Please enter your option:");
                        String option = scanner.next();
                        switch (option){
                            case "1" -> {
                                System.out.println("Phone number: ");
                                String phone = scanner.next();
                                service.changeNumberPhoneClient(client, phone);
                                System.out.println("\nPhone number changed successfully!\n");
                            }
                            case "2" -> {
                                System.out.println("Email: ");
                                String email = scanner.next();
                                service.changeEmailClient(client, email);
                                System.out.println("\nEmail changed successfully!\n");
                            }
                        }
                    } catch (NoSuchElementException ex){
                        System.out.println("\nNo client with that name!\n");
                    }
                }
                case "13" -> {
                    System.out.println("You wanna see the appointments of a: \n 1. Client \n 2. Horse \n 3. Trainer \n Please enter your option:");
                    String option = scanner.next();
                    switch (option){
                        case "1" -> {
                            try {
                                System.out.println("Please enter the client name!");
                                System.out.println("First Name: ");
                                String clientFirstName = scanner.next();
                                System.out.println("Last Name: ");
                                String clientLastName = scanner.next();
                                Client client = service.getClientByName(clientFirstName, clientLastName);
                                if (client == null) {
                                    String clientID = scanner.next();
                                    client = service.getClientByID(Integer.parseInt(clientID));
                                }
                                System.out.println("\n"  + clientFirstName + " " + clientLastName + "'s appointments:");
                                service.getAppointmentsClient(client);
                            } catch (NoSuchElementException ex) {
                                System.out.println("\nNo client with that name!\n");
                            }
                        }
                        case "2" -> {
                            try {
                                System.out.println("Please enter the horse name and the riding center ID!");
                                System.out.println("Name: ");
                                String horseName = scanner.next();
                                System.out.println("Riding Center ID: ");
                                String ridingCenterID = scanner.next();
                                Horse horse = service.getHorseByName(horseName, Integer.parseInt(ridingCenterID));
                                if (horse == null) {
                                    String horseId = scanner.next();
                                    horse = service.getHorseById(Integer.parseInt(horseId), Integer.parseInt(ridingCenterID));
                                }
                                System.out.println("\n"  + horseName + "'s appointments:");
                                service.getAppointmentsHorse(horse);
                            } catch (NoSuchElementException ex) {
                                System.out.println("\nNo horse with that name at this riding center!\n");
                            }
                        }
                        case "3" -> {
                            try {
                                System.out.println("Please enter the employee name and the riding center ID!");
                                System.out.println("First Name: ");
                                String trainerFirstName = scanner.next();
                                System.out.println("Last Name: ");
                                String trainerLastName = scanner.next();
                                System.out.println("Riding Center ID: ");
                                String ridingCenterID = scanner.next();
                                Employee trainer = service.getTrainerByName(trainerFirstName, trainerLastName, Integer.parseInt(ridingCenterID));
                                if (trainer == null) {
                                    String trainerID = scanner.next();
                                    trainer = service.getTrainerByID(Integer.parseInt(trainerID), Integer.parseInt(ridingCenterID));
                                }
                                System.out.println("\n"  + trainerFirstName + " " + trainerLastName + "'s appointments:");
                                service.getAppointmentsTrainer(trainer);
                            } catch (NoSuchElementException ex) {
                                System.out.println("\nNo trainer with that name at this riding center!\n");
                            }
                        }
                    }
                }
                case "14" -> {
                    System.out.println("Our loyal clients are: ");
                    service.loyalClients();
                }
                case "15" -> {
                    System.out.println("Our most active employees are: ");
                    service.activeEmployees();
                }
                case "16" -> {
                    System.out.print("The total amount collected on appointments: ");
                    System.out.println(service.totalAmount() + " RON");
                    System.out.println();
                }
                case "17" -> {
                    System.out.print("The monthly budget necessary for riding centers to pay all employees: ");
                    System.out.println(service.monthlyBudget() + " RON");
                    System.out.println();
                }
                case "18" -> {
                    System.out.println("Please enter the number of the category!\n 1.DRESSAGE \n 2.SHOWJUMPING \n 3.RIDING");
                    System.out.println("Category: ");
                    String categoryId = scanner.next();
                    Category category = null;
                    switch (categoryId){
                        case "1" -> category = Category.DRESSAGE;
                        case "2" -> category = Category.SHOWJUMPING;
                        case "3" -> category = Category.RIDING;
                    }
                    System.out.println("\nThese are our horses from all riding centers trained for " + category + ": ");
                    service.getHorsesByCategory(category);
                }
                case "19" -> {
                    System.out.println("Color: ");
                    String color = scanner.next();
                    System.out.println("\n These are our " + color + " horses: ");
                    service.getHorsesByColor(color);
                }
                case "20" -> {
                    System.out.println("Please enter your option!\n 1.Employee \n 2.Client");
                    System.out.println("Option: ");
                    String option = scanner.next();
                    switch (option) {
                        case "2" -> {
                            try {
                                System.out.println("Please enter the client name!");
                                System.out.println("First Name: ");
                                String clientFirstName = scanner.next();
                                System.out.println("Last Name: ");
                                String clientLastName = scanner.next();
                                Client client = service.getClientByName(clientFirstName, clientLastName);
                                if (client == null) {
                                    String clientID = scanner.next();
                                    client = service.getClientByID(Integer.parseInt(clientID));
                                }
                                service.showClientContactDetails(client);
                            } catch (NoSuchElementException ex) {
                                System.out.println("\nNo client with that name!\n");
                            }
                            System.out.println();
                        }
                        case "1" -> {
                            try {
                                System.out.println("Please enter the employee name and the riding center ID!");
                                System.out.println("Riding Center ID: ");
                                String ridingCenterID = scanner.next();
                                System.out.println("First Name: ");
                                String trainerFirstName = scanner.next();
                                System.out.println("Last Name: ");
                                String trainerLastName = scanner.next();
                                Employee employee = service.getEmployeeByName(trainerFirstName, trainerLastName, Integer.parseInt(ridingCenterID));
                                if (employee == null) {
                                    String employeeID = scanner.next();
                                    employee = service.getEmployeeById(Integer.parseInt(employeeID), Integer.parseInt(ridingCenterID));
                                }
                                service.showEmployeeContactDetails(employee);
                            } catch (NoSuchElementException ex) {
                                System.out.println("\nNo employee with that name at this riding center!\n");
                            }
                        }
                    }
                }
                case "21" -> {
                    System.out.println("Please enter your option!\n 1.Employee \n 2.Client");
                    System.out.println("Option: ");
                    String option = scanner.next();
                    switch (option) {
                        case "2" -> {
                            try {
                                System.out.println("Please enter the client name!");
                                System.out.println("First Name: ");
                                String clientFirstName = scanner.next();
                                System.out.println("Last Name: ");
                                String clientLastName = scanner.next();
                                Client client = service.getClientByName(clientFirstName, clientLastName);
                                if (client == null) {
                                    String clientID = scanner.next();
                                    client = service.getClientByID(Integer.parseInt(clientID));
                                }
                                service.showAgeClient(client);
                            } catch (NoSuchElementException ex) {
                                System.out.println("\nNo client with that name!\n");
                            }
                            System.out.println();
                        }
                        case "1" -> {
                            try {
                                System.out.println("Please enter the employee name and the riding center ID!");
                                System.out.println("Riding Center ID: ");
                                String ridingCenterID = scanner.next();
                                System.out.println("First Name: ");
                                String trainerFirstName = scanner.next();
                                System.out.println("Last Name: ");
                                String trainerLastName = scanner.next();
                                Employee employee = service.getEmployeeByName(trainerFirstName, trainerLastName, Integer.parseInt(ridingCenterID));
                                if (employee == null) {
                                    String employeeID = scanner.next();
                                    employee = service.getEmployeeById(Integer.parseInt(employeeID), Integer.parseInt(ridingCenterID));
                                }
                                service.showAgeEmployee(employee);
                            } catch (NoSuchElementException ex) {
                                System.out.println("\nNo employee with that name at this riding center!\n");
                            }
                            System.out.println();
                        }
                    }
                }

            }
        } while (!command.equals("0"));
    }
}