package com.example.MyRxUsingRooms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PersonClassTests {

    @Test
    public void newPerson_Created() {
        Person testP = new Person(123456, "Boogie", "Arrowood", "");
        assertEquals(123456, testP.getPersonID());
        assertEquals("Boogie", testP.getFirstName());
        assertEquals("Arrowood", testP.getLastName());
    }
}