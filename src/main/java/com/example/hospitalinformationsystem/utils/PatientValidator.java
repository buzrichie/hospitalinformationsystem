package com.example.hospitalinformationsystem.utils;

import com.example.hospitalinformationsystem.models.Patient;

public class PatientValidator {
    public static boolean isValid(Patient patient) {
        if (patient == null) return false;
        if (patient.getPatientNumber() == null || patient.getPatientNumber().isBlank()) return false;
        if (patient.getName() == null || patient.getName().isBlank()) return false;
        if (patient.getSurname() == null || patient.getSurname().isBlank()) return false;
        if (patient.getAddress() == null || patient.getAddress().isBlank()) return false;
        if (patient.getTelephoneNumber() == null || !patient.getTelephoneNumber().matches("\\d{10,15}")) return false;

        return true;
    }
}
