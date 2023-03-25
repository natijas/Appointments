package model;

import dao.DoctorDAO;

import java.util.Objects;

/**
 * Represents a doctor person.
 * Inherits after Person class
 */
public class Doctor extends Person{
    /**
     *  Unique ID of doctor
     */
    int ID;
    /**
     * Doctor specialization
     */
    Specialization specialization;

    /**
     * Class constructor specifying doctor's first name, last name, gender and specialization
     * @param firstName - first name
     * @param lastName - last name
     * @param gender - gender
     * @param specialization - specialization
     */
    public Doctor(String firstName, String lastName, String gender, Specialization specialization) {
        super(firstName, lastName, gender);
        this.specialization = specialization;
    }

    /**
     * Class constructor specifying doctor's ID, first name, last name, gender and specialization
     * @param ID - ID
     * @param firstName - first name
     * @param lastName - last name
     * @param gender - gender
     * @param specialization - specialization
     */
    public Doctor(int ID, String firstName, String lastName, String gender, Specialization specialization) {
        this(firstName,lastName,gender,specialization);
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    /**
     * Creates a text describing doctor
     * @return String describing doctor information
     */
    public String toString(){
        DoctorDAO dao = new DoctorDAO();
        return getID()+" "+getFirstName()+" "+getLastName()+" "+(Objects.equals(getGender(), "m") ? "Mężczyzna " : "Kobieta ")+
                getSpecialization().getName();
    }
}
