package com.medilabo.patientms.model;

import java.time.LocalDate;

import com.medilabo.patientms.constant.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Patient {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "patient_id")
private int id;

@Column(name = "firstname")
@NotBlank(message = "Firstname is mandatory")
@NotNull
@Size(max = 50, message = "Maximum of {max} characters")
private String firstname;

@Column(name = "lastname")
@NotBlank(message = "Lastname is mandatory")
@NotNull
@Size(max = 50, message = "Maximum of {max} characters")
private String lastname;

@Column(name = "birthdate")
@NotNull(message = "Birthdate is mandatory")
private LocalDate birthdate;

@Column(name = "gender")
@Enumerated(EnumType.STRING)
@NotNull(message = "Gender is mandatory")
private Gender gender;

@Size(max = 100, message = "Maximum of {max} characters")
@Column(name = "postal_address")
private String postalAddress;

@Column(name = "phone_number")
@Pattern(regexp = "([0-9]+(-[0-9]+)+)", message ="Phone number format is 000-000-0000")
private String phoneNumber;
	
	
	
}
