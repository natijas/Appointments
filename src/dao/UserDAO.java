package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Represents a database access object for User.
 * The class provides connectivity with database in terms of User class objects.
 */
public class UserDAO{
    /**
     * Gets a user with specific ID from database.
     * @param id - ID of user
     * @return If user with given ID exists, Optional will contain user object, if not it will be empty
     */
    public Optional<User> get(int id) {
        String sql = "SELECT * FROM users WHERE ID = ? ";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();
            if(rs.next())
                return Optional.of(resultUser(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Gets a user with specific login from database
     * @param username - user's username
     * @return User object matching given username
     */
    public int getByUsername(String username) {
        String sql = "SELECT COUNT(ID) as quantity FROM users WHERE login = ? ";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                return rs.getInt("quantity");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Gets ID of user with specific login and password
     * @param username - user's username
     * @param password - user's password
     * @return - ID of matching user, 0 if no user is found
     */
    public int getSignIn(String username, String password){
        String sql = "SELECT ID FROM users WHERE login = ? AND password = ? AND active = TRUE ";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                return rs.getInt("ID");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Saves new user to the database
     * Even if user object has its ID, the database will save it to database with automatically generated one.
     * @param user - user to be saved
     * @return ID of the newly saved user
     */
    public int save(User user) {
        String sql = "INSERT INTO users (login, password)\n" +
                "VALUES(?,?);";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }


    public void update(User user, String[] params) {

    }


    public void delete(User user) {

    }
    /**
     * Transforms a result set to User object
     * @param rs - result set containing user data
     * @return a User object
     */
    private User resultUser(ResultSet rs) throws SQLException{
        return new User(rs.getInt("ID"),
                rs.getString("login"),
                rs.getString("password"));
    }
}
