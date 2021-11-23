package com.example.MyRx;

public class Person {
    // Mirror of Person in Database.
    // Make a corresponding Medication & Notification Class.

    // Table 1: Person Table
    // Fields:
    // id
    // first name
    // last name
    // date of birth
    Long id;
    String firstName;
    String lastName;
    String dob;

    // make getter & setter for each field.
    // after make a TextView to display information.
    public Person() {
        firstName = "";
        lastName = "";
        dob = "00/00/0000";
    }

    public String toString(){
        return firstName;
    }

    // Setters
    public void setID(Long ID) {
        id = ID;
    }

    public void setFirstName(String fName) {
        firstName = fName;
    }

    public void setLastName(String lName) {
        lastName = lName;
    }

    public void setDOB(String DOB) {
        dob = DOB;
    }

    // Getters
    public Long getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDOB() {
        return dob;
    }
}
