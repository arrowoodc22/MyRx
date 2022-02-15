/*
    Medication class
        Used to represent required information for the medications a person wants to enter.
 */

package com.example.MyRxUsingRooms;

public class Medication {
    Long rxNumber;
    String medicationName;
    String medicationDosage;
    String medicationFrequency;
    String medicationCurrentQuantity;
    Integer remainingRefills;
    String previousRefillDate;
    String expirationDate;
    String doctorName;

    // Setters
    public void setMedicationRxNumber(Long RxNum) {
        rxNumber = RxNum;
    }

    public void setMedicationName(String rxName) {
        medicationName = rxName;
    }

    public void setMedicationDosage(String medDosage) {
        medicationDosage = medDosage;
    }

    public void setMedicationFrequency(String medFrequency) {
        medicationFrequency = medFrequency;
    }

    public void setMedicationCurrentQuantity(String medCurrentQuantity) {
        medicationCurrentQuantity = medCurrentQuantity;
    }

    public void setRemainingRefills(Integer remRefills) {
        remainingRefills = remRefills;
    }

    public void setPreviousRefillDate(String prevRefillDate) {
        previousRefillDate = prevRefillDate;
    }

    public void setExpirationDate(String expDate) {
        expirationDate = expDate;
    }

    public void setDoctorName(String docName) {
        doctorName = docName;
    }

    // Getters
    public Long getMedicationRxNumber() {
        return rxNumber;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getMedicationDosage() {
        return medicationDosage;
    }

    public String getMedicationFrequency() {
        return medicationFrequency;
    }

    public String getMedicationCurrentQuantity() {
        return medicationCurrentQuantity;
    }

    public Integer getRemainingRefills() {
        return remainingRefills;
    }

    public String getPreviousRefillDate() {
        return previousRefillDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getDoctorName() {
        return doctorName;
    }
}
