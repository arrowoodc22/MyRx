package com.example.MyRxUsingRooms.ui.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.MyRxUsingRooms.R;
import com.example.MyRxUsingRooms.databinding.FragmentAddBinding;

public class AddFragment extends Fragment {

    private AddViewModel searchViewModel;
    private FragmentAddBinding binding;
    Spinner dosageSpinner;
    Spinner quantitySpinner;
    Spinner frequencySpinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initializeDosageSpinner(root);
        initializeFrequencySpinner(root);
        initializeQuantitySpinner(root);
        return root;
    }

    public void initializeDosageSpinner(View view) {
        dosageSpinner = view.findViewById(R.id.spinnerDosage);
        ArrayAdapter<CharSequence> dosageAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.dosage_type, android.R.layout.simple_spinner_item);
        dosageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dosageSpinner.setAdapter(dosageAdapter);
    }

    public void initializeFrequencySpinner(View view) {
        frequencySpinner = view.findViewById(R.id.spinnerFrequency);
        ArrayAdapter<CharSequence> frequencyAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.frequency_type, android.R.layout.simple_spinner_item);
        frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequencySpinner.setAdapter(frequencyAdapter);
    }

    public void initializeQuantitySpinner(View view) {
        quantitySpinner = view.findViewById(R.id.spinnerQuantity);
        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.quantity_type, android.R.layout.simple_spinner_item);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}