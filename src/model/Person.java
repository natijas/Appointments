package model;

/**
 * Class representing a person.
 * It represents an abstract person, consisting a name, surname and gender.
 */
public abstract class Person {
        /**
         * First name of person
         */
        private String firstName;
        /**
         * Last name of person
         */
        private String lastName;
        /**
         * Gender of a person, can be male or female
         */
        private String gender;

        /**
         * Class constructor specifying first name, last name and gender
         * @param firstName
         * @param lastName
         * @param gender
         */
        public Person(String firstName, String lastName, String gender) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.gender = gender;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getGender() {
                return gender;
        }

        public void setGender(String gender) {
                this.gender = gender;
        }
}
