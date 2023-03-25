package dao;

import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Represents a class which methods can be used to initialize and fill the database.
 */
public class DbInitializator {
    /**
     * calls functions needed, editable.
     */
    public void initialize() {
        createTables();

        insertUsers();
        insertPatients();
        insertSpecializations();
        insertDoctors();
        insertLocalizations();
        insertShifts();
        insertAppointments();

        for(int i = 2; i <= 31; i++ ) {
            generateAppointments(LocalDate.of(2023, 1, i));
        }
    }

    /**
     * Creates an empty database with all needed fields.
     */
    private void createTables(){
        String[] sql = {
                """
                CREATE TABLE IF NOT EXISTS users (
                  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                  login varchar(255) UNIQUE,
                  password varchar(255),
                  active bool DEFAULT true
                );""",
                """
                CREATE TABLE IF NOT EXISTS specializations (
                  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                  name varchar(255)
                );""",
                """
                CREATE TABLE IF NOT EXISTS localizations (
                  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                  name varchar(255)
                );""",
                """
                CREATE TABLE IF NOT EXISTS patients (
                  ID INTEGER PRIMARY KEY,
                  firstName varchar(255),
                  lastName varchar(255),
                  birthDate date,
                  gender char,
                  FOREIGN KEY (ID) REFERENCES users (ID)
                );""",

                """
                CREATE TABLE IF NOT EXISTS doctors (
                  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                  firstName varchar(255),
                  lastName varchar(255),
                  specialization int,
                  gender char,
                  FOREIGN KEY (specialization) REFERENCES specializations (ID)
                );""",
                """
                CREATE TABLE IF NOT EXISTS appointments (
                  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                  date datetime,
                  doctor int,
                  patient int DEFAULT NULL,
                  localization int,
                  FOREIGN KEY (doctor) REFERENCES doctors (ID),
                  FOREIGN KEY (patient) REFERENCES patients (ID),
                  FOREIGN KEY (localization) REFERENCES localizations (ID)
                );""",
                """
                CREATE TABLE IF NOT EXISTS shifts (
                  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                  doctor int,
                  localization int,
                  dayOfTheWeek int,
                  shiftStart time,
                  shiftEnd time,
                  FOREIGN KEY (doctor) REFERENCES doctors (ID),
                  FOREIGN KEY (localization) REFERENCES localizations (ID)
                );"""};
        for(var query : sql){
            executeStatement(query);
        }
    }

    /**
     * Fills database with few test Specializations.
     */
    private void insertSpecializations(){
        String[] specializations = {
                "Pediatra",
                "Internista",
                "Kardiolog",
                "Laryngolog",
                "Ginekolog",
                "Dermatolog",
                "Ortopeda"};
        var dao = new SpecializationDAO();
        for(var spec : specializations)
            dao.save(new Specialization(spec));
    }

    /**
     * Fills database with few test Localizations.
     */
    private void insertLocalizations(){
        String[] localizations = {
                "Kraków Chabrowa",
                "Rybnik Słowiańska",
                "Ostrołęka Piastowska",
                "Biała Podlaska Zamkowa",
                "Przemyśl Rzemieniewicka",
                "Opole Urocza",
                "Konin Słowiańska"};
        var dao = new LocalizationDAO();
        for(var localization : localizations)
            dao.save(new Localization(localization));
    }

    /**
     * Fills database with few test doctors.
     */
    private void insertDoctors(){
        var dao= new DoctorDAO();
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Adam", "Nowak", "m", new Specialization(1)));
        doctors.add(new Doctor("Anna", "Kochanowska", "f", new Specialization(2)));
        doctors.add(new Doctor("Eryk", "Urbański", "m", new Specialization(3)));
        doctors.add(new Doctor("Damian", "Krajewski", "m", new Specialization(4)));
        doctors.add(new Doctor("Alex", "Pawlak", "m", new Specialization(5)));
        doctors.add(new Doctor("Aleksy", "Nowak", "m", new Specialization(6)));
        doctors.add(new Doctor("Kajetan", "Kucharski", "m", new Specialization(7)));
        doctors.add(new Doctor("Diego", "Andrzejewski", "m", new Specialization(1)));
        doctors.add(new Doctor("Łukasz", "Woźniak", "m", new Specialization(2)));
        doctors.add(new Doctor("Błażej", "Rutkowski", "m", new Specialization(3)));
        doctors.add(new Doctor("Stefania", "Urbańska", "m", new Specialization(4)));
        doctors.add(new Doctor("Milena", "Kamińska", "m", new Specialization(5)));
        doctors.add(new Doctor("Adela", "Ziółkowska", "m", new Specialization(6)));
        doctors.add(new Doctor("Joanna", "Kowalska", "m", new Specialization(7)));
        doctors.add(new Doctor("Agnieszka", "Brzezińska", "m", new Specialization(1)));
        doctors.add(new Doctor("Kinga", "Kubiak", "m", new Specialization(2)));
        doctors.add(new Doctor("Hortensja", "Błaszczyk", "m", new Specialization(3)));
        doctors.add(new Doctor("Judyta", "Nowak", "m", new Specialization(4)));
        for(var doc : doctors){
            dao.save(doc);
        }
    }

    /**
     * Randomly generates all shifts(from monday to saturday) for every doctor in random place
     */
    private void insertShifts(){
        // 1 - monday ... 6 - saturday
        var shiftDAO = new ShiftDAO();
        List<Doctor> doctors = new DoctorDAO().getAll();
        List<Localization> localizations = new LocalizationDAO().getAll();
        Random rand = new Random();
        for(Doctor doctor : doctors){
            for(int i = 1; i <= 6; i++){
                shiftDAO.save(new Shift(doctor,
                        localizations.get(
                                rand.nextInt(localizations.size())), i, 8, 16));
            }
        }
    }

    /**
     * Generates all appointments in specified day.
     * @param date - day to appointments be generated in
     */
    private void generateAppointments(LocalDate date){
        List<Shift> shifts = new ShiftDAO().getAll();
        var appDAO = new AppointmentDAO();
            for(var shift : shifts) {
                if(shift.getDayOfWeek() != date.getDayOfWeek().getValue()) continue;
                LocalTime start = LocalTime.of(shift.getShiftStart(), 0);
                LocalTime end = LocalTime.of(shift.getShiftEnd(), 0);
                for (LocalTime time = start; time.compareTo(end) < 0; time = time.plusMinutes(60)) {
                    appDAO.save(new Appointment(
                            LocalDateTime.of(date,time),
                            shift.getDoctor(),
                            null,
                            shift.getLocalization()));
                }
            }

    }

    /**
     * Fills database with few test users.
     */
    private void insertUsers(){
        UserDAO dao = new UserDAO();
        List<User> patients = new ArrayList<>();
        patients.add(new User("test", "1234"));
        patients.add(new User("tset", "4321"));
        for(var patient : patients){
            dao.save(patient);
        }
    }

    /**
     * Fills database with few test patients
     * Attention, patient must be connected to user
     */
    private void insertPatients(){
        var dao = new PatientDAO();
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1,"Adam", "Kowalski", "m",LocalDate.of(2001,12,1)));
        patients.add(new Patient(2,"Ewa", "Kowalska", "f", LocalDate.of(1999,11,5)));
        for(var patient : patients){
            dao.save(patient);
        }

        dao.get(1).ifPresent(System.out::println);
        dao.get(2).ifPresent(System.out::println);
    }

    private void insertAppointments(){
        var dao = new AppointmentDAO();
        var doctor = new DoctorDAO().get(1);
        var patient = new PatientDAO().get(2);
        var loc = new LocalizationDAO().get(1);
        if(doctor.isPresent() && patient.isPresent() && loc.isPresent()){
            var appointment = new Appointment(LocalDateTime.of(2022, 12, 15, 12, 0),
                    doctor.get(), patient.get(), loc.get());
            dao.save(appointment);
        }

        dao.get(1).ifPresent(System.out::println);
    }

    private void selectAppointments(){
        var app = new AppointmentDAO();
        var doctor = new DoctorDAO().get(1);
        //var patient = new PatientDAO().get(2);
        var loc = new LocalizationDAO().get(1);
        Map<String, Integer> searchParams = new HashMap<>();
        searchParams.put("doctor", 1);
        searchParams.put("localization", 1);
        var begin = LocalDateTime.of(2022, 10, 18, 8, 0);
        var end = LocalDateTime.of(2022, 10, 20, 8, 0);
        var appo = app.search(begin, end, searchParams);
        System.out.println(appo);

        Map<String, Integer> searchParams2 = new HashMap<>();
        searchParams2.put("specialization", 1);
        //searchParams2.put("localization", 1);
        var appo2 = app.search(begin, end, searchParams2);
        System.out.println(appo2);
    }

    /**
     * Executes a simple SQL query
     * @param sql - query to be executed
     */
    private void executeStatement(String sql){
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch(SQLException e){
            System.out.println("błąd");
        }
    }
}
