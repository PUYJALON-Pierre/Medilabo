package com.medilabo.clientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * Main class of client-ui(Front-end service of Medilabo)
 *
 * @author PUYJALON Pierre
 * @since 07/10/2023
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.medilabo.clientui")
public class ClientUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientUiApplication.class, args);
		
		
	}

}
