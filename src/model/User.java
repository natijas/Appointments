package model;

/**
 * Represents a user, who has an account in system
 */
public class User {
    /**
     * User's unique ID
     */
    int ID;
    /**
     * User's login
     */
    String login;
    /**
     * User's password
     */
    String password;

    /**
     * Class constructor specifying login and password
     * @param login - user's login
     * @param password - user's password
     */
    public User( String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Class constructor specifying ID, login and password
     * @param ID - user's ID
     * @param login - user's login
     *      * @param password - user's password
     */
    public User(int ID, String login, String password) {
        this(login, password);
        this.ID = ID;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
