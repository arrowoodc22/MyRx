package com.example.MyRx;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddPersonActivity extends AppCompatActivity {

    // Created Add Person Class to allow user to Add Person to Home Page.
    // This page will allow ask user to input First Name, Last Name,
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        // Added a Back Button.
        getSupportActionBar().setTitle("Add Person");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}