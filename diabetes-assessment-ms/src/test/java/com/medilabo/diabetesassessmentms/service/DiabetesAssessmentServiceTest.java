package com.medilabo.diabetesassessmentms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;
import com.medilabo.diabetesassessmentms.beans.PatientBean;
import com.medilabo.diabetesassessmentms.constant.Gender;
import com.medilabo.diabetesassessmentms.constant.RiskLevel;
import com.medilabo.diabetesassessmentms.exception.PatientDataNotFoundException;
import com.medilabo.diabetesassessmentms.model.PatientData;
import com.medilabo.diabetesassessmentms.proxies.DoctorNoteProxy;
import com.medilabo.diabetesassessmentms.proxies.PatientProxy;
import com.medilabo.diabetesassessmentms.util.PatientCalculator;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class DiabetesAssessmentServiceTest {

	@Autowired
	private IDiabetesAssessmentService iDiabetesAssessmentService;

	@MockBean
	PatientProxy patientProxy;

	@MockBean
	DoctorNoteProxy doctorNoteProxy;

	@MockBean
	PatientCalculator patientCalculator;

	PatientBean patient = new PatientBean();
	PatientData patientData = new PatientData();
	List<DoctorNoteBean> notes = new ArrayList<DoctorNoteBean>();

	@BeforeEach
	void setUp() {

		when(patientProxy.getPatientById(1)).thenReturn(patient);
		when(doctorNoteProxy.getDoctorNoteByPatientId(1)).thenReturn(notes);
		when(patientCalculator.calculatePatientAgeFromLocalDate(patient.getBirthdate())).thenReturn(15);

		
		PatientBean patient1 = new PatientBean(1, "luis", "enrique", LocalDate.of(1989, 8, 4), Gender.M, "2 rue de Paris",
				"400-587-7566");
		
		
		
	}

	@Test
	void getPatientDataTest() throws PatientDataNotFoundException {

		patientData = iDiabetesAssessmentService.retrieveDataOfPatientById(1);
		
		
		assertEquals(patientData.getAge(), 15);
		assertEquals(patientData.getBirthdate(), patient.getBirthdate());
		assertEquals(patientData.getFirstName(), patient.getFirstName());
		assertEquals(patientData.getLastName(), patient.getLastName());
		assertEquals(patientData.getGender(), patient.getGender());
		assertEquals(patientData.getId(), patient.getId());
		assertEquals(patientData.getRiskLevel(), null);
		assertEquals(patientData.getNotes(), notes);
	
		
	}	

	@Test
	void getPatientDataFailTest() throws PatientDataNotFoundException {
		when(patientProxy.getPatientById(1)).thenReturn(null);
		
		try {
			patientData = iDiabetesAssessmentService.retrieveDataOfPatientById(1);

		} catch (Exception e) {
			assertEquals(e.getMessage(), "Patient not founded for assessment");
		}
	}

	
	
	@Test
	void getDiabeteRiskLevelAssessmentForLevelNone() throws PatientDataNotFoundException {
		long triggerCount = 0;
		when(patientCalculator.triggerDiabeteTermCounter(notes)).thenReturn(triggerCount);
		
		PatientData test = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 15, Gender.F,
				null, notes);
		
	RiskLevel result =	iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(test);
		
		assertEquals(result, RiskLevel.NONE);
		
		
	}
	
	
	
	
	@Test
	void getDiabeteRiskLevelAssessmentForLevelBorderline() throws PatientDataNotFoundException {
		long triggerCount = 2;
		when(patientCalculator.triggerDiabeteTermCounter(notes)).thenReturn(triggerCount);
		when(patientCalculator.ageOverThirty(30)).thenReturn(true);
		
		PatientData test = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 30, Gender.F,
				null, notes);
		
	RiskLevel result =	iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(test);
		
		assertEquals(result, RiskLevel.BORDERLINE);
		
		
	}
	
	@Test
	void getDiabeteRiskLevelAssessmentForLevelEarlyOnSetAgeUnderThirtyMale() throws PatientDataNotFoundException {
		long triggerCount = 5;
		when(patientCalculator.triggerDiabeteTermCounter(notes)).thenReturn(triggerCount);
		when(patientCalculator.ageOverThirty(15)).thenReturn(false);
		
		PatientData test = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 15, Gender.M,
				null, notes);
		
	RiskLevel result =	iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(test);


		
		assertEquals(result, RiskLevel.EARLY_ONSET);

		
	}
	
	@Test
	void getDiabeteRiskLevelAssessmentForLevelEarlyOnSetAgeUnderThirtyFeMale() throws PatientDataNotFoundException {
		long triggerCount = 7;
		when(patientCalculator.triggerDiabeteTermCounter(notes)).thenReturn(triggerCount);
		when(patientCalculator.ageOverThirty(15)).thenReturn(false);
		
		PatientData test = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 15, Gender.F,
				null, notes);
		
	RiskLevel result =	iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(test);


		
		assertEquals(result, RiskLevel.EARLY_ONSET);

		
	}
	
	@Test
	void getDiabeteRiskLevelAssessmentForLevelEarlyOnSetAgeOverThirty() throws PatientDataNotFoundException {
		long triggerCount = 8;
		when(patientCalculator.triggerDiabeteTermCounter(notes)).thenReturn(triggerCount);
		when(patientCalculator.ageOverThirty(30)).thenReturn(true);
		
		PatientData test = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 30, Gender.F,
				null, notes);

		
	RiskLevel result =	iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(test);

		
		assertEquals(result, RiskLevel.EARLY_ONSET);
	}
	
	
	@Test
	void getDiabeteRiskLevelAssessmentForLevelInDangerAgeMaleUnderThirty() throws PatientDataNotFoundException {
		long triggerCount = 3;
		when(patientCalculator.triggerDiabeteTermCounter(notes)).thenReturn(triggerCount);
		when(patientCalculator.ageOverThirty(15)).thenReturn(false);
		
		PatientData test = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 15, Gender.M,
				null, notes);

		
	RiskLevel result =	iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(test);

		
		assertEquals(result, RiskLevel.IN_DANGER);
	}
	
	@Test
	void getDiabeteRiskLevelAssessmentForLevelInDangerAgeFeMaleUnderThirty() throws PatientDataNotFoundException {
		long triggerCount = 4;
		when(patientCalculator.triggerDiabeteTermCounter(notes)).thenReturn(triggerCount);
		when(patientCalculator.ageOverThirty(15)).thenReturn(false);
		
		PatientData test = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 15, Gender.F,
				null, notes);

		
	RiskLevel result =	iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(test);

		
		assertEquals(result, RiskLevel.IN_DANGER);
	}
	
	@Test
	void getDiabeteRiskLevelAssessmentForLevelInDangerAgeOverThirty() throws PatientDataNotFoundException {
		long triggerCount = 6;
		when(patientCalculator.triggerDiabeteTermCounter(notes)).thenReturn(triggerCount);
		when(patientCalculator.ageOverThirty(30)).thenReturn(true);
		
		PatientData test = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 30, Gender.F,
				null, notes);

		
	RiskLevel result =	iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(test);

		
		assertEquals(result, RiskLevel.IN_DANGER);
	}
	
}


