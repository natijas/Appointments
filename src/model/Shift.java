package model;

/**
 * Represents a doctor's shift in a clinic.
 * Multiple shifts can describe doctor's week work plan
 */
public class Shift {
    /**
     * Unique ID of shift
     */
    int ID;
    /**
     * Doctor working on this shift
     */
    Doctor doctor;
    /**
     * Localization of the shift
     */
    Localization localization;
    /**
     * Day of the week when the shift is
     */
    int dayOfWeek;
    /**
     * Full hour when the shift starts
     */
    int ShiftStart;
    /**
     * Full hour when the shift ends
     */
    int ShiftEnd;

    /**
     * Class constructor specifying doctor, localization, day of the week, hour of start and end of the shift
     * @param doctor - doctor working
     * @param localization - localization of shift
     * @param dayOfWeek - day of the week of shift, 1 - monday, ... , 6 - saturday
     * @param shiftStart - full hour of shift start
     * @param shiftEnd - full houst of shift end
     */
    public Shift(Doctor doctor, Localization localization, int dayOfWeek, int shiftStart, int shiftEnd) {
        this.doctor = doctor;
        this.localization = localization;
        this.dayOfWeek = dayOfWeek;
        ShiftStart = shiftStart;
        ShiftEnd = shiftEnd;
    }

    /**
     * Class constructor specifying ID, doctor, localization, day of the week, hour of start and end of the shift
     * @param ID - unique ID of shift
     * @param doctor - doctor working
     * @param localization - localization of shift
     * @param dayOfWeek - day of the week of shift, 1 - monday, ... , 6 - saturday
     * @param shiftStart - full hour of shift start
     * @param shiftEnd - full houst of shift end
     */
    public Shift(int ID, Doctor doctor, Localization localization, int dayOfWeek, int shiftStart, int shiftEnd) {
        this(doctor, localization, dayOfWeek, shiftStart,shiftEnd);
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getShiftStart() {
        return ShiftStart;
    }

    public void setShiftStart(int shiftStart) {
        ShiftStart = shiftStart;
    }

    public int getShiftEnd() {
        return ShiftEnd;
    }

    public void setShiftEnd(int shiftEnd) {
        ShiftEnd = shiftEnd;
    }

    /**
     * Provides information about shift
     * @return Readable description of shift
     */
    public String toString(){
        return getID()+" "+getDoctor()+" "+getLocalization()+" "+getDayOfWeek()+" "+getShiftStart()+" "+getShiftEnd();
    }
}


