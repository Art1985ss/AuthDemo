package com.auth.AuthDemo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is entry point for spring boot application.
 * Additional configuration for *war deployment is made with
 * SpringApplicationBuilder configuration method
 **/


@SpringBootApplication
public class AuthDemoApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(AuthDemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(AuthDemoApplication.class);
	}

}
