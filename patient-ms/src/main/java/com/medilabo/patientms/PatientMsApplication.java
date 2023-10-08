package com.medilabo.patientms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main class of Patient microservice (Médilabo)
 *
 * @author PUYJALON Pierre
 * @since 07/10/2023
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PatientMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientMsApplication.class, args);
	}

}
