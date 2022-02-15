package com.example.MyRxUsingRooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.MyRxUsingRooms.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Button addPersonButton;
    Button manageButton;
    Button editButton;
    Spinner dosageSpinner;
    Spinner quantitySpinner;
    Spinner frequencySpinner;
    private MyRxViewModel myRxViewModel;
    public static final int NEW_PERSON_ACTIVITY_REQUEST_CODE = 10;

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
        myRxViewModel = new ViewModelProvider(this).get(MyRxViewModel.class);
    }

    public void addActivity(View view) {
        addPersonButton = findViewById(R.id.addPerson);
        addPersonButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
            startActivity(intent);
        });
    }

    public void manageActivity(View view) {
        manageButton = findViewById(R.id.managePerson);
        manageButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, ManagePersonActivity.class);
            startActivity(intent);
        });
    }

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
        Medication medication = gatherMedicationInformation();
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int intentType;

        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                intentType = extras.getInt("intentType");
                if (intentType == 10) {
                    int id = extras.getInt("id");
                    String fName = extras.getString("fName");
                    String lName = extras.getString("lName");
                    String dob = extras.getString("dob");
                    Person p = new Person(id, fName, lName, dob);
                    myRxViewModel.insertPerson(p);
                }
            }
        }
    }
}