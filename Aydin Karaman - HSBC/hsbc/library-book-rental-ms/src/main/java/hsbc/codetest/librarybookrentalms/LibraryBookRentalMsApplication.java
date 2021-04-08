package hsbc.codetest.librarybookrentalms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LibraryBookRentalMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryBookRentalMsApplication.class, args);
	}

}
