package com.example.MyRxUsingRooms;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRxRepository {
    //Holds info from queries
    private PersonDao personDao;
    private LiveData<List<Person>> allPersons;

    MyRxRepository(Application application) {
        MyRxDatabase db = MyRxDatabase.getDatabase(application);
        personDao = db.personDao();
        allPersons = personDao.getAllPersons();

    }

    LiveData<List<Person>> getAllPersons() {
        return allPersons;
    }

    Person getOnePerson(String firstName) {
        return personDao.getOnePerson(firstName);
    }

    void insertPerson(Person p) {
        MyRxDatabase.databaseWriteExecutor.execute(() -> {personDao.insert(p);
        });
    }
}
