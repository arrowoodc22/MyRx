package com.example.MyRx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddPersonActivity extends AppCompatActivity {
    private DBHandler dbHandler;
    private static final String TAG = "inAddPersonActivity";

    // Created Add Person Class to allow user to Add Person to Home Page.
    // This page will allow ask user to input First Name, Last Name,
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        // Added a Back Button.
        getSupportActionBar().setTitle("Add Person");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHandler = DBHandler.getInstance(getApplicationContext());
    }

    public void addPerson(View view) {
        // Insert code to be able to access AddPersonActivity information
        Person p1 = new Person();
        EditText first = findViewById(R.id.firstName);
        p1.setFirstName(first.getText().toString());
        EditText last = findViewById(R.id.lastName);
        p1.setLastName(last.getText().toString());
        TextView dob = findViewById(R.id.selectedBirthDate);
        p1.setDOB(dob.toString());
        dbHandler.addNewPerson(p1.getFirstName(), p1.getLastName(), p1.getDOB());
        returnToHomePage();
    }

    private void returnToHomePage() {
        Intent intent = new Intent(AddPersonActivity.this, MainActivity.class);
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