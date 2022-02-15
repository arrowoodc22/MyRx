/*
    Person class
        Used to represent persons with prescriptions in the app; matches
        entities in persontable in MyRxDatabase.
 */

package com.example.MyRxUsingRooms;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "persontable")
public class Person {
    // Represents person entity from database.

    @PrimaryKey
    @NonNull
    //@ColumnInfo(name = "personID")
    private int personID;

    private String firstName;
    private String lastName;

    //@ColumnInfo(name = "DOB")
    private String DOB;

    public Person(@NonNull int personID, String firstName, String lastName, String DOB) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
    }

    public String toString(){
        return firstName;
    }

    // Setters
//    public void setFirstName(String fName)
//    {
//        firstName = fName;
//    }
//
//    public void setLastName(String lName)
//    {
//        lastName = lName;
//    }
//
//    public void setDOB(String DOB) {
//        dob = DOB;
//    }
    // Getters
    public int getPersonID() {
        return this.personID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getDOB() {
        return this.DOB;
    }
}
