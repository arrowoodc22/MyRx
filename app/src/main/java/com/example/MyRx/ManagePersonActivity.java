package com.example.MyRx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ManagePersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_person);
        // Added a Back Button.
        getSupportActionBar().setTitle("Manage Person");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}