/*
    AddPersonActivity class
        Used to allow a user to add a person to their app.
 */

package com.example.MyRxUsingRooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Random;

public class AddPersonActivity extends AppCompatActivity {
    private static final String TAG = "inAddPersonActivity";
    private static final int UPPERBOUND = 10000;
    private static final int FROMADDPERSON = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        // Added a Back Button.
        getSupportActionBar().setTitle("Add Person");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void addPerson(View view) {

        Random rand = new Random();
        int id = rand.nextInt(UPPERBOUND);

        EditText first = findViewById(R.id.firstName);
        String fName = first.getText().toString();

        EditText last = findViewById(R.id.lastName);
        String lName = last.getText().toString();

        TextView dobText = findViewById(R.id.selectedBirthDate);
        String dob = dobText.toString();

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