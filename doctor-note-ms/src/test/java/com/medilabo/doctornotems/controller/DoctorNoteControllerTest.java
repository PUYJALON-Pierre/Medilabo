package com.medilabo.doctornotems.controller;

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
import com.medilabo.doctornotems.model.DoctorNote;
import com.medilabo.doctornotems.repository.DoctorNoteRepository;
import com.medilabo.doctornotems.service.IDoctorNoteService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

@WebMvcTest(controllers = DoctorNoteController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class DoctorNoteControllerTest {

	
	

	@Autowired
	MockMvc mockMvc;

	@MockBean
	IDoctorNoteService iDoctorNoteService;

	@MockBean
	DoctorNoteRepository doctorNoteRepository;

	private DoctorNote note1;
	private DoctorNote note2;
	private DoctorNote note3;

	
	@BeforeEach
	void setUp() {

		doctorNoteRepository.deleteAll();

		note1 = new DoctorNote("1", 1, "note", LocalDate.of(2023, 8, 4));
		note2 = new DoctorNote("2", 2, "note2", LocalDate.of(2023, 7, 2));
		note3 = new DoctorNote("3", 3, "note3" ,LocalDate.of(2023, 1, 2));

	

	}
	
	@Test
	void getAllDoctorNotesForPatientTest() throws Exception {
		
       when(iDoctorNoteService.getAllDoctorNotesByPatient(1)).thenReturn(List.of(note1, note3));
		
		mockMvc.perform(get("/doctorNote/patient/{patientId}", 1))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[1].patientId", is(3)))
		.andExpect(jsonPath("$[0].patientId", is(1)));
		
		
	}
	
	@Test
	void getAllDoctorNotesForPatientFailTest() throws Exception {
		
       when(iDoctorNoteService.getAllDoctorNotesByPatient(1)).thenReturn(null);
		
		mockMvc.perform(get("/doctorNote/patient/{patientId}", 1))
		.andExpect(status().isNotFound());

		
		
	}
	
	@Test
	void getDoctorNoteByIdTest() throws Exception {
		
	  
		when(iDoctorNoteService.getDoctorNoteById("1")).thenReturn(note1);
		
		mockMvc.perform(get("/doctorNote/note/{noteId}", 1))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.noteId", is("1")))
		.andExpect(jsonPath("$.noteContent", is("note")));
	}
	
	
	@Test
	void getDoctorNoteByIdFailTest() throws Exception {
		
	  
		when(iDoctorNoteService.getDoctorNoteById("1")).thenReturn(null);
		
		mockMvc.perform(get("/doctorNote/note/{noteId}", 1))
		.andExpect(status().isNotFound());
	}
	
	
	
	@Test
	void addDoctorNoteTest() throws Exception {
		when(iDoctorNoteService.saveDoctorNote(note1)).thenReturn(note1);
		 mockMvc.perform(post("/doctorNote").contentType(MediaType.APPLICATION_JSON)
				 .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(note1)))
		 .andExpect(status().isCreated())
			.andExpect(jsonPath("$.noteId", is("1")))
			.andExpect(jsonPath("$.noteContent", is("note")));
		
	}
	
	@Test
	void addDoctorNoteFailTest() throws Exception {
		when(iDoctorNoteService.saveDoctorNote(note1)).thenReturn(null);
		 mockMvc.perform(post("/doctorNote").contentType(MediaType.APPLICATION_JSON)
				 .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(note1)))
		 .andExpect(status().isBadRequest());
		
	}
	
	
	@Test
	void updateDoctorNoteFailTest() throws Exception {
		
		when(iDoctorNoteService.updateDoctorNote(note1)).thenReturn(null);
		 mockMvc.perform(put("/doctorNote").contentType(MediaType.APPLICATION_JSON)
				 .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(note1)))
		 .andExpect(status().isBadRequest());
		
	}
	
	@Test
	void updateDoctorTest() throws Exception {
		note1.setNoteContent("pedro");
		
		when(iDoctorNoteService.updateDoctorNote(note1)).thenReturn(note1);
		 mockMvc.perform(put("/doctorNote")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(note1)))
		 .andExpect(status().isOk())
			.andExpect(jsonPath("$.noteId", is("1")))
			.andExpect(jsonPath("$.noteContent", is("pedro")));
		
	}
	
	
	@Test
	void deleteDoctorNoteTest() throws Exception {
		
		when(iDoctorNoteService.deleteDoctorNote("1")).thenReturn(note1);
		 mockMvc.perform(delete("/doctorNote/{id}", "1"))
				
		 .andExpect(status().isOk())
			.andExpect(jsonPath("$.noteId", is("1")))
			.andExpect(jsonPath("$.noteContent", is("note")));	
	}
	
	@Test
	void deleteDoctorNoteFailTest() throws Exception {
		
		when(iDoctorNoteService.deleteDoctorNote("1")).thenReturn(null);
		 mockMvc.perform(delete("/doctorNote/{id}", "1"))
				
		 .andExpect(status().isNotFound());	
	}
	
}
