package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Represents an util class to connect to database.
 */
public class DbConnection
{
    /**
     * Database's address
     */
    private final static String Url = "jdbc:mariadb://localhost:3306/app";
    private final static String Username = "root";
    private final static String Password = "admin";

    /**
     * Function used to get a connection with database
     * @return Connection to database
     * @throws SQLException - if connection fails it raises an exception
     */
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(Url, Username, Password);
    }
}