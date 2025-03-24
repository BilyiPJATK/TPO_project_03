package com.example.tpo_project_03;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TpoProject03Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(TpoProject03Application.class, args);
		FlashcardsController flashcardsController = context.getBean(FlashcardsController.class);

		EntryService entryService = context.getBean(EntryService.class);
		if(entryService.getAllEntries().isEmpty()){
			flashcardsController.addEntry("tak", "ja", "yes");
			flashcardsController.addEntry("testP", "testG", "testE");
		}
		flashcardsController.runApplicationConsole();
	}

}
