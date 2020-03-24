package com.auth.AuthDemo;

import com.auth.AuthDemo.gui.TeacherGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;
@SpringBootApplication
public class AuthDemoApplication {
	@Autowired
	private static TeacherGUI teacherGUI;

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(AuthDemoApplication.class, args);
	}

}
