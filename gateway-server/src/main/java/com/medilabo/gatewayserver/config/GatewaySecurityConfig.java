package com.medilabo.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

	

		
		public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
				    return http.csrf(csrf -> csrf.disable())
				    		.authorizeExchange(exchanges -> exchanges
				      .anyExchange().authenticated())
				      .formLogin(formLogin -> formLogin
				        .loginPage("/login").authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("http://localhost:8083/client"))
				        )
				      .build();
				}
		
		
		   @Bean
		    public MapReactiveUserDetailsService userDetailsService() {
		        UserDetails user = User.withUsername("user")
		                .password(passwordEncoder().encode("password"))
		                .roles("USER")
		                .build();
		        return new MapReactiveUserDetailsService(user);
		    }


		    @Bean
		    public PasswordEncoder passwordEncoder() {
		        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		    }

		
	
}
