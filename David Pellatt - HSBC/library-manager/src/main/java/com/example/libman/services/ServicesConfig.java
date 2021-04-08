/**
 * 
 */
package com.example.libman.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * For ease of use elsewhere, define a single configuration to bring in all
 * services.
 * 
 * @author David Pellatt
 */
@Configuration
@Import({ LibraryService.class, BorrowerManagementService.class, BookRentalService.class })
public class ServicesConfig {
}
