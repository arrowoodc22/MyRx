package com.example.MyRx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    private void returnToHomePage() {
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(intent);
    }
}