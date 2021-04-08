package hsbc.codetest.libraryconfigserverms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class LibraryConfigServerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryConfigServerMsApplication.class, args);
	}

}
