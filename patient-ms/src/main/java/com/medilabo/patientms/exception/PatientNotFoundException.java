package com.medilabo.patientms.exception;

/**
 * PatientNotFoundException in Patient microservice (Médilabo)
 */
public class PatientNotFoundException extends Exception {

	public PatientNotFoundException(String message) {
		super(message);
	}
}
