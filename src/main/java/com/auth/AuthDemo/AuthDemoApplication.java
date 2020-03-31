package com.auth.AuthDemo;

//import com.auth.AuthDemo.gui.TeacherGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.awt.*;
@SpringBootApplication
public class AuthDemoApplication extends SpringBootServletInitializer {
//	@Autowired
//	private static TeacherGUI teacherGUI;

	public static void main(String[] args) {
//		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(AuthDemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(AuthDemoApplication.class);
	}

}
