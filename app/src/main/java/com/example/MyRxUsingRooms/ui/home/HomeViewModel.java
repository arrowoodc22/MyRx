package com.example.MyRxUsingRooms.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> sText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Hello, Courtney! Your prescriptions are listed below.");

        sText = new MutableLiveData<>();
        sText.setValue("Here will be your listed prescriptions.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}