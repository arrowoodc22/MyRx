/*
    ManagePerson class
        Used to let a person manage added persons to their app.
 */

package com.example.MyRxUsingRooms;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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