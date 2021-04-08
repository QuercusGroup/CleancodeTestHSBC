/**
 * 
 */
package com.example.libman.repositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * For ease of use elsewhere, define a single configuration to bring in all
 * repositories and entities.
 */
@Configuration
@EnableJpaRepositories
@EntityScan({ "com.example.libman.domain" })
public class RepositoriesConfig {

}
