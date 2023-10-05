package com.medilabo.patientms.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medilabo.patientms.constant.Gender;
import com.medilabo.patientms.model.Patient;
import com.medilabo.patientms.repository.PatientRepository;
import com.medilabo.patientms.service.IPatientService;

@WebMvcTest(controllers = PatientController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class PatientControllerTest {

	
	@Autowired
	MockMvc mockMvc;

	@MockBean
	IPatientService iPatientService;

	@MockBean
	PatientRepository patientRepository;

	private Patient patient1;
	private Patient patient2;
	private List<Patient> patients = new ArrayList<Patient>();

	@BeforeEach
	void setUp() {

		patientRepository.deleteAll();
		patients.clear();

		patient1 = new Patient(1, "luis", "enrique", LocalDate.of(1989, 8, 4), Gender.M, "2 rue de Paris",
				"400-587-7566");
		patient2 = new Patient(2, "carlo", "ancelotti", LocalDate.of(1978, 10, 7), Gender.M, "2 rue de Madrid",
				"320-456-6896");

		patients.add(patient1);
		patients.add(patient2);

	}

	@Test
	void getAllPatientsTest() throws Exception {
		
		when(iPatientService.getAllPatients()).thenReturn(patients);
		
		mockMvc.perform(get("/patient/allPatients"))
		.andExpect(status().isFound())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[1].id", is(2)))
		.andExpect(jsonPath("$[0].id", is(1)));
	}
	
	@Test
	void getAllPatientsNoneTest() throws Exception {
	
		when(iPatientService.getAllPatients()).thenReturn(null);
		
		mockMvc.perform(get("/patient/allPatients"))
		.andExpect(status().isNotFound());
	}

	
	
	@Test
	void getPatientByIdTest() throws Exception {
		
	  
		when(iPatientService.getPatientById(1)).thenReturn(patient1);
		
		mockMvc.perform(get("/patient/{id}", 1))
		.andExpect(status().isFound())
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.firstname", is("luis")));
	}
	
	
	@Test
	void getPatientByIdFailTest() throws Exception {
		
	  
		when(iPatientService.getPatientById(1)).thenReturn(null);
		
		mockMvc.perform(get("/patient/{id}", 1))
		.andExpect(status().isNotFound());
	}
	
	
	
	@Test
	void getPatientByFirstnameTest() throws Exception {
		
	  
		when(iPatientService.getPatientByFirstName("luis")).thenReturn(List.of(patient1));
		
		mockMvc.perform(get("/patient/firstname/{firstname}", "luis"))
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].firstname", is("luis")))
		.andExpect(status().isFound());
	}
	
	@Test
	void getPatientByFirstNameFailTest() throws Exception {
		
	  
		when(iPatientService.getPatientByFirstName("luis")).thenReturn(null);
		
		mockMvc.perform(get("/patient/firstname/{firstname}", "luis"))
		.andExpect(status().isNotFound());
	}
	
	
	
	@Test
	void getPatientByLastnameTest() throws Exception {
		
	  
		when(iPatientService.getPatientByLastName("enrique")).thenReturn(List.of(patient1));
		
		mockMvc.perform(get("/patient/lastname/{lastname}", "enrique"))
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].lastname", is("enrique")))
		.andExpect(status().isFound());
	}
	
	@Test
	void getPatientByLastNameFailTest() throws Exception {
		
	  
		when(iPatientService.getPatientByLastName("enrique")).thenReturn(null);
		
		mockMvc.perform(get("/patient/lastname/{lastname}", "enrique"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void addPatientTest() throws Exception {
		when(iPatientService.savePatient(patient1)).thenReturn(patient1);
		 mockMvc.perform(post("/patient").contentType(MediaType.APPLICATION_JSON)
				 .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(patient1)))
		 .andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.firstname", is("luis")));
		
	}
	
	@Test
	void addPatientFailTest() throws Exception {
		when(iPatientService.savePatient(patient1)).thenReturn(null);
		 mockMvc.perform(post("/patient").contentType(MediaType.APPLICATION_JSON)
				 .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(patient1)))
		 .andExpect(status().isBadRequest());
		
	}
	
	
	@Test
	void updatePatientTest() throws Exception {
		patient1.setFirstname("pedro");
		
		when(iPatientService.updatePatient(patient1)).thenReturn(patient1);
		 mockMvc.perform(put("/patient")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(patient1)))
		 .andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.firstname", is("pedro")));
		
	}
	
	
	@Test
	void updatePatientFailTest() throws Exception {
		
		when(iPatientService.updatePatient(patient1)).thenReturn(null);
		 mockMvc.perform(put("/patient")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(patient1)))
		 .andExpect(status().isBadRequest());
		
	}
	
	@Test
	void deletePatientTest() throws Exception {
		
		when(iPatientService.deletePatientById(1)).thenReturn(patient1);
		 mockMvc.perform(delete("/patient/{id}", 1))
				
		 .andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.firstname", is("luis")));	
	}
	
	@Test
	void deletePatientFailTest() throws Exception {
		
		when(iPatientService.deletePatientById(1)).thenReturn(null);
		 mockMvc.perform(delete("/patient/{id}", 1))
				
		 .andExpect(status().isNotFound());	
	}
}
