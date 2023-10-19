package com.medilabo.doctornotems.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.medilabo.doctornotems.model.DoctorNote;
import com.medilabo.doctornotems.repository.DoctorNoteRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class DoctorNoteServiceTest {

	@Autowired
	private IDoctorNoteService iDoctorNoteService;

	@MockBean
	private DoctorNoteRepository doctorNoteRepository;

	private DoctorNote note1;
	private DoctorNote note2;
	private DoctorNote note3;

	@BeforeEach
	void setUp() {

		note1 = new DoctorNote("1", 1, "note", LocalDate.of(2023, 8, 4));
		note2 = new DoctorNote("2", 2, "note2", LocalDate.of(2023, 7, 2));
		note3 = new DoctorNote("3", 3, "note3" ,LocalDate.of(2023, 1, 2));

	}

	@Test
	void getAllDoctorNotesTest() {
		
		
		when(doctorNoteRepository.findAll()).thenReturn(List.of(note1, note2));
	    
		List<DoctorNote> notes = iDoctorNoteService.getAllDoctorNotes();
		
		assertThat(notes.contains(note1));
		assertThat(notes.contains(note2));
		assertThat(notes.size()).isEqualTo(2);
			
		
	}

	@Test
	void getAllDoctorNotesByPatient() {
		
		when(doctorNoteRepository.findAllByPatientId(1)).thenReturn(List.of(note1, note3));
		
		List<DoctorNote> notesByPatient = iDoctorNoteService.getAllDoctorNotesByPatient(1);
		
		assertThat(notesByPatient.contains(note1));
		assertThat(notesByPatient.contains(note3));
		assertThat(notesByPatient.size()).isEqualTo(2);
	}

	@Test
	void getDoctorNoteByIdTest() throws Exception {
		
	    when(doctorNoteRepository.findById("1")).thenReturn(Optional.of(note1));
	    
	    DoctorNote noteById = iDoctorNoteService.getDoctorNoteById("1");
		
	    assertThat(noteById != null);
		assertThat(noteById == note1 );
	    assertEquals(noteById.getNoteContent(), "note");
		
		
	}

	@Test
	void getDoctorNoteByIdFailTest() throws Exception {
		try {
			when(doctorNoteRepository.findById("1")).thenReturn(Optional.empty());

			DoctorNote noteById = iDoctorNoteService.getDoctorNoteById("1");

		} catch (Exception e) {
			assertEquals(e.getMessage(), "Doctor note cannot be founded with this id");
		}
	}

	@Test
	void saveDoctorNoteTest() {
		
		
	    when(doctorNoteRepository.save(note2)).thenReturn(note2);
		
	    assertEquals(iDoctorNoteService.saveDoctorNote(note2), note2);
	    verify(doctorNoteRepository, times(1)).save(note2);
		
		
	}

	@Test
	void updateDoctorNoteTest() throws Exception {
		
	    when(doctorNoteRepository.findById("1")).thenReturn(Optional.of(note1));
	    when(doctorNoteRepository.save(note1)).thenReturn(note1);
		  
		  assertEquals(iDoctorNoteService.updateDoctorNote(note1), note1);
		    verify(doctorNoteRepository, times(1)).save(note1);
		
		
	}

	@Test
	void updateDoctorNoteFailTest() throws Exception {
		try {
			when(doctorNoteRepository.findById("1")).thenReturn(Optional.empty());
			when(doctorNoteRepository.save(note1)).thenReturn(note1);

			iDoctorNoteService.updateDoctorNote(note1);

		} catch (Exception e) {
			assertEquals(e.getMessage(), "Doctor note to update not founded");
		}
	}

	@Test
	void deleteDoctorNoteTest() throws Exception {
		
		  when(doctorNoteRepository.findById("1")).thenReturn(Optional.of(note1));
		   doNothing().when(doctorNoteRepository).deleteById(note1.getNoteId());
          
          DoctorNote noteToDelete = iDoctorNoteService.deleteDoctorNote(note1.getNoteId());
          
          verify(doctorNoteRepository, times(1)).deleteById(note1.getNoteId());
       	assertThat(noteToDelete == note1 );
		
		
	}

	@Test
	void deleteDoctorNoteFailTest() throws Exception {

		try {
			when(doctorNoteRepository.findById("1")).thenReturn(Optional.empty());
			doNothing().when(doctorNoteRepository).deleteById(note1.getNoteId());

			DoctorNote noteToDelete = iDoctorNoteService.deleteDoctorNote(note1.getNoteId());

		} catch (Exception e) {
			assertEquals(e.getMessage(), "Doctor note cannot be founded with this id");
		}

	}
}
