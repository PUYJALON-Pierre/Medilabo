package com.medilabo.patientms.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.medilabo.patientms.constant.Gender;
import com.medilabo.patientms.model.Patient;
import com.medilabo.patientms.repository.PatientRepository;




@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class PatientServiceTest {

	
	@Autowired
	private IPatientService iPatientService;

	@MockBean
	private PatientRepository patientRepository;

	private Patient patient1;
	private Patient patient2;

	@BeforeEach
	void setUp() {

		patientRepository.deleteAll();

		patient1 = new Patient(1, "luis", "enrique", LocalDate.of(1989, 8, 4), Gender.M, "2 rue de Paris",
				"400-587-7566");
		patient2 = new Patient(2, "carlo", "ancelotti", LocalDate.of(1978, 10, 7), Gender.M, "2 rue de Madrid",
				"320-456-6896");

	}

	@Test
	void getAllPatientsTest() {
		
	when(patientRepository.findAll()).thenReturn(List.of(patient1, patient2));
    
	List<Patient> patients = iPatientService.getAllPatients();
	
	assertThat(patients.contains(patient1));
	assertThat(patients.contains(patient2));
	assertThat(patients.size()).isEqualTo(2);
		
	}

	@Test
	void getPatientByLastNameTest() {
		
	when(patientRepository.findByLastName("enrique")).thenReturn(List.of(patient1));
    
	List<Patient> patientsByLastname = iPatientService.getPatientByLastName("enrique");
	
	assertThat(patientsByLastname.contains(patient1));
	assertThat(patientsByLastname.size()).isEqualTo(1);
		
	}

	@Test
	void getPatientByLastNameFailTest() {
		
		when(patientRepository.findByLastName("enrique")).thenReturn(List.of(patient1));
    
	List<Patient> patientsByLastname = iPatientService.getPatientByLastName("pedro");
	
	assertThat(patientsByLastname.isEmpty());
	assertThat(patientsByLastname.size()).isEqualTo(0);
		
	}

	@Test
	void getPatientByFirstNameTest() {
		
	when(patientRepository.findByFirstName("luis")).thenReturn(List.of(patient1));
    
	List<Patient> patientsByFirstname = iPatientService.getPatientByFirstName("luis");
	
	assertThat(patientsByFirstname.contains(patient1));
	assertThat(patientsByFirstname.size()).isEqualTo(1);
		
	}

	@Test
	void getPatientByFirstNameFailTest() {
		
		when(patientRepository.findByLastName("luis")).thenReturn(List.of(patient1));
    
	List<Patient> patientsByFirstname = iPatientService.getPatientByFirstName("pedro");
	
	assertThat(patientsByFirstname.isEmpty());
	assertThat(patientsByFirstname.size()).isEqualTo(0);
		
	}

	@Test
	void getPatientByIdTest() throws Exception {
		
    when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));
    
    Patient patient = iPatientService.getPatientById(1);
	
    assertThat(patient != null);
	assertThat(patient == patient1 );
    assertEquals(patient.getFirstName(), "luis");
	}
	
	@Test
	void getPatientByIdFailTest() throws Exception {
		try {
			when(patientRepository.findById(1)).thenReturn(Optional.empty());
			
			   Patient patient = iPatientService.getPatientById(1);

		} catch (Exception e) {
			assertEquals(e.getMessage(), "Patient cannot be founded with this id");
		}
	}

	@Test
	void savePatientTest() {
		
    when(patientRepository.save(patient2)).thenReturn(patient2);
	
    assertEquals(iPatientService.savePatient(patient2), patient2);
    verify(patientRepository, times(1)).save(patient2);
 
	}

	@Test
	void updatePatientTest() throws Exception {
		
		  when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));
		  when(patientRepository.save(patient1)).thenReturn(patient1);
		  
		  assertEquals(iPatientService.updatePatient(patient1), patient1);
		    verify(patientRepository, times(1)).save(patient1);
	}

	@Test
	void updatePatientFailTest() throws Exception {
		try {
			when(patientRepository.findById(1)).thenReturn(Optional.empty());
			when(patientRepository.save(patient1)).thenReturn(patient1);

			iPatientService.updatePatient(patient1);

		} catch (Exception e) {
			assertEquals(e.getMessage(), "Patient to update not founded");
		}
	}

	@Test
	void deletePatientTest() throws Exception {
		
		   when(patientRepository.findById(patient1.getId())).thenReturn(Optional.of(patient1));
		   doNothing().when(patientRepository).deleteById(patient1.getId());
           
           Patient patientToDelete = iPatientService.deletePatientById(patient1.getId());
           
           verify(patientRepository, times(1)).deleteById(patient1.getId());
        	assertThat(patientToDelete == patient1 );
	
	}

	@Test
	void deletePatientFailTest() throws Exception {

		try {
			  when(patientRepository.findById(patient1.getId())).thenReturn(Optional.empty());
			 doNothing().when(patientRepository).deleteById(patient1.getId());
			
			 Patient patientToDelete = iPatientService.deletePatientById(patient1.getId());

		} catch (Exception e) {
			assertEquals(e.getMessage(), "Patient cannot be founded with this id");
		}

	}

}
