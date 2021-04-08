package com.example.libman.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Basic app to run up our REST server.
 * 
 * @author David Pellatt
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LibraryManagerRestServer {

	public static void main(String[] args) {
		new SpringApplication(LibraryManagerRestServer.class).run(args);
	}

}
