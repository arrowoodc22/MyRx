package com.example.MyRx.ui.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.MyRx.R;
import com.example.MyRx.databinding.FragmentManageBinding;

public class ManageFragment extends Fragment {

    private ManageViewModel manageViewModel;
    private FragmentManageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        manageViewModel =
                new ViewModelProvider(this).get(ManageViewModel.class);

        binding = FragmentManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//
//        final TextView textView = binding.textManage;
//        manageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}