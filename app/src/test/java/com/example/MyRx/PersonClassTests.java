package com.example.MyRx;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PersonClassTests {

    @Test
    public void newPerson_Created() {
        Person testP = new Person();
        testP.setFirstName("Boogie");
        testP.setLastName("Arrowood");

        assertEquals("Boogie", testP.getFirstName());
        assertEquals("Arrowood", testP.getLastName());
    }
}