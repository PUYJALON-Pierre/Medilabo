package com.medilabo.clientui.beans;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Model class for DoctorNoteBean in client-ui (Front-end service of Medilabo)
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorNoteBean {

	private String noteId;

	@NotNull(message = "Patient ID is required")
	private int patientId;

	private String noteContent;

	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private LocalDate date;
}
