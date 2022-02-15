package com.example.MyRxUsingRooms;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
class PersonDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private PersonDao personDao;
    private MyRxDatabase myRxDb;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        myRxDb = Room.inMemoryDatabaseBuilder(context, MyRxDatabase.class)
                .allowMainThreadQueries()
                .build();
        personDao = myRxDb.personDao();
    }

    @After
    public void closeDb() {
        myRxDb.close();
    }

    @Test
    public void insertAndGetPerson() throws Exception {
        int personId = 123456;
        Person testP = new Person(123456, "Boogie", "Arrowood", "");
        personDao.insert(testP);
        Person newP = personDao.getOnePerson("Boogie");
        Assert.assertEquals(123456, newP.getPersonID());
        Assert.assertEquals("Boogie", newP.getFirstName());
        Assert.assertEquals("Arrowood", newP.getLastName());
    }
}
