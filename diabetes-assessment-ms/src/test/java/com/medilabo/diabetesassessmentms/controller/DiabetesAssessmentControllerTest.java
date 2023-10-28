package com.medilabo.diabetesassessmentms.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;
import com.medilabo.diabetesassessmentms.constant.Gender;
import com.medilabo.diabetesassessmentms.constant.RiskLevel;
import com.medilabo.diabetesassessmentms.model.PatientData;
import com.medilabo.diabetesassessmentms.service.IDiabetesAssessmentService;

@WebMvcTest(controllers = DiabetesAssessmentController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class DiabetesAssessmentControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	IDiabetesAssessmentService iDiabetesAssessmentService;

	private PatientData patient1;
	List<DoctorNoteBean> notes = new ArrayList<DoctorNoteBean>();

	@BeforeEach
	void setUp() {
		patient1 = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 15, Gender.F, null, notes);
	}

	@Test
	void getRiskLevelDiabeteByPatientIdTest() throws Exception {

       	when(iDiabetesAssessmentService.retrieveDataOfPatientById(1)).thenReturn(patient1);
       	when(iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(patient1)).thenReturn(RiskLevel.EARLY_ONSET);
       	
       	
       	mockMvc.perform(get("/diabetesAssessment/{id}", 1))
		.andExpect(status().isOk());
       	
    
       	
       	
	}

	@Test
	void getRiskLevelDiabeteByPatientIdFailTest() throws Exception {

       	when(iDiabetesAssessmentService.retrieveDataOfPatientById(1)).thenReturn(null);
       	when(iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(patient1)).thenReturn(null);
       	
       	
       	mockMvc.perform(get("/diabetesAssessment/{id}", 1))
		.andExpect(status().isNotFound());
       	
       	
       	
       	
	}

}
