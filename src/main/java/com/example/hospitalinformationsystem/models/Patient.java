package com.example.hospitalinformationsystem.models;

public class Patient {
    private String patientNumber;
    private String name;
    private String surname;
    private String address;
    private String telephoneNumber;

    public Patient(String patientNumber, String name, String surname, String address, String telephoneNumber) {
        this.patientNumber = patientNumber;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    // Getters and setters
    public String getPatientNumber() { return patientNumber; }
    public void setPatientNumber(String patientNumber) { this.patientNumber = patientNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTelephoneNumber() { return telephoneNumber; }
    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }
}
