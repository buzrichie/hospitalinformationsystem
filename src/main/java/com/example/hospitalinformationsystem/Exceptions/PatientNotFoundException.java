package com.example.hospitalinformationsystem.Exceptions;

public class PatientNotFoundException extends RuntimeException {
  public PatientNotFoundException(String message) {
    super(message);
  }
}
