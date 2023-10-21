package com.medilabo.clientui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;


/**
 * Configuration class of feign to pass through spring security authentication in gateway of medilabo project from client-ui
 */
@Configuration
public class FeignConfig {

	
	 @Bean
	   public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor()

	{
	      return  new BasicAuthRequestInterceptor("user", "password");
	   }
}
