package com.medilabo.clientui.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medilabo.clientui.beans.DoctorNoteBean;
import com.medilabo.clientui.beans.PatientBean;
import com.medilabo.clientui.proxies.DoctorNoteProxy;
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

	private final DoctorNoteProxy doctorNoteProxy;

	public ClientController(PatientProxy patientProxy, DoctorNoteProxy doctorNoteProxy) {
		super();
		this.patientProxy = patientProxy;
		this.doctorNoteProxy = doctorNoteProxy;
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
	 * Search a patient by keyword containing in his first name or last name (from
	 * client-ui view)
	 * 
	 * @param keyword - String
	 * @param model   - Model
	 * @return Accueil or redirect:/client
	 */
	@GetMapping("/patient/search")
	public String searchPatientByKeyword(
			@RequestParam(name = "keyword", defaultValue = "", required = false) String keyword, Model model) {
		LOGGER.debug("Getting request patient/search/{keyword} to search patient with keyword:{}", keyword);

		if (keyword == "") {
			return "redirect:/client";
		}

		List<PatientBean> searchPatients = patientProxy.getPagePatientsByKeyword(keyword);

		model.addAttribute("keyword", keyword);
		model.addAttribute("patients", searchPatients);

		return "Accueil";

	}

	/**
	 * Get patient/add page model
	 * 
	 * @param patient - PatientBean
	 * @param model   - Model
	 * @return patient/add - (html template)
	 */
	@GetMapping("/patient/add")
	public String showAddPatient(Model model) {
		PatientBean patient = new PatientBean();
		model.addAttribute("patient", patient);
		return "patient/add";

	}

	/**
	 * Creating a new Patient from client-ui view
	 * 
	 * @param patient - PatientBean
	 * @param model   - Model
	 * @param result  -BindingResult
	 * @return redirect:/client or patient/add
	 */
	@PostMapping("/validate")
	public String addPatient(@Valid @ModelAttribute("patient") PatientBean patient, BindingResult result, Model model) {

		LOGGER.debug("Posting request client/validate for patient with id:{}", patient.getId());
		if (result.hasErrors()) {
			LOGGER.error("Error during saving patient : {}", result.getFieldError());
			model.addAttribute("patient", patient);
	
			return "patient/add";
		}

		else {
			patientProxy.addPatient(patient);
			model.addAttribute("patients", patientProxy.getPatients());
			return "redirect:/client";
		}

	}

	/**
	 * Get patient/update page model
	 * 
	 * @param id    - Integer
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
	 * @param id      - Integer
	 * @param patient - PatientBean
	 * @param result  - BindingResult
	 * @param model   - Model
	 * @return patient/update or redirect:/client
	 */
	@PostMapping("/patient/update/{id}")
	public String updatePatient(@PathVariable("id") Integer id, @Valid @ModelAttribute("patient") PatientBean patient, BindingResult result,
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
	 * @param id    - Integer
	 * @param model - Model
	 * @return redirect:/client
	 */
	@GetMapping("/patient/delete/{id}")
	public String deletePatientById(@PathVariable("id") Integer id, Model model) {
		LOGGER.debug("Getting request patient/delete/{id} for patient with id:{}", id);
		PatientBean patient = patientProxy.getPatientById(id);
		if (patient == null) {
			LOGGER.error("Error while deleting patient with id:{}", id);
			return "redirect:/client";
		}

		patientProxy.deletePatientById(id);

		model.addAttribute("patients", patientProxy.getPatients());

		return "redirect:/client";

	}

	/***************************************************************************************************************************
	 
	 
	 * /** Get Practitioner page model
	 * 
	 * @param model - Model
	 * @return Accueil - (html template)
	 */
	@GetMapping("/practitioner")
	public String practitionerPage(Model model) {

		List<PatientBean> patients = new ArrayList<>();
		model.addAttribute("patients", patients);
		return "practitioner";

	}

	/**
	 * Search a patient by keyword containing in his first name or last name (from
	 * client-ui view)
	 * 
	 * @param keyword - String
	 * @param model   - Model
	 * @return Accueil or redirect:/client
	 */
	@GetMapping("/practitioner/search")
	public String searchPatientByKeywordPractionner(
			@RequestParam(name = "keyword", defaultValue = "", required = false) String keyword, Model model) {
		LOGGER.debug("Getting request patient/search/{keyword} to search patient with keyword:{}", keyword);

		if (keyword == "") {
			return "redirect:/client/practitioner";
		}

		List<PatientBean> searchPatients = patientProxy.getPagePatientsByKeyword(keyword);

		model.addAttribute("keyword", keyword);
		model.addAttribute("patients", searchPatients);

		return "practitioner";

	}

	/***************************************************************************************************************************
	 
	  
	/** Search a patient by keyword containing in his first name or last name
	 * (from client-ui view)
	 * 
	 * @param keyword - String
	 * @param model   - Model
	 * @return Accueil or redirect:/client
	 */
	@GetMapping("/practitioner/doctorNote/patient/{patientId}")
	public String getAllDoctorNoteForPatientByHisId(@PathVariable("patientId") int patientId, Model model) {

		LOGGER.debug(
				"Getting request /practitioner/doctorNote/patient/{patientId} to search doctor notes for patient with id:{}",
				patientId);

		DoctorNoteBean doctorNote = new DoctorNoteBean();
		model.addAttribute("doctorNote", doctorNote);

		PatientBean patient = patientProxy.getPatientById(patientId);
		List<DoctorNoteBean> doctorNotes = doctorNoteProxy.getDoctorNoteByPatientId(patientId);

		if (doctorNotes == null || patient == null) {
			return "redirect:/client/practitioner";
		}

		model.addAttribute("patient", patient);
		model.addAttribute("doctorNotes", doctorNotes);

		return "patientReport";

	}

	/**
	 * Creating a new DoctorNote from client-ui view
	 * 
	 * @param patientId  - int
	 * @param doctorNote - DoctorNote
	 * @param model      - Model
	 * @param result     - BindingResult
	 * @return patientReport (html template)
	 */
	@PostMapping("/practitioner/doctorNote/{patientId}")
	public String addDoctorNote(@PathVariable("patientId") int patientId, @Valid DoctorNoteBean doctorNote,
			BindingResult result, Model model) {

		LOGGER.debug("Posting request client/doctorNote for patient with id:{}", doctorNote.getPatientId());
		model.addAttribute("doctorNote", doctorNote);

		PatientBean patient = patientProxy.getPatientById(patientId);
		model.addAttribute("patient", patient);

		if (result.hasErrors()) {
			LOGGER.error("Error during saving doctor note : {}", result.getFieldError());
			return "patientReport";
		}

		else {
			doctorNote.setPatientId(patient.getId());
			doctorNoteProxy.addDoctorNoteForPatient(doctorNote);

			List<DoctorNoteBean> doctorNotes = doctorNoteProxy.getDoctorNoteByPatientId(doctorNote.getPatientId());
			model.addAttribute("doctorNotes", doctorNotes);

			return "patientReport";
		}

	}

	/**
	 * Delete an existing DoctorNote by her id in database from client-ui view
	 * 
	 * @param id    - String
	 * @param model - Model
	 * @return patientReport (html template)
	 */
	@GetMapping("/practitioner/doctorNote/delete/{id}")
	public String deleteNoteById(@PathVariable("id") String id, Model model) {
		LOGGER.debug("Deleting request doctorNote/delete/{id} for note with id:{}", id);
		DoctorNoteBean noteToDelete = doctorNoteProxy.getDoctorNoteById(id);

		if (noteToDelete == null) {
			LOGGER.error("Error while deleting note with id:{}", id);
			return "patientReport";
		} else {

			doctorNoteProxy.deleteDoctorNoteForPatient(id);

			DoctorNoteBean doctorNote = new DoctorNoteBean();
			model.addAttribute("doctorNote", doctorNote);

			PatientBean patient = patientProxy.getPatientById(noteToDelete.getPatientId());
			model.addAttribute("patient", patient);

			List<DoctorNoteBean> doctorNotes = doctorNoteProxy.getDoctorNoteByPatientId(patient.getId());
			model.addAttribute("doctorNotes", doctorNotes);

			return "patientReport";
		}

	}

}
