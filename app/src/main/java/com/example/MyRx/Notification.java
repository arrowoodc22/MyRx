package com.example.MyRx;

public class Notification {

    // Table 3: Notification Table
    // Fields:
    // rxID must match with medication rxNumber
    // medicationExpiredNotif
    // refillsLow
    // noRefillsNotif
    String medicationExpired;
    String refillsLow;
    String noRefills;

    // make getter & setter for each field.
    // after make a TextView to display information.
    Notification notification = new Notification();

    // Setters
    public void setMedicationExpired(String medExpiredNotif) {
        medicationExpired = medExpiredNotif;
    }

    public void setRefillsLow(String refLow) {
        refillsLow = refLow;
    }

    public void setnoRefills(String noRefs) {
        noRefills = noRefs;
    }

    // Getters
    public String getMedicationExpired() {
        return medicationExpired;
    }

    public String getRefillsLow() {
        return refillsLow;
    }

    public String getNoRefills() {
        return noRefills;
    }
}
