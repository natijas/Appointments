package dao;

import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Represents a database access object for Patient.
 * The class provides connectivity with database in terms of Patient class objects.
 */
public class PatientDAO{
    /**
     * Gets a patient with specific ID from database
     * @param id - ID of patient
     * @return If patient with given ID exists, Optional will contain patient object, if not it will be empty
     */
    public Optional<Patient> get(int id) {
        String sql = "SELECT * FROM patients WHERE ID = ? ";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();
            if(rs.next())
                return Optional.of(resultPatient(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Gets all patients
     * @return List of all patients
     */
    public List<Patient> getAll(){
        List<Patient> result = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs  = stmt.executeQuery(sql);
            while(rs.next())
                result.add(resultPatient(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Saves patient object in database.
     * Even if patient object has its ID, the database will save it to database with automatically generated one.
     * @param patient - patient to be saved
     */
    public boolean save(Patient patient) {
        String sql = "INSERT INTO patients (ID, firstName, lastName, birthDate, gender)\n" +
                "VALUES(?, ?,?,?,?);";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, patient.getUserID());
            pstmt.setString(2, patient.getFirstName());
            pstmt.setString(3, patient.getLastName());
            pstmt.setDate(4, java.sql.Date.valueOf(patient.getBirthDate()));
            pstmt.setString(5, patient.getGender());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void update(Patient patient, String[] params) {

    }


    public void delete(Patient patient) {

    }

    /**
     * Transforms a result set to Patient object
     * @param rs - result set containing patient data
     * @return a Patient object
     * @throws SQLException - it uses java.sql.Date
     */
    private Patient resultPatient(ResultSet rs) throws SQLException{
        return new Patient(rs.getInt("ID"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("gender"),
                new java.sql.Date(rs.getDate("birthDate").getTime()).toLocalDate());
    }
}
