package hsbc.codetest.librarybookrentalms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import hsbc.codetest.librarybookrentalms.bss.dto.UserDto;

@FeignClient(name = "library-users-ms", url = "localhost:8081")
public interface UserServiceClient {

	@GetMapping("/{id}")
	public UserDto getById(@PathVariable Long id);
}
