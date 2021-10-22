package PersonDBSchema;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonBaseHelper extends SQLiteOpenHelper {
    // Database name variable
    private static final String DATABASE_NAME = "persondb";
    // Database version
    private static final int VERSION = 1;

    // COLUMNS
    // Table Name for PersonDataBase
    public static final String TABLE_NAME = "Person Table";
    // Database ID column
    public static final String ID = "id";
    // Database First Name of person column
    public static final String FIRST = "first";
    // Database Last Name of person column
    public static final String LAST = "last";

    public PersonBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
//
    @Override
    public void onCreate(SQLiteDatabase db) {
//        // Setting column names along with their data types
//        String query = "CREATE TABLE " + TABLE_NAME + " (" +
//                ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                FIRST + "TEXT, " +
//                LAST + "TEXT)";
//        // Executes above query
//        db.execSQL(query);
    }
//
//    public void addPerson(String first, String last) {
//        // Variable for SQLite Database that calls a writable method as needed to write to database
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // Variable for content values
//        ContentValues values = new ContentValues;
//        values.put(FIRST, first);
//        values.put(LAST, last);
//
//        // Pass added content values to table
//        db.insert(TABLE_NAME, null, values);
//
//        // Close database after adding values
//        db.close();
//    }
//
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        // Method below is called to checkk if the table exists already
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
    }
}
