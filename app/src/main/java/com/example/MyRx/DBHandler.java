package com.example.MyRx;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {
    // Creating database
    private static final String database_name = "myrxdb";
    // Initializing Database version
    private static final int database_version = 1;
    // Table 1: Person Table
        // Fields:
            // id
            // first name
            // last name
            // date of birth
    private static final String person_table = "persontable";
    // personID - first column of person table.
    private static final String id_col = "personid";
    // firstName - second column of person table.
    private static final String firstname_col = "firstname";
    // lastName - third column of person table.
    private static final String lastname_col = "lastname";
    // DOB - final column of person table.
    private static final String dob_col = "dateofbirth";

    // Table 2: Medication Table
    // Fields:
        // Rx Number
        // Medication Name
        // Medication Dosage
        // Medication Current Quantity
        // Previous Refill Date
        // Remaining Refills
        // Expiration Date
        // Doctor Name
    private static final String medication_table = "medicationtable";
    // rxNumber - ( PK ) first column of medication table.
    private static final String medicationrx_col = "medicationrx";
    // medicationName - second column of medication table.
    private static final String medicationname_col = "medicationname";
    // medicationDosage - third column of medication table.
    private static final String dosage_col = "medicationdosage";
    // currentQuantity - fourth column of medication table.
    private static final String currentquantity_col = "currentquantity";
    // previousRefillDate - fifth column of medication table.
    private static final String previousrefilldate_col = "previousrefilldate";
    // remainingRefills - fifth column of medication table.
    private static final String remainingrefills_col = "remainingrefills";
    // expirationDate - fifth column of medication table.
    private static final String expirationdate_col = "expirationdate";
    // doctorName - final column of medication table.
    private static final String doctorname_col = "doctorname";

    // Table 3: Notification Table
    // Fields:
        // rxID must match with medication rxNumber
        // medicationExpiredNotif
        // refillsLow
        // noRefillsNotif
    private static final String notification_table = "notificationtable";
    // rxID - ( PK ) first column of notification table.
    private static final String rxidnotif_col = "rxidnotification";
    // medicationExpiredNotif - second column of notification table.
    private static final String medicationexpirednotif_col = "medicationexpired";
    // refillsLow - fourth column of notification table.
    private static final String refillslownotif_col = "lowrefills";
    // noRefills - third column of notification table.
    private static final String norefills_col = "norefills";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String personQuery = "CREATE TABLE " + person_table + " ("
                + id_col + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + firstname_col + " TEXT, "
                + lastname_col + " TEXT, "
                + dob_col + " TEXT) ";

        String medicationQuery = "CREATE TABLE " + medication_table + " ("
                + medicationrx_col + " INTEGER PRIMARY KEY, "
                + medicationname_col + " TEXT, "
                + dosage_col + " TEXT, "
                + currentquantity_col + " TEXT, "
                + previousrefilldate_col + " TEXT, "
                + remainingrefills_col + " TEXT, "
                + expirationdate_col + " TEXT, "
                + doctorname_col + " TEXT) ";

        String notificationQuery = "CREATE TABLE " + notification_table + " ("
                + rxidnotif_col + " INTEGER PRIMARY KEY, "
                + medicationexpirednotif_col + " TEXT, "
                + refillslownotif_col + " TEXT, "
                + norefills_col + " TEXT) ";

        // execute queries below
        db.execSQL(personQuery);
        db.execSQL(medicationQuery);
        db.execSQL(notificationQuery);
    }

    // calling a writable method to write to our database for adding a new person.
    public void addNewPerson(String firstName, String lastName, String dob){
        SQLiteDatabase db = this.getWritableDatabase();

        // content values variable
        ContentValues personValues = new ContentValues();
        personValues.put(firstname_col, firstName);
        personValues.put(lastname_col, lastName);
        personValues.put(dob_col, dob);

        // inserting passed values into table.
        db.insert(person_table, null, personValues);

        // closing database after adding to database.
        db.close();
    }

    // calling a writable method to write to our database for adding a new medication.
    public void addNewMedication(Integer medicationRx, String medicationName, String dosage,
                                 Integer currentQuantity, String previousRefillDate,
                                 Integer remainingRefills, String expirationDate,
                                 String doctorName) {
        SQLiteDatabase db = this.getWritableDatabase();

        // content values variable
        ContentValues medicationValues = new ContentValues();
        medicationValues.put(medicationrx_col, medicationRx);
        medicationValues.put(medicationname_col, medicationName);
        medicationValues.put(dosage_col, dosage);
        medicationValues.put(currentquantity_col, currentQuantity);
        medicationValues.put(previousrefilldate_col, previousRefillDate);
        medicationValues.put(remainingrefills_col, remainingRefills);
        medicationValues.put(expirationdate_col, expirationDate);
        medicationValues.put(doctorname_col, doctorName);

        // inserting passed values into table.
        db.insert(medication_table, null, medicationValues);

        // closing database after adding to database.
        db.close();
    }

    // calling a writable method to write to our database for adding a new medication.
    public void addNotification(Integer rxID, String medicationExpired, String refillsLow, String noRefills) {
        SQLiteDatabase db = this.getWritableDatabase();

        // content values variable
        ContentValues notificationValues = new ContentValues();
        notificationValues.put(medicationexpirednotif_col, medicationExpired);
        notificationValues.put(refillslownotif_col, refillsLow);
        notificationValues.put(norefills_col, noRefills);

        // inserting passed values into table.
        db.insert(notification_table, null, notificationValues);

        // closing database after adding to database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + person_table + medication_table + notification_table);
        onCreate(db);
    }
}
