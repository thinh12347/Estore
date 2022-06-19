package com.estore.service;

import org.springframework.context.annotation.Bean;

public class BCryptPasswordEncoder {
	@Bean
	public BCryptPasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}	
