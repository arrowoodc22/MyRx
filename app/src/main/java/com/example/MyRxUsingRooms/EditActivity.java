package com.example.MyRxUsingRooms;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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