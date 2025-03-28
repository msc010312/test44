package CH38.Controller;

import java.util.HashMap;
import java.util.Map;


public class FrontController {
	//서브컨트롤러 저장 자료구조("/endPoint" : 서브컨트롤러 객체)
	private Map<String, SubController> map = new HashMap();
	
	private static FrontController instance;
	private FrontController() {
		System.out.println("[FC] FrontController init");
		init();
	};
	public static FrontController getInstance() {
		if(instance == null)
			instance = new FrontController();
		return instance;
	}
	//초기화
	private void init() {
		// 인증요청 API(ENDPOINT : 서브컨트롤러 객체 저장);
		map.put("/user", new UserController());
		map.put("/book", new BookController());
	}
	
	// View로부터 전달받는 요청 처리
	public Map<String, Object> execute(Map<String, Object> params){
		System.out.println("[FC] execute invoke");
		String endPoint = (String)params.get("endPoint");
		SubController controller = map.get(endPoint);
		return controller.execute(params);
	};
}
