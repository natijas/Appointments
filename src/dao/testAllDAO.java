package dao;

import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Class providing methods to test Data Access Objects
 */
public class testAllDAO {
    /**
     * Scanner for taking user's input
     */
    Scanner scanner = new Scanner(System.in);
    /**
     * Actually logged in patient
     */
    Patient patient;

    /**
     *Class constructor starting the menu interface
     */
    public testAllDAO() {
       // var logIn = new SignInController();
        //logIn.run();
       // patient = logIn.getPatient();
        patient = new Patient(1,"cos", "łos", "m", LocalDate.of(2001,1,1));
        System.out.println(patient);
        int action = menu();
        while(action!=0){
            switch(action){
                case 1 -> addLocalizations();
                case 2 -> showLocalizations();
                case 3 -> addSpecializations();
                case 4 -> showSpecializations();
                case 5 -> selectDoctorsSpec();
                case 6 -> showDoctors();
                case 7 -> addDoctors();
                case 8 -> showFreeAppointments();
                case 9 -> showAppointments();
                case 10 -> searchAppointments();
                case 11 -> addAppointments();
                case 12 -> makeAnAppointment();
                case 13 -> showMyAppointments();
                case 14 -> showDoctorShifts();
                case 15 -> resignAppointment();
                case 16 -> showMyPastAppointments();
            }
            action = menu();
        }
    }

    /**
     * Allows to choose visit to resign.
     */
    private void resignAppointment(){
        var appDAO = new AppointmentDAO();
        var searchParams = new HashMap<String, Integer>();
        searchParams.put("patient", patient.getUserID());
        List<Appointment> result = appDAO.
                search(LocalDateTime.now(),
                        LocalDateTime.of(2024,10,15,12,10),
                        searchParams);
        for(int i = 0; i < result.size(); i++){
            System.out.println(i+" "+result);
        }
        System.out.println("Wybierz którą wizytę chciałbyś usunąć");
        int number = scanner.nextInt();
        appDAO.resignAppointment(result.get(number));
    }

    /**
     * Shows all doctor's shifts.
     */
    private void showDoctorShifts() {
        List<Shift> result = new ArrayList<>();
        System.out.println("Wybierz ID doktora");
        Optional<Doctor> doctor = new DoctorDAO().get(scanner.nextInt());
        if(doctor.isPresent()) result = new ShiftDAO().getDoctorShift(doctor.get());
        result.forEach(System.out::println);
    }

    /**
     * Shows all user's appointments.
     */
    private void showMyAppointments() {
        var searchParams = new HashMap<String, Integer>();
        searchParams.put("patient", patient.getUserID());
        List<Appointment> result = new AppointmentDAO().
                search(LocalDateTime.now(),
                        LocalDateTime.of(2024,10,15,12,10),
                        searchParams);
        result.forEach(System.out::println);
    }


    private void showMyPastAppointments() {
        var searchParams = new HashMap<String, Integer>();
        searchParams.put("patient", patient.getUserID());
        List<Appointment> result = new AppointmentDAO().search(LocalDateTime.of(2020,1,1, 1, 1, 1),
                LocalDateTime.now(), searchParams);
        result.forEach(System.out::println);
    }

    /**
     * allows user to make an appointment.
     */
    private void makeAnAppointment() {
        System.out.println("Wybierz id wizyty");
        int id = scanner.nextInt();
        var dao = new AppointmentDAO();
        Optional<Appointment> appo = dao.get(id);
        appo.ifPresent(appointment -> dao.update(appointment, patient));
    }

    /**
     * creates new free appointment.
     */
    private void addAppointments() {
        System.out.println("id doktora");
        int doc = scanner.nextInt();
        System.out.println("id lokalizacji");
        int loc = scanner.nextInt();
        System.out.println("Data w formacie YYYY-MM-DD-HH-MM");
        LocalDateTime datetime = StringToDate(scanner.next());

        Optional<Doctor> doctor = new DoctorDAO().get(doc);
        Optional<Localization> local = new LocalizationDAO().get(loc);
        if(doctor.isPresent() && local.isPresent())
            new AppointmentDAO().save(new Appointment(datetime, doctor.get(), null,  local.get()));
    }

    /**
     * Allows user to find specific appointments using few parameters
     */
    private void searchAppointments() {
        Map<String, Integer> searchParams = new HashMap<>();
        System.out.println("poczatek okresu w postaci YYYY-MM-DD");
        LocalDateTime begin = StringToDate(scanner.next());
        System.out.println("koniec okresu w postaci YYYY-MM-DD");
        LocalDateTime end = StringToDate(scanner.next());
        System.out.println("Id specjalizacji");
        int spec = scanner.nextInt();
        if(spec != 0) searchParams.put("specialization", spec);
        System.out.println("Id lokalizacji");
        int loc = scanner.nextInt();
        if(loc != 0) searchParams.put("localization", loc);

        List<Appointment> result = new AppointmentDAO().search(begin, end, searchParams);
        result.forEach(System.out::println);
        if(result.isEmpty()) System.out.println("nic nie ma");
    }

    /**
     * Shows free(without specified patient) appointments
     */
    private void showFreeAppointments(){
        List<Appointment> apps = new AppointmentDAO().search(LocalDateTime.of(2020,5,10,11,0),
                LocalDateTime.of(2050,1,1,1,1), new HashMap<>());
        apps.forEach(System.out::println);
    }

    /**
     * Shows all appointments
     */
    private void showAppointments() {
        List<Appointment> apps = new AppointmentDAO().getAll();
        apps.forEach(System.out::println);
    }

    /**
     * Adds a new doctor to database.
     */
    private void addDoctors() {
        System.out.println("Imie");
        String vorname = scanner.next();
        System.out.println("Nazwisko");
        String name = scanner.next();
        System.out.println("Płeć m/f");
        String gender = scanner.next();
        System.out.println("Specjalizacja(int)");
        int spec = scanner.nextInt();
        new DoctorDAO().save(new Doctor(vorname,name,gender,new Specialization(spec)));
    }

    /**
     * Shows all doctors.
     */
    private void showDoctors(){
        List<Doctor> docs = new DoctorDAO().getAll();
        docs.forEach(System.out::println);
    }

    /**
     * Shows all doctors with specific specialization.
     */
    private void selectDoctorsSpec() {
        System.out.println("Podaj numer specjalizacji");
        List<Doctor> doctors = new DoctorDAO().getDoctorsBySpecialization(new Specialization(scanner.nextInt()));
        System.out.println(doctors);
    }

    /**
     * Shows all specializations.
     */
    private void showSpecializations(){
        List<Specialization> specs = new SpecializationDAO().getAll();
        specs.forEach(System.out::println);
    }

    /**
     * Adds new specialization to database.
     */
    private void addSpecializations() {
        System.out.println("Podaj nazwę nowej specjalizacji");
        new SpecializationDAO().save(new Specialization(scanner.next()));
    }

    /**
     * Shows all localizations.
     */
    private void showLocalizations(){
        List<Localization> locs = new LocalizationDAO().getAll();
        locs.forEach(System.out::println);
    }

    /**
     * Adds new localization
     */
    private void addLocalizations(){
        System.out.println("Podaj nazwę nowej lokalizacji");
        new LocalizationDAO().save(new Localization(scanner.next()));
    }

    /**
     * Displays menu.
     * @return user's choice which menu item was chosen.
     */
    public int menu(){
        System.out.println("""
                1. Dodaj lokalizacje
                2. Pokaż lokalizacje
                3. Dodaj specjalizacje
                4. Pokaż specjalizacje
                5. Zobacz lekarzy - specjalność
                6. Zobacz lekarzy - wszyscy
                7. Dodaj lekarzy
                8. Zobacz wizyty - wolne
                9. Zobacz wizyty - wszystkie
                10. Wyszukaj wizyty
                11. Stwórz nowe wizyty
                12. Umów się na wizytę
                13. Pokaż moje wizyty
                14. Pokaż zmiany doktora
                15. odwołaj wizytę
                16. pokaz przeszłe wizyty
                0. Wyjdz
                """);
        return scanner.nextInt();
    }

    /**
     * Transforms String in specific format to LocalDateTime object.
     * @param inputDate - string to be transformed
     * @return LocalDateTime matching the string
     */
    public LocalDateTime StringToDate(String inputDate){
        List<Integer> d = Arrays.stream(inputDate.split("-")).map(Integer::parseInt).toList();
        if(d.size() == 5)
            return LocalDateTime.of(d.get(0), d.get(1), d.get(2), d.get(3), d.get(4));
        else
            return LocalDate.of(d.get(0), d.get(1), d.get(2)).atStartOfDay();
    }


}
