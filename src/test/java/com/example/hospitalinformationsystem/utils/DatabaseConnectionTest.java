package com.example.hospitalinformationsystem.utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void getConnection() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            assertNotNull(connection, "Connection should not be null");
            assertFalse(connection.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("Connection test failed with exception: " + e.getMessage());
        }
    }

    @Test
    void closeConnection() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            assertNotNull(connection);

            connection.close();
            assertTrue(connection.isClosed(), "Connection should be closed");
        } catch (SQLException e) {
            fail("Close connection test failed with exception: " + e.getMessage());
        }
    }
}
