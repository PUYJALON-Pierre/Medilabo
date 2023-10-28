package com.medilabo.doctornotems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * Main class of doctor-note-ms (Médilabo)
 *
 * @author PUYJALON Pierre
 * @since 19/10/2023
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DoctorNoteMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorNoteMsApplication.class, args);
	}

}
