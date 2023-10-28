package com.medilabo.diabetesassessmentms.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;
import com.medilabo.diabetesassessmentms.constant.DiabetesTriggerTerms;

/**
 * Class that can calculate elements about patient
 */
@Service
public class PatientCalculator {

	/**
	 * Calculate age of a Patient from his birthdate
	 * 
	 * @param birthdate - LocalDate
	 * @return age - int
	 */
	public int calculatePatientAgeFromLocalDate(LocalDate birthdate) {
		LocalDate now = LocalDate.now();

		int age = Period.between(birthdate, now).getYears();

		return age;

	}

	/**
	 * Boolean that define if a patient is over thirty years old or not
	 * 
	 * @param age - int
	 * @return Boolean
	 */
	public boolean ageOverThirty(int age) {

		if (age >= 30) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Method that calculate trigger diabetes terms which are present in a list of
	 * DoctorNoteBean
	 * 
	 * @param notes - List<DoctorNoteBean>
	 * @return triggerCount - long
	 */
	public long triggerDiabeteTermCounter(List<DoctorNoteBean> notes) {

		// Turn DoctorNotes list as a big String to lower case
		String notesToString = notes.stream().map(DoctorNoteBean::getNoteContent).map(String::toLowerCase)
				.collect(Collectors.joining(" "));

		// search for trigger term (in lower case) in the string
		long triggerCount = DiabetesTriggerTerms.list.stream().map(String::toLowerCase).filter(notesToString::contains)
				.count();

		return triggerCount;

	}
}
