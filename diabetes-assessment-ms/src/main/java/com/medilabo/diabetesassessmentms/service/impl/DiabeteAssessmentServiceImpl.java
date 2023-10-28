package com.medilabo.diabetesassessmentms.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;
import com.medilabo.diabetesassessmentms.beans.PatientBean;
import com.medilabo.diabetesassessmentms.constant.RiskLevel;
import com.medilabo.diabetesassessmentms.model.PatientData;
import com.medilabo.diabetesassessmentms.proxies.DoctorNoteProxy;
import com.medilabo.diabetesassessmentms.proxies.PatientProxy;
import com.medilabo.diabetesassessmentms.service.IDiabeteAssessmentService;
import com.medilabo.diabetesassessmentms.util.PatientCalculator;

@Service
public class DiabeteAssessmentServiceImpl implements IDiabeteAssessmentService {

	final static Logger LOGGER = LogManager.getLogger(DiabeteAssessmentServiceImpl.class);

	private final PatientProxy patientProxy;

	private final DoctorNoteProxy doctorNoteProxy;
	
	private final PatientCalculator patientCalculator;

	public DiabeteAssessmentServiceImpl(PatientProxy patientProxy, DoctorNoteProxy doctorNoteProxy,
			PatientCalculator patientCalculator) {
		super();
		this.patientProxy = patientProxy;
		this.doctorNoteProxy = doctorNoteProxy;
		this.patientCalculator = patientCalculator;
	}
	



	@Override
	public PatientData retrieveDataOfPatientById(int id) {

		// retrieving patient data
		PatientBean patientRetrieve = patientProxy.getPatientById(id);
		List<DoctorNoteBean> notes = doctorNoteProxy.getDoctorNoteByPatientId(id);
		int age = patientCalculator.calculatePatientAgeFromStringBirthdate(patientRetrieve.getBirthdate().toString());

        //Setting data in object
		PatientData patientData = new PatientData(id, patientRetrieve.getFirstName(), patientRetrieve.getLastName(),
				patientRetrieve.getBirthdate(), age, patientRetrieve.getGender(), null, notes);

		return patientData;
	}

	@Override
	public RiskLevel diabeteRiskLevelAssessment(PatientData patientdata) {

		return null;
	}

}
