package com.example.hospitalinformationsystem.services;
import com.example.hospitalinformationsystem.models.Patient;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientDAOTest {

    private PatientDAO patientDAO;
    private Patient testPatient;

    @BeforeEach
    void setUp() {
        patientDAO = new PatientDAO();
        testPatient = new Patient("TEST123", "Test", "Patient", "123 Test Lane", "0500000000");
        patientDAO.deletePatient("TEST123"); // Ensure clean state
    }

    @Test
    void addPatient() {
        boolean added = patientDAO.addPatient(testPatient);
        assertTrue(added, "Patient should be added successfully");

        Patient fetched = patientDAO.getPatientById("TEST123");
        assertNotNull(fetched);
        assertEquals("Test", fetched.getName());
    }

    @Test
    void getAllPatients() {
        patientDAO.addPatient(testPatient);

        List<Patient> patients = patientDAO.getAllPatients();
        assertNotNull(patients);
        assertTrue(patients.stream().anyMatch(p -> "TEST123".equals(p.getPatientNumber())));
    }

    @Test
    void updatePatient() {
        patientDAO.addPatient(testPatient);

        testPatient.setAddress("456 Updated Lane");
        boolean updated = patientDAO.updatePatient(testPatient);
        assertTrue(updated, "Patient update should be successful");

        Patient updatedPatient = patientDAO.getPatientById("TEST123");
        assertEquals("456 Updated Lane", updatedPatient.getAddress());
    }

    @Test
    void deletePatient() {
        patientDAO.addPatient(testPatient);

        boolean deleted = patientDAO.deletePatient("TEST123");
        assertTrue(deleted, "Patient should be deleted");

        Patient fetched = patientDAO.getPatientById("TEST123");
        assertNull(fetched, "Deleted patient should not be found");
    }
}
