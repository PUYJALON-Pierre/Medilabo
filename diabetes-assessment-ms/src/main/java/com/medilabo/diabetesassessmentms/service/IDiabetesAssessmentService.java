package com.medilabo.diabetesassessmentms.service;

import com.medilabo.diabetesassessmentms.constant.RiskLevel;
import com.medilabo.diabetesassessmentms.exception.PatientDataNotFoundException;
import com.medilabo.diabetesassessmentms.model.PatientData;

/**
 * Interface for services operations in diabetes-assessment-ms
 * 
 *
 */
public interface IDiabetesAssessmentService {

	/**
	 * Retrieve informations of a specific patient by his id and stock them in a
	 * PatientData object
	 * 
	 * @param id -int
	 * @return PatientData
	 * @throws PatientDataNotFoundException
	 */
	public PatientData retrieveDataOfPatientById(int id) throws PatientDataNotFoundException;

	/**
	 * Calculate diabetes risk level from PatientData
	 * 
	 * @param patientdata - PatientData
	 * @return RiskLevel - enumeration
	 * @throws PatientDataNotFoundException
	 */
	public RiskLevel getDiabeteRiskLevelAssessment(PatientData patientdata) throws PatientDataNotFoundException;

}
