package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.DTO.BookDTO;
import Domain.DTO.UserDTO;
import Service.BookServiceImpl;


public class BookController implements SubController {

	private BookServiceImpl bookService;

	Map<String, Object> response;

	public BookController() {
		try {
			bookService = BookServiceImpl.getInstance();
		} catch (Exception e) {
			execeptionHandler(e);
		}
	}

	@SuppressWarnings("finally")
	@Override
	public Map<String, Object> execute(Map<String, Object> params) {
		System.out.println("[BC] BookController execute Invoke");

		response = new HashMap();
		Integer serviceNo = (Integer) params.get("serviceNo");
		if (serviceNo == null) {
			response.put("message", "유효하지 않은 서비스 요청입니다.");
			response.put("status", false);
			return response;
		}
		try {
			//파라미터
			int bookCode = params.get("Book_Code") != null ? (int) params.get("Book_Code") : null;
			int classificationId = params.get("Classification_Id") != null ? (int) params.get("Classification_Id") : null;
			String bookAuther = params.get("Book_Auther") != null ? (String) params.get("Book_Auther") : null;
			String bookName = params.get("Book_Name") != null ? (String) params.get("Book_Name") : null;
			String publisher = params.get("Publisher") != null ? (String) params.get("Publisher") : null;
			int isreserve = params.get("Isreserve") != null ? (int) params.get("Isreserve") : null;
			
			switch (serviceNo) {
			case 1: // 책 등록
				System.out.println("[BC] 책 등록 요청");
				BookDTO bookDTO = new BookDTO(bookCode, classificationId, bookAuther, bookName, publisher, isreserve);
				Boolean isOk = isValid(bookDTO);
				System.out.println("[No-1 책 등록] 유효성 검증 확인 : " + isOk);
				if (!isOk) {
					response.put("status", false);
					return response;
				}
				
				Boolean isSuccess = bookService.bookRegistration(bookDTO);
				if(isSuccess) {
					response.put("status", isSuccess);
					response.put("message", "책 등록 성공");
				}
				break;
			case 2: // 책 조회
				 BookDTO bookDTO1 = new BookDTO(-1, -1, null, bookName, null, -1);
				 
				 BookDTO book1DTO = null;
			     List<BookDTO> book5 = null;
		        if (bookDTO1.getBook_Name() == null) {
		            System.out.println("[BC] 도서 정보 전체 조회 요청");
		            book5 = bookService.bookSearchAll();
		        } else {
		            System.out.println("[BC] 특정 도서 조회 요청");
		            bookDTO1.setBook_Name((String) params.get("Book_Name"));
		        }

		        if (book1DTO != null || book5 != null) {
		            response.put("status", true);
		            response.put("message", "도서 조회 성공");
		            if(book1DTO != null)
		            	response.put("data", book1DTO);
		        } else 
		            response.put("status", book5);
		        
			break;
			case 3: // 책 수정
				System.out.println("[BC] 책 수정 요청");
				break;
			case 4: // 책 삭제
				System.out.println("[UC] 도서 수정 요청 확인");
				bookDTO = new BookDTO(bookCode, -1, null, null, null, -1);
				
				boolean isSuccess1 = bookService.bookDelete(bookDTO);

				if (isSuccess1) {
					response.put("status", isSuccess1);
					response.put("message", "도서삭제 성공");
				}
				break;
			default:
				System.err.println("[BC] 잘못된 요청 번호");
				response.put("message", "잘못된 서비스 요청입니다.");
				response.put("status", false);
			}
		} catch (Exception e) {
			execeptionHandler(e);
		} finally {
			return response;
		}
	}

	private Boolean isValid(BookDTO bookDTO) {
//		System.out.println(bookDTO.getBook_Code());
//		System.out.println(bookDTO.getBook_Code().length());
//		if (bookDTO.getBook_Code() == null || bookDTO.getBook_Code().length() != 4) {
//			response.put("error", "[INVALID] bookCode의 길이는 4글자이어야 합니다");
//			System.out.println("[INVALID] bookCode의 길이는 4글자이어야 합니다");
//			return false;
//		}
//		if (bookDTO.getBook_Name().length() > 255) {
//			response.put("error", "[INVALID] bookName의 길이는 255자를 넘을 수 없습니다");
//			System.out.println("[INVALID] bookName의 길이는 255자를 넘을 수 없습니다");
//			return false;
//		}
		return true;
	}

	// 예외처리함수
	public Map<String, Object> execeptionHandler(Exception e) {
		if (response == null)
			response = new HashMap<>();
		response.put("status", false);
		response.put("message", e.getMessage());
		response.put("exeception", e);
		return response;
	}

}
