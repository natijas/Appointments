package dao;

import model.Doctor;
import model.Localization;
import model.Shift;

import javax.print.Doc;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Represents a database access object for Shift.
 * The class provides connectivity with database in terms of Shift class objects.
 */
public class ShiftDAO{
    /**
     * Gets a shift with specific ID from database
     * @param id - ID of shift
     * @return If shift with given ID exists, Optional will contain shift object, if not it will be empty
     */
    public Optional<Shift> get(int id) {
        String sql = "SELECT * FROM shifts WHERE ID = ? ";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();
            if(rs.next()) return Optional.ofNullable(resultShift(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Gets all shifts
     * @return List of all shifts
     */
    public List<Shift> getAll(){
        List<Shift> result = new ArrayList<>();
        String sql = "SELECT * FROM shifts;";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs  = stmt.executeQuery(sql);
            while(rs.next())
                result.add(resultShift(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Gets specific doctor all shifts
     * @param doctor - doctor whose shifts are being searched
     * @return List of specific doctor shifts
     */
    public List<Shift> getDoctorShift(Doctor doctor){
        List<Shift> result = new ArrayList<>();
        String sql = "SELECT * FROM shifts WHERE doctor = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, doctor.getID());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                result.add(resultShift(rs));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Saves shift object in database.
     * Even if shift object has its ID, the database will save it to database with automatically generated one.
     * @param shift - shift to be saved
     */
    public int save(Shift shift) {
        String sql = "INSERT INTO shifts (doctor, localization, dayOfTheWeek, shiftStart, shiftEnd)\n" +
                "VALUES(?,?,?,?,?);";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, shift.getDoctor().getID());
            pstmt.setInt(2, shift.getLocalization().getID());
            pstmt.setInt(3, shift.getDayOfWeek());
            pstmt.setTime(4, Time.valueOf(LocalTime.of(shift.getShiftStart(),0)));
            pstmt.setTime(5, Time.valueOf(LocalTime.of(shift.getShiftEnd(),0)));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }


    public void update(Localization localization, String[] params) {

    }


    public void delete(Localization localization) {

    }

    /**
     * Transforms a result set to Shift object
     * @param rs - result set containing shift data
     * @return a Shift object
     * @throws SQLException - it uses SQL queries to get doctor and localization objects
     */
    private Shift resultShift(ResultSet rs) throws SQLException{
        Optional<Doctor> doctor = new DoctorDAO().get(rs.getInt("doctor"));
        Optional<Localization> localization = new LocalizationDAO().get(rs.getInt("localization"));
        if(doctor.isPresent() && localization.isPresent()) {
            return new Shift(rs.getInt("ID"), doctor.get(), localization.get(),
                    rs.getInt("dayOfTheWeek"), rs.getTime("shiftStart").toLocalTime().getHour(),
                    rs.getTime("shiftEnd").toLocalTime().getHour());
        }
        return null;
    }
}
