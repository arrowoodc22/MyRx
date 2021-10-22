package PersonDBSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PersonDatabase {
    private static PersonDatabase sPerson;

    // private List<PersonInformation> mPerson;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private PersonDatabase(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new PersonBaseHelper(mContext).getWritableDatabase();
        // mPerson = new ArrayList<>();
    }
}
