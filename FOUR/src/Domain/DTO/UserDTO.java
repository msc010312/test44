package Domain.DTO;

//저장단위(Controller -> Service <->DAO<->DB)
public class UserDTO {
	Integer user_id;
	String user_name;
	String user_identity;
	String user_phone;
	String user_addr;
	String user_grade;

	public UserDTO() {
	}

	public UserDTO(Integer user_id, String user_name, String user_identity, String user_phone, String user_addr,
			String user_grade) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_identity = user_identity;
		this.user_phone = user_phone;
		this.user_addr = user_addr;
		this.user_grade = user_grade;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_identity() {
		return user_identity;
	}

	public void setUser_identity(String user_identity) {
		this.user_identity = user_identity;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_addr() {
		return user_addr;
	}

	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}

	public String getUser_grade() {
		return user_grade;
	}

	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}

	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", user_name=" + user_name + ", user_identity=" + user_identity
				+ ", user_phone=" + user_phone + ", user_addr=" + user_addr + ", user_grade=" + user_grade + "]";
	}


}
