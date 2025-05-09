package com.example.hospitalinformationsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloApplication extends Application {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/HospitalInformationSystem";
    private final String username = "root";
    private final String password = "root";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        try {
////            Connection connection = DriverManager.getConnection(this.jdbcURL, this.username, password);
//            System.out.println("Connected to MySQL successfully!");
////            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Connection failed!");
//            e.printStackTrace();
//        }

//        launch();
    }
}