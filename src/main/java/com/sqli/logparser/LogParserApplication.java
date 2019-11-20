package com.sqli.logparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class LogParserApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private ApplicationContext ctx;

	public static void main(String[] args) {
		SpringApplication.run(LogParserApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LogParserApplication.class);
	}

	@Override
	public void run(String... args) throws Exception { // TODO

		/*
		 * LogParser p = (LogParser) ctx.getBean(LogParserImpl.class);
		 * p.parserDossier("C:\\Users\\sqli\\Desktop\\parser");
		 */

	}

}
