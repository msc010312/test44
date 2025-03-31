package Domain.DTO;

public class ReserveDTO {
	private int rental_id;
	private int user_id;
	private String reserve_order;
	
	public ReserveDTO() {}

	public ReserveDTO(int rental_id, int user_id, String reserve_order) {
		super();
		this.rental_id = rental_id;
		this.user_id = user_id;
		this.reserve_order = reserve_order;
	}

	public int getRental_id() {
		return rental_id;
	}

	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getReserve_order() {
		return reserve_order;
	}

	public void setReserve_order(String reserve_order) {
		this.reserve_order = reserve_order;
	}

	@Override
	public String toString() {
		return "ReserveDTO [rental_id=" + rental_id + ", user_id=" + user_id + ", reserve_order=" + reserve_order
				+ "]";
	}	
}
