package Domain.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	protected Connection conn;
	protected DAO(){}
}
