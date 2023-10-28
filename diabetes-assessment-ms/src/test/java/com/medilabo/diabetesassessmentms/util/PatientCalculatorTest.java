package com.medilabo.diabetesassessmentms.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;

@SpringBootTest
public class PatientCalculatorTest {

	@Autowired
	PatientCalculator patientCalculator;

	@Test
	void calculatePatientAgeFromLocalDate() {
		int age = patientCalculator.calculatePatientAgeFromLocalDate(LocalDate.of(2000, 1, 1));

		assertEquals(age, 23);

	}

	@Test
	void ageOverThirtyFalseTest() {
		int age = 29;
		Boolean result = patientCalculator.ageOverThirty(age);
		assertEquals(result, false);

	}

	@Test
	void ageOverThirtyTrueTest() {
		int age = 31;
		Boolean result = patientCalculator.ageOverThirty(age);
		assertEquals(result, true);

	}
	
	
	@Test
	void triggerAllDiabeteTermCounterTest() {
		List<DoctorNoteBean> notes = new ArrayList<DoctorNoteBean>();
		DoctorNoteBean noteWithAllTerms = new DoctorNoteBean("1", 1, "Hémoglobine A1C, Microalbumine, Taille, Poids, Fumeur, Fumeuse, Anormal, Cholestérol, Vertige, Rechute, Réaction, Anticorps", LocalDate.of(2023, 8, 4));
		notes.add(noteWithAllTerms);
		long triggerCount = patientCalculator.triggerDiabeteTermCounter(notes);
		
		assertEquals(triggerCount, 12);

	}


	@Test
	void triggerNoneDiabeteTermCounterTest() {

		List<DoctorNoteBean> notes = new ArrayList<DoctorNoteBean>();
		long triggerCount = patientCalculator.triggerDiabeteTermCounter(notes);
		
		assertEquals(triggerCount, 0);
		
	}
	
	
	
}
