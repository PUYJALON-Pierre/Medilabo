package com.medilabo.diabetesassessmentms.model;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jparams.verifier.tostring.ToStringVerifier;
import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;
import com.medilabo.diabetesassessmentms.constant.Gender;
import com.medilabo.diabetesassessmentms.constant.RiskLevel;

@SpringBootTest
public class PatientDataTest {

	@Test
	public void patientDataEqualsAndHashCodeTest() {

		List<DoctorNoteBean> notes = new ArrayList<DoctorNoteBean>();

		PatientData patient1 = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 15, Gender.F,
				RiskLevel.BORDERLINE, notes);
		PatientData patient2 = new PatientData(1, "hugues", "dupont", LocalDate.of(1989, 8, 4), 15, Gender.F,
				RiskLevel.BORDERLINE, notes);

		assertNotSame(patient1, patient2);

		assertEquals(patient1.hashCode(), patient2.hashCode());

		assertEquals(patient1.getId(), patient2.getId());
		assertEquals(patient1.getBirthdate(), patient2.getBirthdate());
		assertEquals(patient1.getFirstName(), patient2.getFirstName());
		assertEquals(patient1.getLastName(), patient2.getLastName());
		assertEquals(patient1.getGender(), patient2.getGender());
		assertEquals(patient1.getRiskLevel(), patient2.getRiskLevel());
		assertEquals(patient1.getNotes(), patient2.getNotes());
	}

	@Test
	public void patientDataToStringTest() {

		ToStringVerifier.forClass(PatientData.class).verify();
	}

}
