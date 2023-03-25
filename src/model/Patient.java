package model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represent's patient person
 * Inherits after Person class
 */
public class Patient extends Person {
    /**
     * ID of the user account bonded to patient
     */
    private int userID;
    /**
     * Date of birth of patient
     */
    private LocalDate birthDate;

    /**
     * Generates patient object through interface with user, for console application only.
     * As static function it needs userID to be created as parameter. Can be used in console application for registration
     * @param userID - user ID of new patient
     * @return Patient with fields specified by user inputs
     */
    public static Patient generatePatient(int userID){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Imię: ");
        var firstname = scanner.next();
        System.out.println("Nazwisko: ");
        var lastname = scanner.next();
        System.out.println("Płeć [m/f]: ");
        var gender = scanner.next();
        System.out.println("Data urodzenia DD-MM-YYYY");
        var dateList = Arrays.stream(scanner.next().split("-")).map(Integer::parseInt).toList();
        var localDate = LocalDate.of(dateList.get(2), dateList.get(1), dateList.get(0));

        return new Patient(userID, firstname, lastname, gender, localDate);
    }

    /**
     * Class constructor specifying ID of user, first name, last name, gender and date of birth of patient.
     * @param userID - ID of user, patient is connected to user account
     * @param firstName - first name of patient
     * @param lastName - last name of patient
     * @param gender - gender of patient can be: 'm' and 'f'
     * @param birthDate - LocalDate as date of birth
     */
    public Patient(int userID, String firstName, String lastName, String gender, LocalDate birthDate) {
        super(firstName, lastName, gender);
        this.userID = userID;
        this.birthDate = birthDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gives information about patient
     * @return String with needed inforamtion about patient
     */
    public String toString(){
        return getUserID()+" "+getFirstName()+" "+getLastName()+" "+getGender()+" "+getBirthDate();
    }
}
