package model;

import java.time.LocalDateTime;

/**
 * Represents a single Patient-Doctor appointment.
 */
public class Appointment {
    /**
     * Unique ID of the appointment.
     */
    private int ID;
    /**
     * Date of the appointment.
     */
    private LocalDateTime date;
    /**
     * Doctor object participating the appointment.
     */
    private Doctor doctor;
    /**
     * Patient participating the appointment.
     */
    private Patient patient;
    /**
     * Localization of the appointment.
     */
    private Localization localization;

    /**
     * Class constructor specifying date, doctor, patient and localization.
     * @param date - date of appointment
     * @param doctor - doctor performing appoitment
     * @param patient - patient performing appointment
     * @param localization - localization of appointment
     */
    public Appointment(LocalDateTime date, Doctor doctor, Patient patient, Localization localization) {
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.localization = localization;
    }

    /**
     * Class constructor specifying ID, date, doctor, patient and localization.
     * @param ID - unique ID of the appointment
     * @param date - date of appointment
     * @param doctor - doctor performing appointment
     * @param patient - patient performing appointment
     * @param localization - localization of appointment
     */
    public Appointment(int ID, LocalDateTime date, Doctor doctor, Patient patient, Localization localization) {
        this(date,doctor,patient,localization);
        this.ID = ID;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Localization getLocalization() {
        return localization;
    }
    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    /**
     * Creates readable text describing all useful appointment fields
     * @return string describing appointment
     */
    public String toString(){
        return  "ID: "+getID()+" Doctor: "+getDoctor()+" Patient: "+getPatient()+" When: "+getDate()+" Where: "+getLocalization().name;
    }
}
