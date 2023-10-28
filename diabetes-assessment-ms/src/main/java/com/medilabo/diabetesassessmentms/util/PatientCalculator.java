package com.medilabo.diabetesassessmentms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;
import com.medilabo.diabetesassessmentms.constant.DiabetesTriggerTerms;

@Service
public class PatientCalculator {

	public int calculatePatientAgeFromStringBirthdate(String birthdate) {

		Date birthdateDate = null;
		try {
			birthdateDate = (new SimpleDateFormat("MM/dd/yyyy")).parse(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date actualDate = new Date();
		// Calculating age
		Long ageMillisecond = (actualDate.getTime() - birthdateDate.getTime());

		// Converting age into years
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(ageMillisecond);
		int age = c.get(Calendar.YEAR) - 1970;

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

		// Turn DoctorNotes list as a list of string to lowercase
		List<String> notesToString = notes.stream().map(DoctorNoteBean::getNoteContent).map(String::toLowerCase)
				.collect(Collectors.toList());

		// search for trigger term (in lower case) in the list of string

		long triggerCount = DiabetesTriggerTerms.list.stream().map(String::toLowerCase).filter(notesToString::contains)
				.count();

		return triggerCount;

	}
}
