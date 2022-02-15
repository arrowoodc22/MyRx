package com.example.MyRxUsingRooms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("Select * FROM persontable ")
    LiveData<List<Person>> getAllPersons();

    @Query("Select * FROM persontable WHERE firstName = :firstName")
    Person getOnePerson(String firstName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Person p);

    @Query("DELETE FROM persontable ")
    void deleteAll();
}
