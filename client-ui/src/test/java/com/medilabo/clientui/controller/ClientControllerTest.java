package com.medilabo.clientui.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import com.medilabo.clientui.beans.DoctorNoteBean;
import com.medilabo.clientui.beans.PatientBean;
import com.medilabo.clientui.constant.Gender;
import com.medilabo.clientui.constant.RiskLevel;
import com.medilabo.clientui.proxies.GatewayProxy;


@WebMvcTest(controllers = ClientController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ClientControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	GatewayProxy gatewayProxy;
	


	private PatientBean patient1;
	private PatientBean patient2;
	private List<PatientBean> patients = new ArrayList<PatientBean>();
	private RiskLevel riskLevel;

	@BeforeEach
	void setUp() {

		patients.clear();

		patient1 = new PatientBean(1, "luis", "enrique", LocalDate.of(1989, 8, 4), Gender.M, "2 rue de Paris",
				"400-587-7566");
		patient2 = new PatientBean(2, "carlo", "ancelotti", LocalDate.of(1978, 10, 7), Gender.M, "2 rue de Madrid",
				"320-456-6896");

		patients.add(patient1);
		patients.add(patient2);

	}
	
	@Test
	void getAccueilTest() throws Exception  {
		
		when(gatewayProxy.getPatients()).thenReturn(patients);
		
		mockMvc.perform(get("/client"))
		.andExpect(status().isOk())
	    .andExpect(model().attributeExists("patients"));
		
	}

	
	
	

	@Test
	void getPatientAddFormTest() throws Exception  {
	
		when(gatewayProxy.getPatientById(patient1.getId())).thenReturn(patient1);
		
		mockMvc.perform(get("/client/patient/add"))
		.andExpect(status().isOk())
	     .andExpect(view().name("patient/add"));
		
	}
	

	@Test
	void addPatientTest() throws Exception  {
	
		when(gatewayProxy.addPatient(patient1)).thenReturn(patient1);
		
		mockMvc.perform(post("/client/validate")
				.contentType(MediaType.APPLICATION_JSON)
				.param("firstName","Paul")
				.param("lastName", "Dupont")
				.param("postalAddress", "Paris")
				.param("gender", "M")
				.param("phoneNumber", "300-444-5555")
				.param("birthdate", "1998-10-06")
				)
		   
		.andExpect(status().is3xxRedirection())
	     .andExpect(view().name("redirect:/client"));
		
	}
	
	
	@Test
	void getPatientUpdateFormTest() throws Exception  {
	
		when(gatewayProxy.getPatientById(1)).thenReturn(patient1);
		
		mockMvc.perform(get("/client/patient/update/{id}", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("patient"))
	     .andExpect(view().name("patient/update"));
		
	}
	
	
	@Test
	void updatePatientTest() throws Exception  {
	
		when(gatewayProxy.getPatientById(1)).thenReturn(patient1);
		when(gatewayProxy.updatePatient(patient1)).thenReturn(patient1);
		
		mockMvc.perform(post("/client/patient/update/{id}", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.param("firstName","Paul")
				.param("lastName", "Dupont")
				.param("postalAddress", "Paris")
				.param("gender", "M")
				.param("phoneNumber", "300-444-5555")
				.param("birthdate", "1998-10-06")
				)
		   
		.andExpect(status().is3xxRedirection())
	     .andExpect(view().name("redirect:/client"));
		
	}

	@Test
	void updatePatientFailTest() throws Exception  {
	
		when(gatewayProxy.getPatientById(1)).thenReturn(patient1);
		when(gatewayProxy.updatePatient(patient1)).thenReturn(patient1);
		
		mockMvc.perform(post("/client/patient/update/{id}", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.param("firstName","Paul")
				.param("postalAddress", "Paris")
				.param("gender", "M")
				.param("phoneNumber", "300-444-5555")
				.param("birthdate", "1998-10-06")
				)
		   
		 .andExpect(status().isOk())
	     .andExpect(view().name("patient/update"));
		
	}
	
	
	
	@Test
	void deletePatientTest() throws Exception  {
	
		when(gatewayProxy.getPatientById(1)).thenReturn(patient1);

		
		mockMvc.perform(get("/client/patient/delete/{id}", "1"))
		.andExpect(status().isFound())
	     .andExpect(view().name("redirect:/client"));
		
	}
	
	@Test
	void searchPatientByKeywordTest() throws Exception  {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("keyword", "test");
		
		when(gatewayProxy.getPagePatientsByKeyword("test")).thenReturn(patients);
		when(gatewayProxy.getPatients()).thenReturn(patients);
		
		mockMvc.perform(get("/client/patient/search").params(requestParams))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("keyword"))
		.andExpect(model().attributeExists("patients"))
	     .andExpect(view().name("Accueil"));
		
	}
	
	
	
	
	
	@Test
	void getPractitionerPageTest() throws Exception  {
	
		when(gatewayProxy.getPatientById(patient1.getId())).thenReturn(patient1);
		
		mockMvc.perform(get("/client/practitioner"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("patients"))
	     .andExpect(view().name("practitioner"));
		
	}
	
	
	
	@Test
	void searchPatientByKeywordPractionnerTest() throws Exception  {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("keyword", "test");
		
		when(gatewayProxy.getPagePatientsByKeyword("test")).thenReturn(patients);
		when(gatewayProxy.getPatients()).thenReturn(patients);
		
		mockMvc.perform(get("/client/practitioner/search").params(requestParams))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("keyword"))
		.andExpect(model().attributeExists("patients"))
	     .andExpect(view().name("practitioner"));
		
	}
	
	
	
	
	@Test
	void getAllDoctorNoteForPatientByHisIdTest() throws Exception  {

		
		when(gatewayProxy.getPatientById(1)).thenReturn(patient1);
		when(gatewayProxy.getPatients()).thenReturn(patients);
		when(gatewayProxy.getRiskLevelDiabeteByPatientId(patient1.getId())).thenReturn(riskLevel.BORDERLINE);
		
		mockMvc.perform(get("/client/practitioner/doctorNote/patient/{patientId}", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("doctorNote"))
		.andExpect(model().attributeExists("doctorNotes"))
		.andExpect(model().attributeExists("diabetesRiskLevel"))
		.andExpect(model().attributeExists("patient"))
	     .andExpect(view().name("patientReport"));
		
	}
	
	@Test
	void getAllDoctorNoteForPatientByHisIdFailTest() throws Exception  {

		
		when(gatewayProxy.getPatientById(1)).thenReturn(null);
		when(gatewayProxy.getPatients()).thenReturn(null);
		
		
		mockMvc.perform(get("/client/practitioner/doctorNote/patient/{patientId}", "1"))
		.andExpect(status().is3xxRedirection())
	     .andExpect(view().name("redirect:/client/practitioner"));
		
	}
	
	
	
	
	
	@Test
	void addDoctorNoteTest() throws Exception  {

		
		when(gatewayProxy.getPatientById(1)).thenReturn(patient1);
		when(gatewayProxy.getRiskLevelDiabeteByPatientId(patient1.getId())).thenReturn(riskLevel.BORDERLINE);
		
		mockMvc.perform(post("/client/practitioner/doctorNote/{patientId}", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("doctorNote"))
		.andExpect(model().attributeExists("doctorNotes"))
		.andExpect(model().attributeExists("diabetesRiskLevel"))
		.andExpect(model().attributeExists("patient"))
	     .andExpect(view().name("patientReport"));
		
	}
	

	@Test
	void deleteDoctorNoteTest() throws Exception  {
		
		DoctorNoteBean doctorNote = new DoctorNoteBean();
		doctorNote.setNoteId("1");
		
		when(gatewayProxy.getDoctorNoteById("1")).thenReturn(doctorNote);
		when(gatewayProxy.deleteDoctorNoteForPatient(doctorNote.getNoteId())).thenReturn(doctorNote);
		when(gatewayProxy.getRiskLevelDiabeteByPatientId(patient1.getId())).thenReturn(riskLevel.BORDERLINE);
		
		when(gatewayProxy.getPatientById(doctorNote.getPatientId())).thenReturn(patient1);
		when(gatewayProxy.getDoctorNoteByPatientId(patient1.getId())).thenReturn(List.of(doctorNote));
		
		mockMvc.perform(get("/client/practitioner/doctorNote/delete/{id}", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("doctorNote"))
		.andExpect(model().attributeExists("doctorNotes"))
		.andExpect(model().attributeExists("diabetesRiskLevel"))
		.andExpect(model().attributeExists("patient"))
	     .andExpect(view().name("patientReport"));
		
	}
	
	
}
