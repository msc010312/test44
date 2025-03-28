package CH38.Controller;

import java.util.HashMap;
import java.util.Map;

import CH38.Domain.DTO.BookDTO;
import CH38.Domain.Service.BookServiceImpl;

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
			switch (serviceNo) {
			case 1: // 책 등록
				System.out.println("[BC] 책 등록 요청");
				String bookCode = params.get("bookCode") != null ? (String) params.get("bookCode") : null;
				String bookName = params.get("bookName") != null ? (String) params.get("bookName") : null;
				String publisher = params.get("publisher") != null ? (String) params.get("publisher") : null;
				String isbn = params.get("isbn") != null ? (String) params.get("isbn") : null;
				BookDTO bookDTO = new BookDTO(bookCode, bookName, publisher, isbn);
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
				if (params.get("bookCode") != null) {
					System.out.println("[BC] 책 단일 조회 요청");
				} else {
					System.out.println("[BC] 책 전체 조회 요청");
				}
				break;
			case 3: // 책 수정
				System.out.println("[BC] 책 수정 요청");
				break;
			case 4: // 책 삭제
				System.out.println("[BC] 책 삭제 요청");
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
		System.out.println(bookDTO.getBookCode());
		System.out.println(bookDTO.getBookCode().length());
		if (bookDTO.getBookCode() == null || bookDTO.getBookCode().length() != 8) {
			response.put("error", "[INVALID] bookCode의 길이는 8글자이어야 합니다");
			System.out.println("[INVALID] bookCode의 길이는 8글자이어야 합니다");
			return false;
		}
		if (bookDTO.getBookName().length() > 255) {
			response.put("error", "[INVALID] bookName의 길이는 255자를 넘을 수 없습니다");
			System.out.println("[INVALID] bookName의 길이는 255자를 넘을 수 없습니다");
			return false;
		}
		return true;
	}

	// 예외처리함수
	public Map<String, Object> execeptionHandler(Exception e) {
		if (response == null)
			response = new HashMap();
		response.put("status", false);
		response.put("message", e.getMessage());
		response.put("exeception", e);
		return response;
	}

}
