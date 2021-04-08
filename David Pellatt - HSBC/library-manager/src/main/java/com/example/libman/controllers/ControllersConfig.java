package com.example.libman.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configure all Controllers for easy import to a server app.
 * 
 * @author David Pellatt
 */
@Configuration
@Import({ BookRentalController.class, BorrowerManagementController.class, LibraryManagerController.class })
public class ControllersConfig {
}
