package com.medilabo.diabetesassessmentms.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;
import com.medilabo.diabetesassessmentms.beans.PatientBean;
import com.medilabo.diabetesassessmentms.constant.Gender;
import com.medilabo.diabetesassessmentms.constant.RiskLevel;
import com.medilabo.diabetesassessmentms.exception.PatientDataNotFoundException;
import com.medilabo.diabetesassessmentms.model.PatientData;
import com.medilabo.diabetesassessmentms.proxies.DoctorNoteProxy;
import com.medilabo.diabetesassessmentms.proxies.PatientProxy;
import com.medilabo.diabetesassessmentms.service.IDiabetesAssessmentService;
import com.medilabo.diabetesassessmentms.util.PatientCalculator;

@Service
public class DiabetesAssessmentServiceImpl implements IDiabetesAssessmentService {

	final static Logger LOGGER = LogManager.getLogger(DiabetesAssessmentServiceImpl.class);

	private final PatientProxy patientProxy;

	private final DoctorNoteProxy doctorNoteProxy;

	private final PatientCalculator patientCalculator;

	public DiabetesAssessmentServiceImpl(PatientProxy patientProxy, DoctorNoteProxy doctorNoteProxy,
			PatientCalculator patientCalculator) {
		super();
		this.patientProxy = patientProxy;
		this.doctorNoteProxy = doctorNoteProxy;
		this.patientCalculator = patientCalculator;
	}

	@Override
	public PatientData retrieveDataOfPatientById(int id) throws PatientDataNotFoundException {

		// retrieving patient data
		PatientBean patientRetrieve = patientProxy.getPatientById(id);
		if (patientRetrieve == null) {
			LOGGER.error("Patient not found");
			throw new PatientDataNotFoundException("Patient not founded for assessment");
		}

		List<DoctorNoteBean> notes = doctorNoteProxy.getDoctorNoteByPatientId(id);
		// check null??

		int age = patientCalculator.calculatePatientAgeFromStringBirthdate(patientRetrieve.getBirthdate().toString());

		// Setting data in object
		PatientData patientData = new PatientData(id, patientRetrieve.getFirstName(), patientRetrieve.getLastName(),
				patientRetrieve.getBirthdate(), age, patientRetrieve.getGender(), null, notes);

		return patientData;
	}

	@Override
	public RiskLevel getDiabeteRiskLevelAssessment(PatientData patientdata) throws PatientDataNotFoundException {

		LOGGER.debug("Starting diabetes risk assessment for patient {} {}", patientdata.getFirstname(),
				patientdata.getLastname());
		if (patientdata != null) {

			// getting informations for assessment
			Boolean ageOverThirty = patientCalculator.ageOverThirty(patientdata.getAge());
			long diabeteTriggerCount = patientCalculator.triggerDiabeteTermCounter(patientdata.getNotes());

			// Searching RiskLevel
			if (diabeteTriggerCount == 0) {
				return RiskLevel.NONE;
			}

			else {
				if (ageOverThirty == true && diabeteTriggerCount >= 2 && diabeteTriggerCount <= 5) {
					return RiskLevel.BORDERLINE;
				}

				else {
					if (patientdata.getGender() == Gender.M && ageOverThirty == false && diabeteTriggerCount >= 5) {
						return RiskLevel.EARLY_ONSET;

					}
					if (patientdata.getGender() == Gender.F && ageOverThirty == false && diabeteTriggerCount >= 7) {
						return RiskLevel.EARLY_ONSET;
					}

					if (ageOverThirty == true && diabeteTriggerCount >= 8) {
						return RiskLevel.EARLY_ONSET;
					}

					else {

						if (patientdata.getGender() == Gender.M && ageOverThirty == false && diabeteTriggerCount >= 3) {
							return RiskLevel.IN_DANGER;
						}
						if (patientdata.getGender() == Gender.F && ageOverThirty == false && diabeteTriggerCount >= 4) {
							return RiskLevel.IN_DANGER;
						}
						if (ageOverThirty == true && diabeteTriggerCount >= 6) {
							return RiskLevel.IN_DANGER;

						}

					}

				}

			}

			LOGGER.info("Diabete risk assessment for patient with id : {} completed", patientdata.getId());
			return RiskLevel.NONE; // pour gérer les autres cas???

		}

		else {
			LOGGER.error("Patient not found");
			throw new PatientDataNotFoundException("Patient not founded for assessment");
		}

	}

}
