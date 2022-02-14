package com.example.MyRxUsingRooms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Random;

public class AddPersonActivity extends AppCompatActivity {
//    private DBHandler dbHandler;
    private static final String TAG = "inAddPersonActivity";
    private static final int UPPERBOUND = 10000;
    private static final int FROMADDPERSON = 10;

    // Created Add Person Class to allow user to Add Person to Home Page.
    // This page will allow ask user to input First Name, Last Name,
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        // Added a Back Button.
        getSupportActionBar().setTitle("Add Person");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // dbHandler = DBHandler.getInstance(getApplicationContext());
    }

    public void addPerson(View view) {
        // Insert code to be able to access AddPersonActivity information
        Log.d(TAG, "Starting addPerson");

        //dbHandler.addNewPerson(p1.getFirstName(), p1.getLastName(), p1.getDOB());
        Random rand = new Random();
        int id = rand.nextInt(UPPERBOUND);

        EditText first = findViewById(R.id.firstName);
        String fName = first.getText().toString();
        Log.d(TAG, "First name is: " + fName);

        EditText last = findViewById(R.id.lastName);
        String lName = last.getText().toString();
        Log.d(TAG, "Last name is: " + lName);

        TextView dobText = findViewById(R.id.selectedBirthDate);
        String dob = dobText.toString();
        Log.d(TAG, "DOB is: " + dob);
        Log.d(TAG, "Created new person");
        Log.d(TAG, "Adding person to database");

        // Creating intent
        Intent intent = new Intent(AddPersonActivity.this, MainActivity.class);
        // Insert Extra Message Here.
        intent.putExtra("intentType", FROMADDPERSON);
        intent.putExtra("id", id);
        intent.putExtra("firsName", fName);
        intent.putExtra("lasName", lName);
        intent.putExtra("dob", dob); // change to dob.toString();
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    public void selectADateofBirth(View view) {
        DialogFragment newFragment3 = new DatePickerFragmentBirth();
        newFragment3.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResultBirth(int year, int month, int day) {
        TextView birthTextView = findViewById(R.id.selectedBirthDate);
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (" " + month_string + "/" + day_string + "/" + year_string);
        birthTextView.setText(dateMessage);
    }
}