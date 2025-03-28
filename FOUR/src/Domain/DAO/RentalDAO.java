package Domain.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;
import Domain.DTO.RentalDTO;

public class RentalDAO extends DAO implements RentalDAOInterface {
	// 데이터베이스 접속 정보 선언
	private static String id = "system"; // 데이터베이스 로그인 아이디
	private static String pw = "1234"; // 데이터베이스 로그인 비밀번호
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Oracle 데이터베이스 URL

	// 데이터베이스 연결 메서드
	public static void DAO() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, id, pw);
		System.out.println("DB CONNECTED...");
	}
	
	private static RentalDAO instace;

	public static RentalDAO getInstance() throws Exception {
		if (instace == null)
			instace = new RentalDAO();
		return instace;
	}

	// CRUD
	@Override
	public int insert(RentalDTO rentalDto) throws Exception {
		try {
			pstmt = conn.prepareStatement("insert into rental_tbl values(?,?,?)");
			pstmt.setInt(1, rentalDto.getRental_id());
			pstmt.setInt(2, rentalDto.getBook_code());
			pstmt.setInt(3, rentalDto.getUser_id());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("RentalDAO : INSERT SQL EXCEPTION");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
		}
	}
}
