package com.medilabo.clientui.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medilabo.clientui.beans.PatientBean;
import com.medilabo.clientui.proxies.PatientProxy;

import jakarta.validation.Valid;

/**
 * Controller class of client-ui(Front-end service of Medilabo)
 *
 */
@Controller
@RequestMapping("/client")
public class ClientController {

	final static Logger LOGGER = LogManager.getLogger(ClientController.class);

	private final PatientProxy patientProxy;

	public ClientController(PatientProxy patientProxy) {
		super();
		this.patientProxy = patientProxy;
	}

	/**
	 * Get Acceuil page model
	 * 
	 * @param model - Model
	 * @return Accueil - (html template)
	 */
	@GetMapping
	public String accueil(Model model) {
		List<PatientBean> patients = patientProxy.getPatients();

		model.addAttribute("patients", patients);

		return "Accueil";

	}

	/**
	 * Get patient/add page model
	 * 
	 * @param patient - PatientBean
	 * @param model - Model
	 * @return patient/add - (html template)
	 */
	@GetMapping("/patient/add")
	public String showAddPatient(PatientBean patient, Model model) {
		model.addAttribute("patient", patient);
		return "patient/add";

	}

	/**
	 * Creating a new Patient from client-ui view
	 * 
	 * @param patient - PatientBean
	 * @param model - Model
	 * @param result -BindingResult
	 * @return redirect:/client or patient/add
	 */
	@PostMapping("/validate")
	public String addPatient(@Valid PatientBean patient, Model model, BindingResult result) {

		LOGGER.debug("Posting request client/validate for patient with id:{}", patient.getId());
		if (result.hasErrors()) {
			LOGGER.error("Error during saving patient : {}", result.getFieldError());
			model.addAttribute("patient", patient);
			return "patient/add";}
			
		else {	patientProxy.addPatient(patient);
		model.addAttribute("patients", patientProxy.getPatients());
		return "redirect:/client";}
	

	}

	/**
	 * Get patient/update page model
	 * 
	 * @param id - Integer
	 * @param model - Model
	 * @return patient/update - (html template)
	 */
	@GetMapping("/patient/update/{id}")
	public String showUpdatePatient(@PathVariable("id") Integer id, Model model) {
		LOGGER.debug("Getting request patient/update/{id} for patient with id:{}", id);
		PatientBean patient = patientProxy.getPatientById(id);

		model.addAttribute("patient", patient);
		return "patient/update";

	}

	/**
	 * Updating an existing Patient from client-ui view
	 * 
	 * @param id - Integer
	 * @param patient - PatientBean
	 * @param result - BindingResult
	 * @param model - Model
	 * @return patient/update or redirect:/client
	 */
	@PostMapping("/patient/update/{id}")
	public String updatePatient(@PathVariable("id") Integer id, @Valid PatientBean patient, BindingResult result,
			Model model) {
		LOGGER.debug("Putting request patient/update/{id} for patient with id:{}", id);
		if (result.hasErrors()) {
			patient.setId(id);
			model.addAttribute("patient", patient);
			LOGGER.error("Error during updating patient : {}", result.getFieldError());
			return "patient/update";
		}

		patient = patientProxy.updatePatient(patient);

		model.addAttribute("patient", patient);
		model.addAttribute("patients", patientProxy.getPatients());

		return "redirect:/client";

	}

	/**
	 * Delete an existing Patient by his id in database from client-ui view
	 * 
	 * @param id - Integer
	 * @param model - Model
	 * @return redirect:/client
	 */
	@GetMapping("patient/delete/{id}")
	public String deletePatientById(@PathVariable("id") Integer id, Model model) {
		LOGGER.debug("Getting request bidList/delete/{id} for bidList with id:{}", id);
		PatientBean patient = patientProxy.getPatientById(id);
		patientProxy.deletePatientById(id);

		model.addAttribute("patients", patientProxy.getPatients());

		return "redirect:/client";

	}

	/**
	 * Search a patient by keyword containing in his first name or last name (from client-ui view)
	 * 
	 * @param keyword - String
	 * @param model - Model
	 * @return Accueil or redirect:/client
	 */
	@GetMapping("patient/search")
	public String searchPatientByKeyword(@RequestParam(name = "keyword", required = false) String keyword, Model model)  {
		LOGGER.debug("Getting request patient/search/{keyword} to search patient with keyword:{}", keyword);
	
		if(keyword=="") {
			return "redirect:/client";
		}
		
		List<PatientBean> searchPatients = patientProxy.getPagePatientsByKeyword(keyword);

		model.addAttribute("keyword", keyword);
		model.addAttribute("patients", searchPatients);

		return "Accueil";

	}
	
}
