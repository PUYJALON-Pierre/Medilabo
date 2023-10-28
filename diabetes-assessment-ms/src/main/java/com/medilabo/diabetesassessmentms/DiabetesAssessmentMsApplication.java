package com.medilabo.diabetesassessmentms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.medilabo.diabetesassessmentms")
public class DiabetesAssessmentMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiabetesAssessmentMsApplication.class, args);
	}

}
