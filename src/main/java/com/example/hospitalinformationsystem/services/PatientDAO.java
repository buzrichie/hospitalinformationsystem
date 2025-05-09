package com.example.hospitalinformationsystem.services;

import com.example.hospitalinformationsystem.Exceptions.PatientNotFoundException;
import com.example.hospitalinformationsystem.models.Patient;
import com.example.hospitalinformationsystem.utils.DatabaseConnection;
import com.example.hospitalinformationsystem.utils.PatientValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public boolean addPatient(Patient patient) {
        String sql = "INSERT INTO Patient (PatientNumber, Name, Surname, Address, TelephoneNumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (!PatientValidator.isValid(patient)) {
                throw new IllegalArgumentException("Invalid patient data");
            }

            stmt.setString(1, patient.getPatientNumber());
            stmt.setString(2, patient.getName());
            stmt.setString(3, patient.getSurname());
            stmt.setString(4, patient.getAddress());
            stmt.setString(5, patient.getTelephoneNumber());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Patient added successfully.");
            return rowsAffected > 0;

        }catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Error: Patient with this PatientNumber already exists.");
            return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
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

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE Patient SET Name=?, Surname=?, Address=?, TelephoneNumber=? WHERE PatientNumber=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            Patient spatient = getPatientById(patient.getPatientNumber());
            if (spatient == null) {throw new PatientNotFoundException("Patient "+ patient.getPatientNumber()+" not found.");}
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getSurname());
            stmt.setString(3, patient.getAddress());
            stmt.setString(4, patient.getTelephoneNumber());
            stmt.setString(5, patient.getPatientNumber());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Patient updated successfully.");
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePatient(String patientNumber) {
        String sql = "DELETE FROM Patient WHERE PatientNumber=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            Patient patient = getPatientById(patientNumber);
            if (patient == null) {throw new PatientNotFoundException("Patient "+ patientNumber+" not found.");}
            stmt.setString(1, patientNumber);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Patient deleted successfully.");
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Patient getPatientById(String patientNumber) {
        String sql = "SELECT * FROM Patient WHERE PatientNumber=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patientNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                            rs.getString("PatientNumber"),
                            rs.getString("Name"),
                            rs.getString("Surname"),
                            rs.getString("Address"),
                            rs.getString("TelephoneNumber")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
