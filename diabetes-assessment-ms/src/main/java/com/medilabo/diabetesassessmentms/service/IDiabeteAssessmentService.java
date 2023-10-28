package com.medilabo.diabetesassessmentms.service;


import com.medilabo.diabetesassessmentms.constant.RiskLevel;
import com.medilabo.diabetesassessmentms.model.PatientData;

public interface IDiabeteAssessmentService {

	
	public PatientData retrieveDataOfPatientById(int id);
	
	public RiskLevel diabeteRiskLevelAssessment(PatientData patientdata);
	
	


	
}
