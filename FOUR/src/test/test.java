package test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import Domain.DAO.BookDAOImpl;
import Domain.DTO.BookDTO;

class test {

	@Test
	@Disabled
	void test() throws Exception {
		 BookDAOImpl bookDaoImpl = BookDAOImpl.getInstance();  // 수정: 대소문자 오류 수정
         // BookDTO 생성자에 맞는 값을 넣기
		 BookDTO bookDTO = new BookDTO();
	        bookDTO.setBook_Name("mvc 패턴");
	}

}
