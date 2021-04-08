package uk.co.hsbc.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
@EnableJpaRepositories
@SpringBootApplication
public class LibraryApi {
    public static void main( String[] args ) {
        SpringApplication.run(LibraryApi.class, args);
    }
}
