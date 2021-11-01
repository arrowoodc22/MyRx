package com.example.MyRx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);
        }

    private String displayAddedMedicationtoHomePage() {
        EditText med = findViewById(R.id.medicationName);
        return "Medication, " + med.getText() + " added to Home Page.";
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

        // dosageSpinner located in AddActivity
        dosageSpinner = findViewById(R.id.spinnerDosage);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dosage_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dosageSpinner.setAdapter(adapter);

        Button addMedicationButton = findViewById(R.id.addMedication);
        addMedicationButton.setOnClickListener(viewMed -> {
            displayAddedMedicationtoHomePage();
        });

    }

    // On Click for Manage Fragment that allows Add Person Button to
    // launch New AddPerson Activity when clicked.
    public void addActivity(View view) {
        addPersonButton = findViewById(R.id.addPerson);
        addPersonButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
            startActivity(intent);

            // Insert code to be able to access AddPersonActivity information
            EditText first = findViewById(R.id.firstName);
            EditText last = findViewById(R.id.lastName);
            String firstName = first.getText().toString();
            String lastName = last.getText().toString();
            EditText dateOfBirth = findViewById(R.id.dateOfBirth);
            String dob = dateOfBirth.getText().toString();

            if(firstName.isEmpty() && lastName.isEmpty() && dob.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter all data in addPerson in Manage tab.", Toast.LENGTH_SHORT).show();
                return;
            }

            // add a new person to SQLite Data and pass all values to it.
            dbHandler.addNewPerson(firstName, lastName, dob);

            // Send information to Home Page
            String home = findViewById(R.id.text_home).toString();

            // Below line will make the welcome message of Home Page display
                // Hello, firstName your medications are listed below.
            // home + ", " + firstName + " your medications are listed below.";
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
}