package com.medilabo.diabetesassessmentms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;
import com.medilabo.diabetesassessmentms.constant.DiabetesTriggerTerms;

@Service
public class PatientCalculator {

	public int calculatePatientAgeFromLocalDate(LocalDate birthdate) {
		LocalDate now = LocalDate.now();

		int age = Period.between(birthdate, now).getYears();

		return age;

	}

	public boolean ageOverThirty(int age) {

		if (age >= 30) {
			return true;
		} else {
			return false;
		}

	}

	public long triggerDiabeteTermCounter(List<DoctorNoteBean> notes) {

		// Turn DoctorNotes list as a big String to lowercase
		String notesToString = notes.stream().map(DoctorNoteBean::getNoteContent).map(String::toLowerCase)
				.collect(Collectors.joining(" "));

		// search for trigger term (in lower case) in the string
		long triggerCount = DiabetesTriggerTerms.list.stream().map(String::toLowerCase).filter(notesToString::contains)
				.count();

		return triggerCount;

	}
}
