package hsbc.codetest.librarybookrentalms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import hsbc.codetest.librarybookrentalms.bss.dto.BookDto;

@FeignClient(name="library-books-ms", url="localhost:8080")
public interface BookServiceClient {
	@GetMapping("/{id}")
	public BookDto getById(@PathVariable Long id) ;
}
