package dao;

import model.Localization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Represents a database access object for Localization.
 * The class provides connectivity with database in terms of Localization class objects.
 */
public class LocalizationDAO{
    /**
     * Gets Localization with specific ID from database.
     * @param id - ID of the localization
     * @return If localization with given ID exists, Optional will include Localization, if not it will be empty
     */
    public Optional<Localization> get(int id) {
        String sql = "SELECT ID, name FROM localizations WHERE ID = ? ";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();
            if(rs.next())
                return Optional.of(resultLocalization(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Gets all localizations available in database.
     * @return All localizations available
     */
    public List<Localization> getAll(){
        List<Localization> result = new ArrayList<>();
        String sql = "SELECT * FROM localizations;";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs  = stmt.executeQuery(sql);
            while(rs.next())
                result.add(resultLocalization(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Saves new localization in database.
     * Even if localization object doesn't have ID, the database will give it ID itself.
     * @param localization - localization to be saved
     */
    public void save(Localization localization) {
        String sql = "INSERT INTO localizations (name)\n" +
                "VALUES(?);";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, localization.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void update(Localization localization, String[] params) {

    }


    public void delete(Localization localization) {

    }
    /**
     * Transforms a result set to localization object
     * @param rs - result set containing localization data
     * @return a Localization object
     */
    private Localization resultLocalization(ResultSet rs) throws SQLException{
        return new Localization(rs.getInt("ID"),
                rs.getString("name"));
    }
}
