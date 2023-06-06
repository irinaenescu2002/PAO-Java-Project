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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Service {
    private static Service instance = null;
    private List<RidingCenter> ridingCenters = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private HashMap<Client, List<Appointment>> appointments = new HashMap<>();

    File audit = new File("audit.csv");
    PrintWriter writer = null;
    Connection conn = null;
    Statement statement;

    private Service(){
        try {
            writer = new PrintWriter(audit);
            writer.println("Timestap" + ", " + "Action");
            writer.flush();
        } catch (IOException ex){
            System.out.println("Can't create the audit file!");
        }

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/paojavaprojectschema", "root", "oracle");
            statement = conn.createStatement();

        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static synchronized Service getInstance() {
        if (instance == null)
            instance = new Service();
        return instance;
    }

    private void makeAudit(String action){
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        writer.print(timestamp + ", ");
        writer.println(action);
        writer.flush();
    }

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
        System.out.println("10. Add a new appointment");
        System.out.println("11. Show all appointments");
        System.out.println("12. Changing a client's contact details");
        System.out.println("13. Show the appointments of an employee, a horse or a client");
        System.out.println("14. Display of loyal clients");
        System.out.println("15. Displaying the most active employees");
        System.out.println("16. Calculation of the total amount collected on appointments");
        System.out.println("17. Calculation of the monthly budget necessary for riding centers to pay all employees");
        System.out.println("18. Display of all horses according to a certain category");
        System.out.println("19. Display of all horses according to a certain colour");
        System.out.println("20. Show the contact details of an employee or client");
        System.out.println("21. Show the age of an employee or client");
        System.out.println("22. Delete a client");
        System.out.println("23. Delete a riding center from a location");
        System.out.println("24. Increase an employee's salary by x RON");
        System.out.println("25. Increase the number of stables of a riding center by x");
        System.out.println("26. Move a horse from one horse to another");
        System.out.println();
    }

    public void addRecords(){

        addLocation(new Location("Romania", "Bucuresti", "Panselutelor", "34", "1879098"));
        addLocation(new Location("Romania", "Brasov", "Cositorilor", "32A", "4324564"));
        addLocation(new Location("Romania", "Brasov", "Negru Voda", "23B", "4378890"));
        addLocation(new Location("Romania", "Sibiu", "Crinilor", "20A", "6570098"));

        addRidingCenter(new RidingCenter("Spirit", locations.get(0), 4, 5, "10:00", "18:00"));
        addRidingCenter(new RidingCenter("FreeRide", locations.get(1), 6, 9, "12:00", "20:00"));
        addRidingCenter(new RidingCenter("BeFree", locations.get(2), 5, 8, "8:00", "16:00"));
        addRidingCenter(new RidingCenter("Horseland", locations.get(3), 8, 12, "8:00", "18:00"));

        addClient(new Client("Popescu", "Dan", "12.09.1988", "0786555456", "dan.ppsc@gmail.com"));
        addClient(new Client("Ionescu", "Maria", "12.10.1990", "0755436711", "maria.ionescu@yahoo.com"));
        addClient(new Client("Pirvu", "Carina", "10.12.2002", "0765112009", "carina.prv@gmail.com"));

        addHorseToRidingCenter(new Horse("Luna", "Lipitan", "F", "alb", Category.DRESSAGE, "24.04.2015", 5), ridingCenters.get(0).getId());
        addHorseToRidingCenter(new Horse("Nero", "Frizian", "M" , "negru", Category.DRESSAGE, "03.03.2019", 4), ridingCenters.get(0).getId());
        addHorseToRidingCenter(new Horse("Dakota", "Appaloosa", "F", "alb si maro", Category.RIDING, "19.06.2017", 3), ridingCenters.get(0).getId());
        addHorseToRidingCenter(new Horse("Iris", "Lipitan", "F", "alb", Category.DRESSAGE, "23.07.2015", 6), ridingCenters.get(1).getId());
        addHorseToRidingCenter(new Horse("Fred", "Hanoverian", "M", "maro", Category.SHOWJUMPING, "29.12.2014", 2), ridingCenters.get(1).getId());
        addHorseToRidingCenter(new Horse("Bob", "Lipitan", "M", "alb", Category.DRESSAGE, "02.04.2019", 1), ridingCenters.get(2).getId());
        addHorseToRidingCenter(new Horse("Marvin", "Gypsy Vanner", "M", "alb si negru", Category.RIDING, "06.10.2015", 7), ridingCenters.get(2).getId());
        addHorseToRidingCenter(new Horse("Marko", "Oldenburg", "M", "maro", Category.SHOWJUMPING, "24.04.2015", 9), ridingCenters.get(2).getId());

        addEmployeeToRidingCenter(new Caretaker("Marian", "Ionescu", "12.09.1988", "0789665456", "marian.ionescu@gmail.com", "13.08.2017", 2700, 1, Arrays.asList("Vineri", "Luni", "Miercuri"), Arrays.asList(3, 4, 2, 6)), ridingCenters.get(0).getId());
        addEmployeeToRidingCenter(new Trainer("Mirela", "Popescu", "18.09.1990", "0754338900", "pmirela@gmail.com", "23.10.2010", 3500, 2, Arrays.asList("Vineri", "Luni", "Miercuri"), Arrays.asList("patient", "good comunication"), "riding"), ridingCenters.get(0).getId());
        addEmployeeToRidingCenter(new Trainer("Ioana", "Duma", "23.08.1975", "0756443201", "dioana@gmail.com", "15.11.2020", 4000, 2, Arrays.asList("Vineri", "Luni", "Miercuri"), Arrays.asList("good colaboration", "severe"), "showjumping"), ridingCenters.get(0).getId());
        addEmployeeToRidingCenter(new Caretaker("Relu", "Pirvu", "25.10.1988", "0745334122", "pirvu.rl@gmail.com", "17.10.20016", 2650, 3, Arrays.asList("Marti", "Joi", "Miercuri"), Arrays.asList(3, 4, 2, 6)), ridingCenters.get(1).getId());
        addEmployeeToRidingCenter(new Trainer("Mircea", "Caragea", "21.04.1985", "0799880996", "caragea.mircea34@gmail.com", "21.08.2017", 3890, 1, Arrays.asList("Vineri", "Luni", "Miercuri"), Arrays.asList("patient", "good trainer"), "dressage"), ridingCenters.get(1).getId());
        addEmployeeToRidingCenter(new Caretaker("Laura", "Mihailescu", "12.08.1972", "0743556110", "lauramiha@gmail.com", "08.07.2013", 2880, 2, Arrays.asList("Vineri", "Luni", "Miercuri", "Marti"), Arrays.asList(3, 4, 2, 6)), ridingCenters.get(2).getId());
        addEmployeeToRidingCenter(new Trainer("Crina", "Aldea", "29.09.1970", "0754789341", "craldea@gmail.com", "29.08.2014", 5000, 1, Arrays.asList("Vineri", "Luni", "Miercuri"), Arrays.asList("patient", "caring"), "showjumping"), ridingCenters.get(2).getId());
        addEmployeeToRidingCenter(new Caretaker("Ionut", "Savu", "14.12.1988", "0708654100", "savuionnutz@gmail.com", "12.08.2017", 2400, 1, Arrays.asList("Vineri", "Luni", "Miercuri", "Joi"), Arrays.asList(3, 4, 2, 6)), ridingCenters.get(3).getId());

        addArenaToRidingCenter(new Arena(2, 45), ridingCenters.get(0).getId());
        addArenaToRidingCenter(new Arena(1, 80), ridingCenters.get(0).getId());
        addArenaToRidingCenter(new Arena(1, 54), ridingCenters.get(1).getId());
        addArenaToRidingCenter(new Arena(2, 76), ridingCenters.get(1).getId());
        addArenaToRidingCenter(new Arena(2, 83), ridingCenters.get(2).getId());
        addArenaToRidingCenter(new Arena(2, 90), ridingCenters.get(3).getId());
        addArenaToRidingCenter(new Arena(3, 45), ridingCenters.get(2).getId());
        addArenaToRidingCenter(new Arena(3, 50), ridingCenters.get(3).getId());

        addAppointment(new Appointment(getRidingCenterById(1), getTrainerByName("Ioana", "Duma", 1), getClientByName("Dan", "Popescu"), getHorseByName("Luna", 1), "23.10.2022", "12:00", "15:00", 300));
        addAppointment(new Appointment(getRidingCenterById(1), getTrainerByName("Mirela", "Popescu", 1), getClientByName("Dan", "Popescu"), getHorseByName("Dakota", 1), "27.10.2022", "13:00", "15:00", 200));
        addAppointment(new Appointment(getRidingCenterById(2), getTrainerByName("Mircea", "Caragea", 2), getClientByName("Carina", "Pirvu"), getHorseByName("Fred", 2), "28.11.2022", "12:00", "15:00", 300));
        addAppointment(new Appointment(getRidingCenterById(2), getTrainerByName("Mircea", "Caragea", 2), getClientByName("Carina", "Pirvu"), getHorseByName("Iris", 2), "29.11.2022", "15:00", "18:00", 300));
        addAppointment(new Appointment(getRidingCenterById(3), getTrainerByName("Crina", "Aldea", 3), getClientByName("Carina", "Pirvu"), getHorseByName("Bob", 3), "01.12.2022", "12:00", "14:00", 200));
        addAppointment(new Appointment(getRidingCenterById(3), getTrainerByName("Crina", "Aldea", 3), getClientByName("Maria", "Ionescu"), getHorseByName("Bob", 3), "25.10.2022", "12:00", "14:00", 200));

    }

    public void deleteRecords(){
        String queryEmployees = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID > 0";
        String queryHorses = "DELETE FROM HORSES WHERE HORSE_ID > 0";
        String queryRidingCenters = "DELETE FROM RIDING_CENTERS WHERE RIDING_CENTER_ID > 0";
        String queryLocation = "DELETE FROM LOCATIONS WHERE LOCATION_ID > 0";
        String queryClient = "DELETE FROM CLIENTS WHERE CLIENT_ID > 0";

        try{
            statement.execute(queryHorses);
            statement.execute(queryEmployees);
            statement.execute(queryRidingCenters);
            statement.execute(queryLocation);
            statement.execute(queryClient);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addLocation(Location _location){
        locations.add(_location);
        String action = "Added a new location in " + _location.getCountry() + " - " + _location.getCity();
        makeAudit(action);

        String query = "INSERT INTO LOCATIONS VALUES(" + (0 + ", '" + _location.getCountry() + "', '" + _location.getCity()
                + "', '" + _location.getStreet() + "', '" + _location.getNumber() + "', '"
                + _location.getPostalCode() + "')");
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void showLocation(){
        int i = 0;
        for (Location location : locations){
            i = i + 1;
            System.out.println(i + ". " + location);
        }
        String action = "Show all locations";
        makeAudit(action);

        String query = "SELECT COUNTRY, CITY, STREET, NUMBER, POSTAL_CODE FROM LOCATIONS";
        try{
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("\nFrom Database: ");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2) + " ("
                        + resultSet.getString(3) + " " + resultSet.getString(4) + " - "
                        + resultSet.getString(5) + ")");
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
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
        String action = "Added a new riding center called " + _ridingCenter.getName();
        makeAudit(action);

        Location location = _ridingCenter.getLocation();
        int locationID = 0;

        String query = "SELECT LOCATION_ID FROM LOCATIONS WHERE COUNTRY='" + location.getCountry() + "' AND CITY='" + location.getCity() + "' AND STREET='"
                        + location.getStreet() + "' AND NUMBER='" + location.getNumber() + "' AND POSTAL_CODE='" + location.getPostalCode() + "'";
        try{
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                locationID = resultSet.getInt(1);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        query = "INSERT INTO RIDING_CENTERS VALUES(" + (_ridingCenter.getId() + ", '" + _ridingCenter.getName() + "', '" + _ridingCenter.getStableNumber()
                + "', '" + _ridingCenter.getStartProgram() + "', '" + _ridingCenter.getEndProgram() + "', '" + locationID + "')");
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void showRidingCenters(){
        int i = 0;
        for (RidingCenter ridingCenter : ridingCenters){
            i = i + 1;
            System.out.println(i + ". " + ridingCenter);
            System.out.println();
        }
        String action = "Show all riding centers";
        makeAudit(action);

        String query = "SELECT NAME, START_PROGRAM, END_PROGRAM, COUNTRY, CITY " +
                "FROM RIDING_CENTERS " +
                "JOIN LOCATIONS ON RIDING_CENTERS.LOCATION_ID = LOCATIONS.LOCATION_ID";

        try{
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("\nFrom Database: ");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1) + " (" + resultSet.getString(2) + " - "
                        + resultSet.getString(3) + ") -> " + resultSet.getString(4) + ", "
                        + resultSet.getString(5));
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
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
        String action = "Added a new horse (" + _horse.getName() +  ") in " + ridingCenter.getName() + " Riding Center " ;
        makeAudit(action);

        String query = "INSERT INTO HORSES VALUES(" + (_horse.getId() + ", '" + _horse.getName() + "', '" + _horse.getBreed()
                + "', '" + _horse.getSex() + "', '" + _horse.getColor() + "', '" + _horse.getCategory().getName() +
                "', '" + ridingCenterId + "', '" + _horse.getStable() + "')");
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addArenaToRidingCenter(Arena _arena, int ridingCenterId){
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterId);
        HashSet <Arena> arenas = ridingCenter.getArenas();
        arenas.add(_arena);
        ridingCenter.setArenas(arenas);
        String action = "Added a new arena in " + ridingCenter.getName() + " Riding Center";
        makeAudit(action);
    }

    public void addClient(Client client) {
        clients.add(client);
        clients = clients.stream().sorted(Comparator.comparing(Client::getLastName).thenComparing(Client::getFirstName)).collect(Collectors.toList());
        appointments.put(client, new ArrayList<>());
        String action = "Added a new client (" + client.getFirstName() + " " + client.getLastName() + ")";
        makeAudit(action);

        String query = "INSERT INTO CLIENTS VALUES(" + (client.getId() + ", '" + client.getFirstName() + "', '" + client.getLastName()
                        + "', '" + client.getEmail() + "', '" + client.getPhone() + "', '" + client.getBirthDate() + "')");
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void showClients() {
        int i = 0;
        for (Client client : clients){
            i = i + 1;
            System.out.println(i + ". " + client);
        }
        String action = "Show all clients";
        makeAudit(action);

        String query = "SELECT LAST_NAME, FIRST_NAME, EMAIL, PHONE FROM CLIENTS";
        try{
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("\nFrom Database: ");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " ("
                        + resultSet.getString(3) + ", " + resultSet.getString(4) + ")");
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addEmployeeToRidingCenter(Employee newEmployee, int ridingCenterId) {
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterId);
        List <Employee> employees = ridingCenter.getEmployees();
        employees.add(newEmployee);
        employees = employees.stream().sorted(Comparator.comparing(Employee::getLastName).thenComparing(Employee::getFirstName)).collect(Collectors.toList());
        ridingCenter.setEmployees(employees);
        String action = "Added a new employee (" + newEmployee.getFirstName() + " " + newEmployee.getLastName() + ") at " + ridingCenter.getName() + " Riding Center";
        makeAudit(action);

        String type = null;
        if (newEmployee instanceof Trainer){
            type = "trainer";
        }
        else {
            type = "caretaker";
        }

        String query = "INSERT INTO EMPLOYEES VALUES(" + (newEmployee.getId() + ", '" + newEmployee.getLastName() + "', '" + newEmployee.getFirstName()
                + "', '" + newEmployee.getEmail() + "', '" + newEmployee.getPhone() + "', '" + type + "', '" + newEmployee.getSalary() +
                "', '" + newEmployee.getHireDate() + "', '" + ridingCenterId + "')");
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    public void showAppointments(){
        Integer i = 1;
        for (Map.Entry<Client, List<Appointment>> entry : appointments.entrySet()){
            Client client = entry.getKey();
            List<Appointment> appointmentsList = entry.getValue();
            System.out.println(i + ". " + client);
            i ++;
            if (appointmentsList.isEmpty()){
                System.out.println("No appointments for this client!\n");
            }
            else{
                 int j = 1;
                 for (Appointment appointment : appointmentsList) {
                     System.out.println(j + ". " + appointment);
                     j ++;
                 }
            }
        }
        String action = "Show all appointments";
        makeAudit(action);
    }

    public Client getClientByName(String first, String last) throws NoSuchElementException{
        List<Client> foundClient = new ArrayList<>();
        for (Client client : clients){
            if (client.getFirstName().equals(first) && client.getLastName().equals(last))
                foundClient.add(client);
        }
        if (foundClient.size() == 0)
            throw new NoSuchElementException();
        if (foundClient.size() == 1){
            return foundClient.get(0);
        }
        else {
            System.out.println("More clients with this name!");
            Integer i = 1;
            for (Client client : foundClient) {
                System.out.println(i + ". " + client.getFirstName() + " " + client.getLastName() + " " + client.getEmail() + " - ID: " + client.getId());
                i ++;
            }
            System.out.println("Please enter the ID!");
            return null;
        }
    }

    public Client getClientByID(int id){
        Client clientFound = null;
        for (Client client : clients){
            if (client.getId() == id)
                clientFound = client;
        }
        return clientFound;
    }

    public Employee getTrainerByName(String first, String last, int ridingCenterId) throws NoSuchElementException{
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterId);
        List<Employee> foundTrainer = new ArrayList<>();
        for (Employee emp : ridingCenter.getEmployees()){
            if (emp.getFirstName().equals(first) && emp.getLastName().equals(last) && emp instanceof Trainer)
                foundTrainer.add(emp);
        }
        if (foundTrainer.size() == 0)
            throw new NoSuchElementException();
        if (foundTrainer.size() == 1){
            return foundTrainer.get(0);
        }
        else {
            System.out.println("More trainers with this name!");
            Integer i = 1;
            for (Employee trainer : foundTrainer)
                System.out.println(i + ". " + trainer.getFirstName() + " " + trainer.getLastName() + " " + trainer.getEmail() + " - ID: " + trainer.getId());
            System.out.println("Please enter the ID!");
            return null;
        }
    }

    public void addAppointment(Appointment appointment){
        Client client = appointment.getClient();
        List <Appointment> currentApp = appointments.get(client);
        currentApp.add(appointment);
        appointments.put(client, currentApp);
        String action = "Added a new appointment for a client (" + client.getFirstName() + " " + client.getLastName() + ") on " + appointment.getDate();
        makeAudit(action);
    }

    public Employee getTrainerByID(int id, int ridingCenterID) {
        Employee trainerFound = null;
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterID);
        for (Employee emp : ridingCenter.getEmployees()){
            if (emp.getId() == id && emp instanceof Trainer)
                trainerFound = emp;
        }
        return trainerFound;
    }

    public Horse getHorseByName(String horseName, int ridingCenterId) throws NoSuchElementException{
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterId);
        List<Horse> foundHorse = new ArrayList<>();
        for (Horse horse : ridingCenter.getHorses()){
            if (horse.getName().equals(horseName))
                foundHorse.add(horse);
        }
        if (foundHorse.size() == 0){
            throw new NoSuchElementException();
        }
        if (foundHorse.size() == 1){
            return foundHorse.get(0);
        }
        else {
            System.out.println("More horses with this name!");
            Integer i = 1;
            for (Horse horse : foundHorse){
                System.out.println(i + ". " + horse.getName() + " " + horse.getBreed() + " " + horse.getBirthDate() + " - ID: " + horse.getId());
                i ++ ;
            }
            System.out.println("Please enter the ID!");
            return null;
        }
    }

    public Horse getHorseById(int horseId, int ridingCenterID) {
        Horse horseFound = null;
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterID);
        for (Horse horse : ridingCenter.getHorses()){
            if (horse.getId() == horseId)
                horseFound = horse;
        }
        return horseFound;
    }

    public void changeNumberPhoneClient(Client client, String phoneNumber){
        for (Client myClient : clients){
            if (myClient.equals(client)){
                myClient.setPhone(phoneNumber);
                break;
            }
        }
        String action = "Changing " + client.getFirstName() + " " + client.getLastName() + " phone number in " + phoneNumber;
        makeAudit(action);

        String query = "UPDATE CLIENTS SET PHONE = '" + phoneNumber + "' WHERE CLIENT_ID = " + client.getId();
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void changeEmailClient(Client client, String email) {
        for (Client myClient : clients){
            if (myClient.equals(client)){
                myClient.setEmail(email);
                break;
            }
        }
        String action = "Changing " + client.getFirstName() + " " + client.getLastName() + " email in " + email;
        makeAudit(action);

        String query = "UPDATE CLIENTS SET EMAIL = '" + email + "' WHERE CLIENT_ID = " + client.getId();
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }


    public void getAppointmentsClient(Client client) {
        List <Appointment> clientAppointments = appointments.get(client);
        int j = 1;
        for (Appointment appointment : clientAppointments) {
            System.out.println(j + ". " + appointment);
            j ++;
        }
        if (j == 1){
            System.out.println("\nNo appointments for this client!");
        }
        System.out.println();
        String action = "Show all appointments for  " + client.getFirstName() + " " + client.getLastName();
        makeAudit(action);
    }

    public void getAppointmentsHorse(Horse horse) {
        int i = 1;
        for (Map.Entry<Client, List<Appointment>> entry : appointments.entrySet()){
            List<Appointment> appointmentsList = entry.getValue();
            for (Appointment app : appointmentsList){
                if (app.getHorse().equals(horse)){
                    System.out.println(i + ". " + app);
                    i ++;
                }
            }
        }
        if (i == 1){
            System.out.println("\nNo appointments for this horse!");
        }
        System.out.println();
        String action = "Show all appointments for  " + horse.getName();
        makeAudit(action);
    }

    public void getAppointmentsTrainer(Employee trainer) {
        int i = 1;
        for (Map.Entry<Client, List<Appointment>> entry : appointments.entrySet()){
            List<Appointment> appointmentsList = entry.getValue();
            for (Appointment app : appointmentsList){
                if (app.getTrainer().equals(trainer)){
                    System.out.println(i + ". " + app);
                    i ++;
                }
            }
        }
        if (i == 1){
            System.out.println("\nNo appointments for this trainer!");
        }
        System.out.println();
        String action = "Show all appointments for  " + trainer.getFirstName() + " " + trainer.getLastName();
        makeAudit(action);
    }

    public void loyalClients() {
        HashMap <Integer, List<Client>> appointmentsClients = new HashMap<>();
        for (Map.Entry<Client, List<Appointment>> entry : appointments.entrySet()){
            Client client = entry.getKey();
            List<Appointment> appointmentsList = entry.getValue();
            int numberOfAppoimtments = appointmentsList.size();
            if (!appointmentsClients.containsKey(numberOfAppoimtments)){
                List<Client> myCLients = new ArrayList<>();
                myCLients.add(client);
                appointmentsClients.put(numberOfAppoimtments, myCLients);
            }
            else{
                List<Client> myClients = appointmentsClients.get(numberOfAppoimtments);
                clients.add(client);
                appointmentsClients.put(numberOfAppoimtments, myClients);
            }
        }

        int max = -1;
        for(Map.Entry<Integer, List<Client>> entry : appointmentsClients.entrySet()){
            if (entry.getKey() > max){
                max = entry.getKey();
            }
        }

        int i = 1;
        for (Client client : appointmentsClients.get(max)){
            System.out.println(i + ". " + client.getLastName() + " " + client.getFirstName() + " (" + client.getEmail() + ") - " + max + " appointmnets");
            i ++;
        }
        System.out.println();
        String action = "Show our loyal clients";
        makeAudit(action);
    }

    public void activeEmployees() {
        HashMap <Employee, Integer> activeEmpoyees = new HashMap<>();
        for (RidingCenter ridingCenter : ridingCenters){
            for (Employee employee : ridingCenter.getEmployees()){
                if (employee instanceof Trainer) {
                    activeEmpoyees.put(employee, 0);
                }
            }
        }
        for (Map.Entry<Client, List<Appointment>> entry : appointments.entrySet()) {
            Client client = entry.getKey();
            List<Appointment> appointmentsList = entry.getValue();
            for (Appointment appointment : appointmentsList){
                Employee trainer = appointment.getTrainer();
                int countAppointments = activeEmpoyees.get(trainer);
                countAppointments += 1;
                activeEmpoyees.put(trainer, countAppointments);
            }
        }

        HashMap <Integer, List<Employee>> finalActiveEmployees = new HashMap<>();
        for (Map.Entry <Employee, Integer> entry : activeEmpoyees.entrySet()){
            Employee employee = entry.getKey();
            Integer value = entry.getValue();
            if (!finalActiveEmployees.containsKey(value)){
                List <Employee> employeeList = new ArrayList<>();
                employeeList.add(employee);
                finalActiveEmployees.put(value, employeeList);
            }
            else {
                List <Employee> employeeList = finalActiveEmployees.get(value);
                employeeList.add(employee);
                finalActiveEmployees.put(value, employeeList);
            }
        }

        int max = -1;
        for(Map.Entry<Integer, List<Employee>> entry : finalActiveEmployees.entrySet()){
            if (entry.getKey() > max){
                max = entry.getKey();
            }
        }

        int i = 1;
        for (Employee employee : finalActiveEmployees.get(max)){
            System.out.println(i + ". " + employee.getLastName() + " " + employee.getFirstName() + " (" + employee.getEmail() + ") - " + max + " appointmnets");
            i ++;
        }
        System.out.println();
        String action = "Show our most active employees";
        makeAudit(action);
    }

    public int totalAmount() {
        int amount = 0;
        for (Map.Entry<Client, List<Appointment>> entry : appointments.entrySet()) {
            Client client = entry.getKey();
            List<Appointment> appointmentsList = entry.getValue();
            for (Appointment appointment : appointmentsList) {
                amount += appointment.getPrice();
            }
        }
        String action = "Show the total amount collected on appointments";
        makeAudit(action);
        return amount;
    }

    public int monthlyBudget() {
        int bugdet = 0;
        for (RidingCenter ridingCenter : ridingCenters){
            for (Employee employee : ridingCenter.getEmployees()){
                bugdet += employee.getSalary();
            }
        }
        String action = "Show the monthly budget necessary for riding center to pay all employees";
        makeAudit(action);
        return bugdet;
    }

    public void getHorsesByCategory(Category category) {
        List <Horse> horses = new ArrayList<>();
        for (RidingCenter ridingCenter : ridingCenters){
            for (Horse horse : ridingCenter.getHorses()){
                if (horse.getCategory() == category){
                    horses.add(horse);
                }
            }
        }
        if (horses.size() == 0) {
            System.out.println("No horses for that category!");
        }
        else {
            int i = 1;
            for (Horse horse : horses){
                System.out.println(i + ". " + horse.getName() + " (" + horse.getBreed() + ", " + horse.getBirthDate() + ")");
                i ++ ;
            }
        }
        System.out.println(category.getDescription());
        System.out.println();
        String action = "Show our horses trained for " + category;
        makeAudit(action);
    }

    public void getHorsesByColor(String color) {
        List <Horse> horses = new ArrayList<>();
        for (RidingCenter ridingCenter : ridingCenters){
            for (Horse horse : ridingCenter.getHorses()){
                if (horse.getColor().equals(color) || horse.getColor().contains(color)){
                    horses.add(horse);
                }
            }
        }
        if (horses.size() == 0) {
            System.out.println("No horses with that color!");
        }
        else {
            int i = 1;
            for (Horse horse : horses){
                System.out.println(i + ". " + horse.getName() + " (" + horse.getBreed() + ", " + horse.getBirthDate() + ") - " + horse.getColor());
                i ++ ;
            }
        }

        String action = "Show our " + color + " horses";
        makeAudit(action);

        String query = "SELECT NAME, BREED, COLOR FROM HORSES WHERE COLOR LIKE '%" + color + "%'";
        try{
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("\nFrom Database: ");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1) + " (" + resultSet.getString(2) + ") - " + resultSet.getString(3));
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println();
    }

    public void showClientContactDetails(Client client) {
        System.out.println("\n" + client.getLastName() + " " + client.getFirstName() + " can be contacted at: ");
        client.contactDetails();
        String action = "Show " + client.getFirstName() + " " + client.getLastName() + " contact details";
        makeAudit(action);
    }

    public Employee getEmployeeByName(String first, String last, int ridingCenterId) throws NoSuchElementException{
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterId);
        List<Employee> foundEmployee = new ArrayList<>();
        for (Employee emp : ridingCenter.getEmployees()){
            if (emp.getFirstName().equals(first) && emp.getLastName().equals(last))
                foundEmployee.add(emp);
        }
        if (foundEmployee.size() == 0)
            throw new NoSuchElementException();
        if (foundEmployee.size() == 1){
            return foundEmployee.get(0);
        }
        else {
            System.out.println("More employees with this name!");
            Integer i = 1;
            for (Employee employee : foundEmployee)
                System.out.println(i + ". " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getEmail() + " - ID: " + employee.getId());
            System.out.println("Please enter the ID!");
            return null;
        }
    }

    public Employee getEmployeeById(int id, int ridingCenterID) {
        Employee employeeFound = null;
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterID);
        for (Employee emp : ridingCenter.getEmployees()){
            if (emp.getId() == id)
                employeeFound = emp;
        }
        return employeeFound;
    }

    public void showEmployeeContactDetails(Employee employee) {
        System.out.println("\n" + employee.getLastName() + " " + employee.getFirstName() + " can be contacted at: ");
        employee.contactDetails();
        System.out.println();
        String action = "Show " + employee.getFirstName() + " " + employee.getLastName() + " contact details";
        makeAudit(action);

        String query = "SELECT EMAIL, PHONE FROM EMPLOYEES WHERE EMPLOYEE_ID =" + employee.getId();
        try{
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("\nFrom Database: ");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2));
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void showAgeClient(Client client) {
        client.getAge();
        String action = "Show " + client.getFirstName() + " " + client.getLastName() + " age";
        makeAudit(action);
    }

    public void showAgeEmployee(Employee employee) {
        employee.getAge();
        String action = "Show " + employee.getFirstName() + " " + employee.getLastName() + " age and years spent at our riding centers";
        makeAudit(action);
    }

    public void checkFreeStable(int stable, RidingCenter ridingCenter) throws InvalidStable{
        for (Horse horse : ridingCenter.getHorses()){
            if (horse.getStable() == stable){
               throw new InvalidStable("\nThis stable is occupied by " + horse.getName() + "!\n");
            }
        }
    }

    public void checkExistentStable(int stable, RidingCenter ridingCenter) throws InvalidStable {
        if (stable > ridingCenter.getStableNumber()){
            throw new InvalidStable("\nThis riding center has only " + ridingCenter.getStableNumber() + " stables! \n");
        }
    }

    public void checkContactDetails(String phone, String email) throws InvalidContactDetails {
        if (phone.length() != 10)
            throw new InvalidContactDetails("\nInvalid phone number!\n");
        if (!email.contains("@") || (!email.contains(".com") && !email.contains(".ro")))
            throw new InvalidContactDetails("\nInvalid email!\n");
    }

    public void deleteClient(Client client) {
        for (Client client1 : clients){
            if (client1.equals(client)){
                clients.remove(client);
                break;
            }
        }
        System.out.println("\nClient deleted successfully!\n");

        String action = "Client " + client.getFirstName() + " " + client.getLastName() + " deleted";
        makeAudit(action);

        String query = "DELETE FROM CLIENTS WHERE CLIENT_ID = " + client.getId();
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void deleteRidingCenter(Location location) {
        String name = null;
        for (RidingCenter ridingCenter : ridingCenters){
            if (ridingCenter.getLocation().equals(location)){
                name = ridingCenter.getName();
                ridingCenters.remove(ridingCenter);
                break;
            }
        }

        String action = "Riding center " + name + " deleted";
        makeAudit(action);

        int locationId = 0;
        String query = "SELECT LOCATION_ID FROM LOCATIONS WHERE COUNTRY='" + location.getCountry() + "' AND CITY='" + location.getCity() + "' AND STREET='"
                + location.getStreet() + "' AND NUMBER='" + location.getNumber() + "' AND POSTAL_CODE='" + location.getPostalCode() + "'";
        try{
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                locationId = resultSet.getInt(1);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        int ridingCenterId = 0;
        query = "SELECT RIDING_CENTER_ID " +
                "FROM RIDING_CENTERS " +
                "WHERE LOCATION_ID = '" + locationId + "'";
        try{
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                ridingCenterId = resultSet.getInt(1);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        query = "DELETE FROM HORSES WHERE RIDING_CENTER_ID = " + ridingCenterId;
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        query = "DELETE FROM EMPLOYEES WHERE RIDING_CENTER_ID = " + ridingCenterId;
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        query = "DELETE FROM RIDING_CENTERS WHERE LOCATION_ID = " + locationId;
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        query = "DELETE FROM LOCATIONS WHERE LOCATION_ID = " + locationId;
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void changeSalary(Employee employeeToChange, int x, int ridingCenterId) {
        double new_salary = 0;
        int employee_id = 0;
        for (RidingCenter ridingCenter : ridingCenters){
            if (ridingCenter.getId() == ridingCenterId){
                for (Employee employee : ridingCenter.getEmployees()){
                    if (employee.equals(employeeToChange)){
                        new_salary = employee.getSalary() + x;
                        employee_id = employee.getId();
                        break;
                    }
                }
            }
            break;
        }

        String action = employeeToChange.getFirstName() + " " + employeeToChange.getLastName() + "'s salary increased by " + x;
        makeAudit(action);

        String query = "UPDATE EMPLOYEES SET SALARY = " + new_salary+ " WHERE EMPLOYEE_ID = " + employee_id;
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("\nSalary changed!\n");
    }

    public void changeStableNumberRidingCenter(String ridingCenterID, int x) {
        int new_stable_number = 0;
        RidingCenter ridingCenterAction = null;
        for (RidingCenter ridingCenter: ridingCenters){
            if (ridingCenter.getId() == Integer.parseInt(ridingCenterID)){
                new_stable_number = ridingCenter.getStableNumber() + x;
                ridingCenterAction = ridingCenter;
                ridingCenter.setStableNumber(new_stable_number);
                break;
            }
        }

        String query = "UPDATE RIDING_CENTERS SET STABLE_NUMBER = " + new_stable_number + " WHERE RIDING_CENTER_ID = " + ridingCenterID;
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        String action = "Number of stables increased by " + x + " at " + ridingCenterAction.getName() + " Riding Center";
        makeAudit(action);

        System.out.println("\nNumber of stables increased successfully!\n");

    }

    public void moveHorse(String ridingCenterID, Horse horse, String newStable) throws InvalidStable {
        RidingCenter ridingCenterAction = null;
        for (RidingCenter ridingCenter: ridingCenters) {
            if (ridingCenter.getId() == Integer.parseInt(ridingCenterID)) {
                    ridingCenterAction = ridingCenter;
                    break;
            }
        }
        checkFreeStable(Integer.parseInt(newStable), ridingCenterAction);
        checkExistentStable(Integer.parseInt(newStable), ridingCenterAction);

        String action = "Move " + horse.getName() + " from stable " + horse.getStable() + " to stable " + newStable;
        makeAudit(action);

        for (RidingCenter ridingCenter: ridingCenters) {
            if (ridingCenter.getId() == Integer.parseInt(ridingCenterID)) {
                for (Horse horse1 : ridingCenter.getHorses()){
                    if (horse1.equals(horse)){
                        horse1.setStable(Integer.parseInt(newStable));
                        break;
                    }
                }
                break;
            }
        }

        String query = "UPDATE HORSES SET STABLE = " + newStable + " WHERE HORSE_ID = " + horse.getId();
        try{
            statement.execute(query);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("\nHorse moved successfully!\n");
    }
}
