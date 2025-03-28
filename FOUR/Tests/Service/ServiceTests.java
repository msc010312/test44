package CH38.Tests.Service;

import org.junit.jupiter.api.Test;

import CH38.Domain.DTO.BookDTO;
import CH38.Domain.DTO.UserDTO;
import CH38.Domain.Service.BookServiceImpl;
import CH38.Domain.Service.UserServiceImpl;

public class ServiceTests {

	@Test
	void test1() throws Exception {
		UserServiceImpl userService = UserServiceImpl.getInstance();
		userService.userJoin(new UserDTO());
		userService.userJoin(new UserDTO("basg", "남길동", "125125", "ROLE_USER"));
	}
	
	@Test
	void test2() throws Exception {
		BookServiceImpl bookService = BookServiceImpl.getInstance();
		bookService.bookRegistration(new BookDTO("basg", "남길동", "125125", "ROLE_USER"));
	}
}
