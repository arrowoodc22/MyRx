package com.example.MyRx;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.MyRx.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.MyRx.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Button addPersonButton;
    Button manageButton;
    Button editButton;
    Button addMedicationButton;
    Spinner dosageSpinner;
    Spinner quantitySpinner;
    Spinner frequencySpinner;
    private DBHandler dbHandler;

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

        // creating a new dbhandler class
        // and passing context to it.
        dbHandler = new DBHandler(MainActivity.this);
    }

//    public void doesAppHaveUser() {
//        if (getUserName() == true){
//            //;
//        }
//        else {
//            getUserName();
//            // place name into HomeFragment HomeMessage
//        };
//    }
//    public void getUserName(){
//        // Shared Preferences Editor
//        if (userName.exists()) {
//            //;
//        } else {
//            //;
//        }
//    }

    // On Click for Manage Fragment that allows Add Person Button to
    // launch New AddPerson Activity when clicked.
    public void addActivity(View view) {
        addPersonButton = findViewById(R.id.addPerson);
        addPersonButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
            startActivity(intent);

            // moved AddPerson code to AddPersonActivity
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

    public void selectAnExpirationDate(View view) {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResultExpiration(int year, int month, int day) {
        TextView dateTextView = findViewById(R.id.selectedDate);
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (" " + month_string + "/" + day_string + "/" + year_string);
        dateTextView.setText(dateMessage);
    }

    public void selectAPreviousRefillDate(View view) {
        DialogFragment newFragment2 = new DatePickerFragmentPrevious();
        newFragment2.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResultPrevious(int year, int month, int day) {
        TextView dateTextView = findViewById(R.id.selectedRefillDate);
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (" " + month_string + "/" + day_string + "/" + year_string);
        dateTextView.setText(dateMessage);
    }

    public void addMedication(View view) {
        // dosageSpinner located in AddActivity
        dosageSpinner = findViewById(R.id.spinnerDosage);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dosage_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dosageSpinner.setAdapter(adapter);

        // quantitySpinner located in AddActivity
        quantitySpinner = findViewById(R.id.spinnerQuantity);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(this,
                R.array.quantity_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        quantitySpinner.setAdapter(quantityAdapter);

        // frequencySpinner located in AddActivity
        frequencySpinner = findViewById(R.id.spinnerFrequency);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> frequencyAdapter = ArrayAdapter.createFromResource(this,
                R.array.frequency_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        frequencySpinner.setAdapter(frequencyAdapter);

        Medication med1 = new Medication();
        EditText mRxNumber = findViewById(R.id.medicationRx);
        med1.setMedicationRxNumber(Long.valueOf(mRxNumber.getText().toString()));
        EditText mName = findViewById(R.id.medicationName);
        med1.setMedicationName(mName.getText().toString());

        EditText mDosage = findViewById(R.id.medicationDosage);
        String dosSpinText = dosageSpinner.getSelectedItem().toString();
        String dosage = mDosage + " " + dosSpinText;
        med1.setMedicationDosage(dosage);

        EditText mQuantity = findViewById(R.id.medicationQuantity);
        String quanSpinText = quantitySpinner.getSelectedItem().toString();
        String quantity = mQuantity + " " + dosSpinText;
        med1.setMedicationCurrentQuantity(quantity);

        EditText mFrequency = findViewById(R.id.medicationFrequency);
        String freqSpinText = frequencySpinner.getSelectedItem().toString();
        String frequency = mFrequency + " " + freqSpinText;
        med1.setMedicationFrequency(frequency);

        EditText mRemainingRefills = findViewById(R.id.medicationRefillAmount);
        med1.setRemainingRefills(Integer.valueOf(mRemainingRefills.getText().toString()));
        TextView mPrevRefillDate = findViewById(R.id.selectedRefillDate);
        med1.setPreviousRefillDate(mPrevRefillDate.getText().toString());
        TextView mExpirationDate = findViewById(R.id.expirationDate);
        med1.setExpirationDate(mExpirationDate.getText().toString());
        EditText mDoctorName = findViewById(R.id.doctorName);
        med1.setDoctorName(mDoctorName.getText().toString());
        dbHandler.addNewMedication(med1.getMedicationRxNumber(), med1.getMedicationName(),
                med1.getMedicationDosage(), med1.getMedicationFrequency(),
                med1.getMedicationCurrentQuantity(), med1.getRemainingRefills(),
                med1.getPreviousRefillDate(), med1.getExpirationDate(), med1.getDoctorName());
        returnToHome();
    }

    public void returnToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}