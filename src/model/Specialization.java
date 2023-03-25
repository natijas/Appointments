package model;

import dao.SpecializationDAO;

/**
 * Represents medical specialization
 */
public class Specialization {
    /**
     * Unique ID of the specialization
     */
    int ID;
    /**
     * Name that identifies specialization
     */
    String name;

    /**
     * Class constructor specifying ID and name
     * @param ID - unique ID of specialization
     * @param name - name describing specialization
     */
    public Specialization(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    /**
     * Class constructor specifying its ID
     * @param ID
     */
    public Specialization(int ID){
        SpecializationDAO dao = new SpecializationDAO();
        this.ID = ID;
        dao.get(ID).ifPresent(specialization -> this.name = specialization.getName());
    }

    public Specialization(String name){
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
     * Provides ID and name specialization
     * @return String consisting of ID and name
     */
    public String toString(){
        return getID()+" "+getName();
    }
}
