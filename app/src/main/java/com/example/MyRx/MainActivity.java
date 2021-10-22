package com.example.MyRx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.MyRx.ui.manage.ManageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.MyRx.databinding.ActivityMainBinding;

import PersonDBSchema.PersonBaseHelper;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Button addButton;
    Button manageButton;
    Button editButton;
    Spinner dosageSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        }

        // Function to allow medications to be taken from
        // AddActivity & shows/formatted in Home Page.
    public void addMedicationToScrollView(View view) {
        EditText mName = findViewById(R.id.medicationName);
        mName.getText();
        EditText mDosage = findViewById(R.id.medicationDosage);
        mDosage.getText();
        EditText mQuantity = findViewById(R.id.medicationQuantity);
        mQuantity.getText();
        EditText mRefill = findViewById(R.id.medicationRefillDate);
        mRefill.getText();

        // dosageSpinner located in AddActivity
        dosageSpinner = findViewById(R.id.spinnerDosage);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dosage_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dosageSpinner.setAdapter(adapter);
    }

    // On Click for Manage Fragment that allows Add Person Button to
    // launch New AddPerson Activity when clicked.
    public void addActivity(View view) {
        addButton = findViewById(R.id.addPerson);
        addButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
            startActivity(intent);
        });
    }

    // On Click for Manage Fragment that allows Manage Person Button to
    // launch New ManagePersonActivity when clicked.
    public void manageActivity(View view) {
        manageButton = findViewById(R.id.managePerson);
        manageButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, ManagePersonActivity.class);
            startActivity(intent);
        });
    }

    // On Click Listener for Edit Medications ( in AddActivity ) button allows button to
    // launch EditMedications when clicked.
    public void editActivity(View view) {
        editButton = findViewById(R.id.editMedications);
        editButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            startActivity(intent);
        });
    }
}