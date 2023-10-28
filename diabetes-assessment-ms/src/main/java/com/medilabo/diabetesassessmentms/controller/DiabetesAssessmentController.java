package com.medilabo.diabetesassessmentms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.diabetesassessmentms.constant.RiskLevel;
import com.medilabo.diabetesassessmentms.model.PatientData;
import com.medilabo.diabetesassessmentms.service.IDiabetesAssessmentService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/diabetesAssessment")
public class DiabetesAssessmentController {

	final static Logger LOGGER = LogManager.getLogger(DiabetesAssessmentController.class);

	private IDiabetesAssessmentService iDiabetesAssessmentService;

	public DiabetesAssessmentController(IDiabetesAssessmentService iDiabetesAssessmentService) {
		super();
		this.iDiabetesAssessmentService = iDiabetesAssessmentService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<RiskLevel> getRiskLevelDiabeteByPatientId(@PathVariable("id") Integer id) throws Exception {
		LOGGER.debug("Getting request to find diabetes risk level of patient with id:{}", id);

		PatientData patientData = iDiabetesAssessmentService.retrieveDataOfPatientById(id);
		RiskLevel diabeteRiskLevel = iDiabetesAssessmentService.getDiabeteRiskLevelAssessment(patientData);

		if (diabeteRiskLevel == null) {
			LOGGER.error("Error during recuperation of diabetes risk level of patient with id:{}", id);
			return new ResponseEntity<>(diabeteRiskLevel, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Diabetes risk level for patient with id {} founded", id);
		return new ResponseEntity<>(diabeteRiskLevel, HttpStatus.OK);

	}

}
