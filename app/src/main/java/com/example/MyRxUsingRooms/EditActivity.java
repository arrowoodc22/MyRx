/*
    Edit class
        Used to allow a user to edit existing medications information if
        information has changed.
 */

package com.example.MyRxUsingRooms;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        // Added a Back Button.
        getSupportActionBar().setTitle("Edit Medications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}