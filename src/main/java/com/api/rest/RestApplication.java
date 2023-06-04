package com.api.rest;

import java.util.Date;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.api.rest.util.dao.GenericoDAOImpl;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableAsync
@EntityScan(basePackages = { "com.api.rest.to" })
@EnableJpaRepositories(basePackages = { "com.api.rest.dao" }, repositoryBaseClass = GenericoDAOImpl.class)
public class RestApplication
{
	
	final String timezone = "America/Sao_Paulo";
	
	@PostConstruct
	void started()
	{
		TimeZone.setDefault(TimeZone.getTimeZone(timezone));
		System.out.println("Aplicação rodando na timezone: " + timezone + " - " + new Date());
	}
	
	public static void main(String[] args)
	{
		SpringApplication.run(RestApplication.class, args);
	}
	
}
