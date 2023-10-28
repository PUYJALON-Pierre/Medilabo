package com.medilabo.diabetesassessmentms.service;


import com.medilabo.diabetesassessmentms.constant.RiskLevel;
import com.medilabo.diabetesassessmentms.exception.PatientDataNotFoundException;
import com.medilabo.diabetesassessmentms.model.PatientData;

public interface IDiabetesAssessmentService {

	
	public PatientData retrieveDataOfPatientById(int id) throws PatientDataNotFoundException;
	
	public RiskLevel getDiabeteRiskLevelAssessment(PatientData patientdata) throws PatientDataNotFoundException;
	
	


	
}
