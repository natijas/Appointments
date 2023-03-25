package model;

import dao.LocalizationDAO;

/**
 * Represents a localization
 */
public class Localization {
    /**
     * Unique ID of localization
     */
    int ID;
    /**
     * Localization's name
     */
    String name;

    /**
     * Class constructor specifying ID and name
     * @param ID
     * @param name
     */
    public Localization(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    public Localization(String name){
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string with localization's ID and name
     * @return info about localization
     */
    public String toString(){
        return getID()+" "+getName();
    }
}
