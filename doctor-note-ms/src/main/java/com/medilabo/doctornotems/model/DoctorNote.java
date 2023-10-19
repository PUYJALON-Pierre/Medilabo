package com.medilabo.doctornotems.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Document(collection = "doctor_notes")
@Data
@AllArgsConstructor
public class DoctorNote {
	
	@Id
	private String noteId;
	
	@NotNull(message = "Patient ID is required")
	private int patientId;
	
	private String noteContent;
	
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private LocalDate date = LocalDate.now();
	

	

}
