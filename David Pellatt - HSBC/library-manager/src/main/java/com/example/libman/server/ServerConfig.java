package com.example.libman.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.libman.controllers.ControllersConfig;
import com.example.libman.repositories.RepositoriesConfig;
import com.example.libman.services.ServicesConfig;

/**
 * Configure our REST Server app by bringing in the Controllers, Services and
 * Repositories.
 */
@Configuration
@Import({ ControllersConfig.class, ServicesConfig.class, RepositoriesConfig.class })
public class ServerConfig {
}
