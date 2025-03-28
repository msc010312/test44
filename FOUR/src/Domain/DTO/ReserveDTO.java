package Domain.DTO;

public class ReserveDTO {
	private int rental_id;
	private int member_id;
	private String reserve_order;
	
	public ReserveDTO() {}

	public ReserveDTO(int rental_id, int member_id, String reserve_order) {
		super();
		this.rental_id = rental_id;
		this.member_id = member_id;
		this.reserve_order = reserve_order;
	}

	public int getRental_id() {
		return rental_id;
	}

	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getReserve_order() {
		return reserve_order;
	}

	public void setReserve_order(String reserve_order) {
		this.reserve_order = reserve_order;
	}

	@Override
	public String toString() {
		return "ReserveDTO [rental_id=" + rental_id + ", member_id=" + member_id + ", reserve_order=" + reserve_order
				+ "]";
	}	
}
