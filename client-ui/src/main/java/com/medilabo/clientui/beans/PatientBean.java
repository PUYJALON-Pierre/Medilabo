package com.medilabo.clientui.beans;

import java.time.LocalDate;

import org.springframework.lang.Nullable;

import com.medilabo.clientui.constant.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Model class for PatientBean in client-ui (Front-end service of Medilabo)
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {

	private int id;

	@NotBlank(message = "Firstname is mandatory")
	@NotNull
	@Size(max = 50, message = "Maximum of {max} characters")
	private String firstName;
	
	@NotBlank(message = "Lastname is mandatory")
	@NotNull
	@Size(max = 50, message = "Maximum of {max} characters")
	private String lastName;

	@NotNull(message = "Birthdate is mandatory")
	private LocalDate birthdate;

	@NotNull(message = "Gender is mandatory")
	private Gender gender;

	@Size(max = 100, message = "Maximum of {max} characters")
	private String postalAddress;


	@Pattern(regexp = "^$|^\\d{3}-\\d{3}-\\d{4}$", message = "Phone number format is 000-000-0000 or empty")
	private String phoneNumber;

	

}
