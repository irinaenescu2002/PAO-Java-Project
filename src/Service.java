import appointments.Appointment;
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
import java.util.stream.Collectors;

public class Service {

    private List<RidingCenter> ridingCenters = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private HashMap<Client, List<Appointment>> appointments = new HashMap<>();

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
        System.out.println();
    }

    public void addRecords(){
        locations.add(new Location("Romania", "Bucuresti", "Panselutelor", "34", "1879098"));
        locations.add(new Location("Romania", "Brasov", "Cositorilor", "32A", "4324564"));
        locations.add(new Location("Romania", "Brasov", "Negru Voda", "23B", "4378890"));

        ridingCenters.add(new RidingCenter("Spirit", locations.get(0), 4, 5, "10:00", "18:00"));
        ridingCenters.add(new RidingCenter("FreeRide", locations.get(0), 6, 9, "12:00", "20:00"));
        ridingCenters.add(new RidingCenter("BeFree", locations.get(1), 5, 8, "8:00", "16:00"));
        ridingCenters.add(new RidingCenter("Horseland", locations.get(2), 8, 12, "8:00", "18:00"));

        addClient(new Client("Popescu", "Dan", "0786555456", "dan.ppsc@gmail.com"));
        addClient(new Client("Ionescu", "Maria", "0755436711", "maria.ionescu@yahoo.com"));
        addClient(new Client("Pirvu", "Carina", "0765112009", "carina.prv@gmail.com"));

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
            System.out.println();
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

    public void addArenaToRidingCenter(Arena _arena, int ridingCenterId){
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterId);
        HashSet <Arena> arenas = ridingCenter.getArenas();
        arenas.add(_arena);
        ridingCenter.setArenas(arenas);
    }

    public void addClient(Client client) {
        clients.add(client);
        clients = clients.stream().sorted(Comparator.comparing(Client::getLastName).thenComparing(Client::getFirstName)).collect(Collectors.toList());
        appointments.put(client, new ArrayList<>());
    }

    public void showClients() {
        int i = 0;
        for (Client client : clients){
            i = i + 1;
            System.out.println(i + ". " + client);
        }
    }

    public void addEmployeeToRidingCenter(Employee newEmployee, int ridingCenterId) {
        RidingCenter ridingCenter = getRidingCenterById(ridingCenterId);
        List <Employee> employees = ridingCenter.getEmployees();
        employees.add(newEmployee);
        employees = employees.stream().sorted(Comparator.comparing(Employee::getLastName).thenComparing(Employee::getFirstName)).collect(Collectors.toList());
        ridingCenter.setEmployees(employees);
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
}
