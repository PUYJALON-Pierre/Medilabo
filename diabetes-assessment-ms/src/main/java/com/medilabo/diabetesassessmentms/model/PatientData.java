package com.medilabo.diabetesassessmentms.model;

import java.time.LocalDate;
import java.util.List;

import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;
import com.medilabo.diabetesassessmentms.constant.Gender;
import com.medilabo.diabetesassessmentms.constant.RiskLevel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientData {

	
	private int id;
	
	private String firstname;

	private String lastname;

	private LocalDate birthdate;
	
	private int age;

	private Gender gender;
	
	private RiskLevel riskLevel;
	
	List<DoctorNoteBean> notes;
}
