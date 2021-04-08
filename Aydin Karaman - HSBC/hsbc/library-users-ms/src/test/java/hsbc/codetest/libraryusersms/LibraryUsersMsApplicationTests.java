package hsbc.codetest.libraryusersms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import hsbc.codetest.libraryusersms.web.UserResource;

@SpringBootTest
class LibraryUsersMsApplicationTests {
	
	@Autowired
	private UserResource userResource;

	@Test
	void contextLoads() {
		assertThat(userResource).isNotNull();
	}

}
