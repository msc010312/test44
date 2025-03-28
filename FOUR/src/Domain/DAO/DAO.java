package Domain.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Domain.DTO.RentalDTO;

public abstract class DAO {
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	protected static Connection conn;
	protected DAO(){
		
	}
}
