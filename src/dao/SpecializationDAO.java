package dao;

import model.Specialization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Represents a database access object for Specialization.
 * The class provides connectivity with database in terms of Specialization class objects.
 */
public class SpecializationDAO{
    /**
     * Gets a specialization with specific ID from database.
     * @param id - ID of specialization
     * @return If specialization with given ID exists, Optional will contain specialization object, if not it will be empty
     */
    public Optional<Specialization> get(int id) {
        String sql = "SELECT * FROM specializations WHERE ID = ? ";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();
            if(rs.next())
                return Optional.of(resultSpecialization(rs));
            else
                return Optional.empty();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Gets all specializations
     * @return List of all specializations
     */
    public List<Specialization> getAll(){
        List<Specialization> result = new ArrayList<>();
        String sql = "SELECT * FROM specializations;";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs  = stmt.executeQuery(sql);
            while(rs.next())
                result.add(resultSpecialization(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Saves specialization object in database.
     * Even if specialization object has its ID, the database will save it to database with automatically generated one.
     * @param specialization - specialization to be saved
     */
    public int save(Specialization specialization) {
        String sql = "INSERT INTO specializations (name)\n" +
                "VALUES(?);";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, specialization.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }


    public void update(Specialization specialization, String[] params) {

    }


    public void delete(Specialization specialization) {

    }
    /**
     * Transforms a result set to Specialization object
     * @param rs - result set containing specialization data
     * @return a Specialization object
     */
    private Specialization resultSpecialization(ResultSet rs) throws SQLException{
        return new Specialization(rs.getInt("ID"),
                rs.getString("name"));
    }
}
