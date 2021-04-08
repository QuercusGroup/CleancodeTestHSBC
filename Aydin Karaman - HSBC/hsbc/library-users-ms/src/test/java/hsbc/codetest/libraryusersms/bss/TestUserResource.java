package hsbc.codetest.libraryusersms.bss;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import hsbc.codetest.libraryusersms.bss.dto.UserDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestUserResource {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void saveUserShouldReturnCreatedUser() throws Exception {
		UserDto user = new UserDto();
		user.setName("aydin");
		user.setSurname("karaman");
		assertThat(this.restTemplate.postForObject("http://localhost:" + port, user, UserDto.class).getName().equals("aydin"));
	}
}
