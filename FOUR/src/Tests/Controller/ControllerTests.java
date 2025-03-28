package CH38.Tests.Controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import CH38.Controller.FrontController;

public class ControllerTests {

	FrontController fc = FrontController.getInstance();

	@Test
	void test1() {
		//View에서 요청정보 담기
		Map<String, Object> params = new HashMap();
		params.put("endPoint", "/user");
		params.put("serviceNo", 1);
		//회원가입-인자전달
		params.put("userid","user1");
		params.put("username","홍길동");
		params.put("password","1234");
		//요청하기
		Map<String,Object> response = fc.execute(params);
		for(String key : response.keySet()) {
			System.out.println(key + " : " + response.get(key));
		}
	}

	@Test
	void test2() {
		//View에서 요청정보 담기
		Map<String, Object> params = new HashMap();
		params.put("endPoint", "/book");
		params.put("serviceNo", 1);
		params.put("bookCode", "12345798");
		params.put("bookName", "1234");
		params.put("publisher", "1");
		params.put("isbn", "1");
		//요청하기
		Map<String,Object> response = fc.execute(params);
		for(String key : response.keySet()) {
			System.out.println(key + " : " + response.get(key));
		}
	}
}
