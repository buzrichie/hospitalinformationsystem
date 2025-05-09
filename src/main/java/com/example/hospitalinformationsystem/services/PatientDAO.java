package com.example.hospitalinformationsystem.services;

import com.example.hospitalinformationsystem.models.Patient;
import com.example.hospitalinformationsystem.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patient (PatientNumber, Name, Surname, Address, TelephoneNumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getPatientNumber());
            stmt.setString(2, patient.getName());
            stmt.setString(3, patient.getSurname());
            stmt.setString(4, patient.getAddress());
            stmt.setString(5, patient.getTelephoneNumber());

            stmt.executeUpdate();
            System.out.println("Patient added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patient";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientNumber(rs.getString("PatientNumber"));
                patient.setName(rs.getString("Name"));
                patient.setSurname(rs.getString("Surname"));
                patient.setAddress(rs.getString("Address"));
                patient.setTelephoneNumber(rs.getString("TelephoneNumber"));

                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE Patient SET Name=?, Surname=?, Address=?, TelephoneNumber=? WHERE PatientNumber=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getSurname());
            stmt.setString(3, patient.getAddress());
            stmt.setString(4, patient.getTelephoneNumber());
            stmt.setString(5, patient.getPatientNumber());

            stmt.executeUpdate();
            System.out.println("Patient updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(String patientNumber) {
        String sql = "DELETE FROM Patient WHERE PatientNumber=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patientNumber);
            stmt.executeUpdate();
            System.out.println("Patient deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

