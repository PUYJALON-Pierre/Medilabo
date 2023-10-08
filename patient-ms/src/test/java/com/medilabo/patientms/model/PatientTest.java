package com.medilabo.patientms.model;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.jparams.verifier.tostring.ToStringVerifier;
import com.medilabo.patientms.constant.Gender;

@SpringBootTest
public class PatientTest {

	@Test
	public void patientEqualsAndHashCodeTest() {

		Patient patient1 = new Patient(1, "luis", "enrique", LocalDate.of(1989, 8, 4), Gender.M, "2 rue de Paris",
				"400-587-7566");
		Patient patient2 = new Patient(1, "luis", "enrique", LocalDate.of(1989, 8, 4), Gender.M, "2 rue de Paris",
				"400-587-7566");

		assertNotSame(patient1, patient2);

		assertEquals(patient1.hashCode(), patient2.hashCode());

		assertEquals(patient1.getId(), patient2.getId());
		assertEquals(patient1.getBirthdate(), patient2.getBirthdate());
		assertEquals(patient1.getFirstName(), patient2.getFirstName());
		assertEquals(patient1.getLastName(), patient2.getLastName());
		assertEquals(patient1.getGender(), patient2.getGender());
		assertEquals(patient1.getPostalAddress(), patient2.getPostalAddress());
		assertEquals(patient1.getPhoneNumber(), patient2.getPhoneNumber());
	}

	@Test
	public void patientToStringTest() {

		ToStringVerifier.forClass(Patient.class).verify();
	}

}
