/*
    MyRxViewModel
        Used to set up the viewModel & functions for the MyRxRepository.
 */

package com.example.MyRxUsingRooms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRxViewModel extends AndroidViewModel {
    private MyRxRepository myRxRepository;
    private final LiveData<List<Person>> allPersons;

    public MyRxViewModel (Application application) {
        super(application);
        myRxRepository = new MyRxRepository(application);
        allPersons = myRxRepository.getAllPersons();
    }

    LiveData<List<Person>> getAllPersons(){
        return allPersons;
    }

    public void insertPerson(Person person) {
        myRxRepository.insertPerson(person);
    }
}
