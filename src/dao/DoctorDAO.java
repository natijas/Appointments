package dao;

import model.Doctor;
import model.Specialization;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Represents a database access object for Doctors.
 * The class provides connectivity with database in terms of Doctor class objects.
 */
public class DoctorDAO{
    /**
     * Gets a doctor with a specific ID from database
     * @param id - ID of doctor
     * @return If doctor with provided ID exists, the Optional will include Doctor, if not it will be empty
     */
    public Optional<Doctor> get(int id) {
        String sql = "SELECT * FROM doctors WHERE ID = ? ";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();
            if(rs.next())
                return Optional.ofNullable(resultDoctor(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Gets all doctors having specific specialization
     * @param specialization - needed doctors' specialization
     * @return list of doctors having exact specialization
     */
    public List<Doctor> getDoctorsBySpecialization(Specialization specialization){
        List<Doctor> result = new ArrayList<>();
        String sql = "SELECT * FROM doctors WHERE specialization = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, specialization.getID());
            ResultSet rs  = pstmt.executeQuery();
            while(rs.next())
                result.add(resultDoctor(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Get all doctors
     * @return List of all doctors
     */
    public List<Doctor> getAll(){
        List<Doctor> result = new ArrayList<>();
        String sql = "SELECT * FROM doctors;";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs  = stmt.executeQuery(sql);
            while(rs.next())
                result.add(resultDoctor(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Saves new doctor in database
     * @param doctor - new Doctor to be saved
     */
    public void save(Doctor doctor) {
        String sql = "INSERT INTO doctors (firstName, lastName, specialization, gender)\n" +
                "VALUES(?,?,?,?);";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, doctor.getFirstName());
            pstmt.setString(2, doctor.getLastName());
            pstmt.setInt(3, doctor.getSpecialization().getID());
            pstmt.setString(4, doctor.getGender());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void update(Doctor doctor, String[] params) {

    }


    public void delete(Doctor doctor) {

    }

    /**
     * Transforms a result set to doctor object
     * @param rs - result set containing doctor data
     * @return a Doctor object
     * @throws SQLException - to get specialization database action is needed, can raise an exception
     */
    private Doctor resultDoctor(ResultSet rs) throws SQLException{
        Optional<Specialization> specialization = new SpecializationDAO().get(rs.getInt("specialization"));
        if(specialization.isPresent()) {
            return new Doctor(rs.getInt("ID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("gender"),
                    specialization.get());
        }
        return null;
    }
}
