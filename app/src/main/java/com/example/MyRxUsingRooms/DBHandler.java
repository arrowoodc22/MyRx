package com.example.MyRxUsingRooms;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// REFACTOR STATIC FINAL (CONSTANT) NEED TO BE IN ALL CAPS.

public class DBHandler extends SQLiteOpenHelper {
    private static final String TAG = "inDBHandler";
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
    private static DBHandler dbInstance;
    // DOB - final column of person table.
    private final String dob_col = "dob";


    // Table 2: Medication Table
    // Fields:
        // Rx Number
        // Medication Name
        // Medication Dosage
        // Medication Current Quantity
        // Medication Frequency
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
    // medicationFrequency - fifth column of medication table.
    private static final String frequency_col = "frequency";
    // previousRefillDate - sixth column of medication table.
    private static final String previousrefilldate_col = "previousrefilldate";
    // remainingRefills - seventh column of medication table.
    private static final String remainingrefills_col = "remainingrefills";
    // expirationDate - eighth column of medication table.
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

    public static DBHandler getInstance(Context context){
        if (dbInstance == null){
            dbInstance = new DBHandler(context);
        }
        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String personQuery = "CREATE TABLE " + person_table + " ("
                + id_col + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + firstname_col + " TEXT, "
                + lastname_col + " TEXT, "
                + dob_col + " DATE) ";

        String medicationQuery = "CREATE TABLE " + medication_table + " ("
                + medicationrx_col + " INTEGER PRIMARY KEY, "
                + medicationname_col + " TEXT, "
                + dosage_col + " TEXT, "
                + currentquantity_col + " TEXT, "
                + frequency_col + "TEXT,"
                + previousrefilldate_col + " DATE, "
                + remainingrefills_col + " TEXT, "
                + expirationdate_col + " DATE, "
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
    public long addNewPerson(String fName, String lName, String dob){
        SQLiteDatabase db = this.getWritableDatabase();

        // content values variable
        ContentValues personValues = new ContentValues();
        personValues.put(firstname_col, fName);
        personValues.put(lastname_col, lName);
        personValues.put(dob_col, dob);

        // inserting passed values into table.
        long result = db.insert(person_table, null, personValues);

        if (result == -1) {
            Log.e(TAG, "Insertion to DB Failed.");
        }
        else {
            Log.e(TAG, fName + " " + lName + " Inserted.");
        }

        // closing database after adding to database.
        db.close();
        return result;
    }

    // calling a writable method to write to our database for adding a new medication.
    public long addNewMedication(Long medRxNumber, String medName, String medDosage,
                                 String medFrequency, String medQuantity, Integer medRemainingRefills,
                                 String medPrevRefillDate, String medExpirationDate,
                                 String medDoctorName) {
        SQLiteDatabase db = this.getWritableDatabase();

        // content values variable
        ContentValues medicationValues = new ContentValues();
        medicationValues.put(medicationrx_col, medRxNumber);
        medicationValues.put(medicationname_col, medName);
        medicationValues.put(dosage_col, medDosage);
        medicationValues.put(frequency_col, medFrequency);
        medicationValues.put(currentquantity_col, medQuantity);
        medicationValues.put(remainingrefills_col, medRemainingRefills);
        medicationValues.put(previousrefilldate_col, medPrevRefillDate);
        medicationValues.put(expirationdate_col, medExpirationDate);
        medicationValues.put(doctorname_col, medDoctorName);

        // inserting passed values into table.
        long medResult = db.insert(medication_table, null, medicationValues);

        if (medResult == -1) {
            Log.e(TAG, "Medication Insertion to DB Failed.");
        }
        else {
            Log.e(TAG, "Medication: " + medName + " Inserted.");
        }

        // closing database after adding to database.
        db.close();
        return medResult;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + person_table + medication_table + notification_table);
        onCreate(db);
    }

    long addMedicationToDatabase(Medication medication) {
        return addNewMedication(medication.getMedicationRxNumber(), medication.getMedicationName(), medication.getMedicationDosage(),
                medication.getMedicationFrequency(),medication.getMedicationCurrentQuantity(), medication.getRemainingRefills(),
                medication.getPreviousRefillDate(), medication.getExpirationDate(), medication.getDoctorName());
    }
}
