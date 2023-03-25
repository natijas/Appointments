package dao;

import model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents a database access object for Appointments.
 * The class provides connectivity with database in terms of Appointment class objects.
 */
public class AppointmentDAO{
    /**
     * Gets a specific appointment from database.
     * @param id - unique ID of appointment
     * @return If object with specified ID exists the Optional object will contain appointment, otherwise it will contain null
     */
    public Optional<Appointment> get(int id) {
        String sql = "SELECT * FROM appointments WHERE ID = ? ";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();
            if(rs.next()) {
                return Optional.ofNullable(resultAppointment(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Gets all appointments in database
     * @return All appointments stored in a list
     */
    public List<Appointment> getAll(){
        List<Appointment> result = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs  = stmt.executeQuery(sql);
            while(rs.next())
                result.add(resultAppointment(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    /**
     * Creates a query to SELECT specific appointments from database.
     * @param params - database column names
     * @param useBeginDate - the query should select 10 appointments, starting from an indent dependent of this
     * @param useEndDate - the query should select 10 appointments, starting from an indent dependent of this
     * @return a string containing a SQL query to find appointments
     */
    private String searchBuilder(Map<String, Integer> params, boolean useBeginDate, boolean useEndDate){
        StringBuilder res = new StringBuilder();
        if(params.containsKey("patient")){
            if(params.get("patient") != null) {
                res.append("SELECT appointments.ID, appointments.date," +
                        " appointments.doctor, doctors.firstName as doctorFirstName, doctors.lastName as doctorLastName," +
                        "doctors.gender as doctorGender, doctors.specialization as doctorSpec, specializations.name as doctorSpecName," +
                        " appointments.patient, patients.firstName as patientFirstName, patients.lastName as patientLastName," +
                        " patients.birthDate as patientBirthDate, patients.gender as patientGender, appointments.localization," +
                        " localizations.name as localizationName FROM appointments " +
                        "JOIN doctors ON (appointments.doctor=doctors.ID) " +
                        "JOIN patients ON (appointments.patient=patients.ID) " +
                        "JOIN localizations ON (appointments.localization=localizations.ID) " +
                        "JOIN specializations ON (doctors.specialization=specializations.ID) ");
            }
            else{
                res.append("SELECT appointments.ID, appointments.date," +
                        " appointments.doctor, doctors.firstName as doctorFirstName, doctors.lastName as doctorLastName," +
                        "doctors.gender as doctorGender, doctors.specialization as doctorSpec, specializations.name as doctorSpecName," +
                        "appointments.localization," +
                        " localizations.name as localizationName FROM appointments " +
                        "JOIN doctors ON (appointments.doctor=doctors.ID) " +
                        "JOIN localizations ON (appointments.localization=localizations.ID) " +
                        "JOIN specializations ON (doctors.specialization=specializations.ID) ");
            }
        }

        if (useBeginDate || useEndDate || !params.isEmpty()) res.append(" WHERE ");
        if (useBeginDate) res.append(" appointments.date >= ?");
        if (useBeginDate && useEndDate) res.append(" AND ");
        if (useEndDate) res.append(" appointments.date <= ?");
        int i = 0;
        for (var entry : params.keySet()) {
            if (i != 0 || useBeginDate || useEndDate) {
                res.append(" AND ");
            }
            i++;
            String val;
            if (params.get(entry) == null) {
                val = "IS NULL";
            } else {
                val = "= ?";
            }
            if("specialization".equals(entry)){
                res.append("appointments.doctor IN (SELECT ID FROM doctors WHERE specialization " + val + ")");
            }
            else {
                res.append(entry).append(" " + val);
            }
        }
        res.append(" ORDER BY appointments.date ASC");
        return res.toString();
    }
    /**
     * Selects specific appointments from database, depending on search options
     * @param begin - start of search period
     * @param end - end of search period
     * @param params - parameters of searching
     * @return Appointments meeting the requirements
     */
    public List<Appointment> search(LocalDateTime begin, LocalDateTime end, Map<String, Integer> params){
        List<Appointment> res = new ArrayList<>();
        String sql = searchBuilder(params, begin != null, end != null);
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int i = 0;
            if (begin != null) pstmt.setTimestamp(++i, Timestamp.valueOf(begin));
            if (end != null) pstmt.setTimestamp(++i, Timestamp.valueOf(end));
            for(var value : params.values()){
                if (value != null) {
                    pstmt.setInt(++i, value);
                }
            }
            ResultSet rs  = pstmt.executeQuery();
            while(rs.next()) {
               // res.add(resultAppointment(rs));
                res.add(enhancedResultAppointment(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * Saves an appointment object in database.
     * The appointment object ID is not saved in database, but generated automatically. The newly saved appointment is free of patient,
     * so the patient can make an appointment later
     * @param appointment - appointment to be saved
     */
    public void save(Appointment appointment) {
        String sql = "INSERT INTO appointments (date, doctor, localization)\n" +
                "VALUES(?, ?,?);";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(appointment.getDate()));
            pstmt.setInt(2, appointment.getDoctor().getID());
            pstmt.setInt(3, appointment.getLocalization().getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Set patient to free appointment
     * @param appointment - appointment where the patient ought to be saved
     * @param patient - patient to be saved in appointment
     */
    public void update(Appointment appointment, Patient patient) {
        String sql = "UPDATE appointments SET patient = ? WHERE ID = ?;";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patient.getUserID());
            pstmt.setInt(2, appointment.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sets the appointment patient field as NULL in the database.
     * The resigned appointment is again available to choose.
     * @param appointment - appointment where the patient field is about to be nullified.
     */
    public void resignAppointment(Appointment appointment) {
        String sql = "UPDATE appointments SET patient = NULL WHERE ID = ?;";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, appointment.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Generates appointment object from result set from database.
     * @param rs - single database result
     * @return Appointment object
     * @throws SQLException - function calls Doctor, Patient and Localization database access objects which can throw SQL exception
     */
    private Appointment resultAppointment(ResultSet rs) throws SQLException{
        int id = rs.getInt("ID");
        Optional<Doctor> doctor = new DoctorDAO().get(rs.getInt("doctor"));
        Optional<Patient> patient = new PatientDAO().get(rs.getInt("patient"));
        Optional<Localization> loc = new LocalizationDAO().get(rs.getInt("localization"));
        if (doctor.isPresent() && patient.isPresent() && loc.isPresent()) {
            return new Appointment(id, rs.getTimestamp("date").toLocalDateTime(),
                    doctor.get(), patient.get(), loc.get());
        }
        if (patient.isEmpty() && doctor.isPresent() && loc.isPresent()) {
            return new Appointment(id, rs.getTimestamp("date").toLocalDateTime(),
                    doctor.get(), null, loc.get());
        }
        return null;
    }

    private Appointment enhancedResultAppointment(ResultSet rs) throws SQLException{
        int id = rs.getInt("ID");
        var doctor = new Doctor(rs.getInt("doctor"), rs.getString("doctorFirstName"), rs.getString("doctorLastName"),
                rs.getString("doctorGender"), new Specialization(rs.getInt("doctorSpec"), rs.getString("doctorSpecName")));
        Patient patient = null;
        try {
            rs.findColumn("patient");
            if (rs.findColumn("patient") != 0) {
                patient = new Patient(rs.getInt("patient"),
                        rs.getString("patientFirstName"),
                        rs.getString("patientLastName"),
                        rs.getString("patientGender"),
                        new java.sql.Date(rs.getDate("patientBirthDate").getTime()).toLocalDate());
            }
        }
            catch (SQLException sqlex){
        }
        var localization = new Localization(rs.getInt("localization"), rs.getString("localizationName"));
        return new Appointment(id, rs.getTimestamp("date").toLocalDateTime(),
                doctor, patient, localization);


    }

}
