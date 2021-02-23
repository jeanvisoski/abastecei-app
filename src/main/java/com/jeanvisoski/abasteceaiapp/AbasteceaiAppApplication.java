package com.jeanvisoski.abasteceaiapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableCaching
public class AbasteceaiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbasteceaiAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder BCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
