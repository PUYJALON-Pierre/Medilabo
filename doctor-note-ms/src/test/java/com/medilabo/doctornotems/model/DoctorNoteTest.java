package com.medilabo.doctornotems.model;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jparams.verifier.tostring.ToStringVerifier;

@SpringBootTest
public class DoctorNoteTest {

	@Test
	public void DoctorNoteEqualsAndHashCodeTest() {

		DoctorNote note1 = new DoctorNote("1", 1, "note", LocalDate.of(2023, 8, 4));
		DoctorNote note2 = new DoctorNote("2", 2, "note2", LocalDate.of(2023, 7, 2));

		assertNotSame(note1, note2);

		assertEquals(note1.hashCode(), note2.hashCode());

		assertEquals(note1.getNoteId(), note2.getNoteId());
		assertEquals(note1.getDate(), note2.getDate());
		assertEquals(note1.getNoteContent(), note2.getNoteContent());
		assertEquals(note1.getPatientId(), note2.getPatientId());
	}

	@Test
	public void DoctorNoteToStringTest() {

		ToStringVerifier.forClass(DoctorNote.class).verify();
	}
	
}
