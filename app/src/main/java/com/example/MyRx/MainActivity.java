package com.example.MyRx;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
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
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        dbHandler = new DBHandler(MainActivity.this);
    }

    // On Click for Manage Fragment that allows Add Person Button to
    // launch New AddPerson Activity when clicked.
    public void addActivity(View view) {
        addPersonButton = findViewById(R.id.addPerson);
        addPersonButton.setOnClickListener(view1 -> {
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
        Log.d("TAG", "Entered Add Medication Function.");
        Medication medication = gatherMedicationInformation();
        Log.d("TAG", "Gathered medication info, now adding to Database...");
        dbHandler.addMedicationToDatabase(medication);
        Log.d("TAG", "Added medication to Database.");
        returnToHome();
    }

    @NonNull
    private Medication gatherMedicationInformation() {
        Medication medication = new Medication();
        medication.setMedicationRxNumber(createRxNumber());
        medication.setMedicationName(getRxName());
        medication.setMedicationDosage(getDosage());
        medication.setMedicationFrequency(getFrequency());
        medication.setMedicationCurrentQuantity(getQuantity());
        medication.setRemainingRefills(getRemainingRefills());
        medication.setPreviousRefillDate(getPreviousRefillDate());
        medication.setExpirationDate(getExpirationDate());
        medication.setDoctorName(getDoctorName());
        return medication;
    }

    @NonNull
    private Long createRxNumber() {
        EditText mRxNumber = findViewById(R.id.medicationRx);
        Long rxNum = Long.valueOf(mRxNumber.getText().toString());
        return rxNum;
    }

    @NonNull
    private String getRxName() {
        return this.<EditText>findViewById(R.id.medicationName).getText().toString();
    }

    @NonNull
    private String getDosage() {
        return this.<EditText>findViewById(R.id.medicationDosage).getText().toString() + " " + dosageSpinner.getSelectedItem().toString();
    }

    @NonNull
    private String getFrequency() {
        return this.<EditText>findViewById(R.id.medicationFrequency).getText().toString() + " " + frequencySpinner.getSelectedItem().toString();
    }

    @NonNull
    private String getQuantity() {
        return this.<EditText>findViewById(R.id.medicationQuantity).getText().toString() + " " + quantitySpinner.getSelectedItem().toString();
    }

    @NonNull
    private Integer getRemainingRefills() {
        return Integer.valueOf(this.<EditText>findViewById(R.id.medicationRefillAmount).toString());
    }

    @NonNull
    private String getPreviousRefillDate() {
        return this.<TextView>findViewById(R.id.previousRefillDate).toString();
    }

    @NonNull
    private String getExpirationDate() {
        return this.<TextView>findViewById(R.id.expirationDate).toString();
    }

    @NonNull
    private String getDoctorName() {
        return this.<EditText>findViewById(R.id.doctorName).getText().toString();
    }

    public void returnToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}