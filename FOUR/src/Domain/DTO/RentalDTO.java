package Domain.DTO;

public class RentalDTO {
	private int rental_id;
	private int book_code;
	private int user_id;
	
	RentalDTO(){};
	public RentalDTO(int rental_id, int book_code, int user_id) {
		super();
		this.rental_id = rental_id;
		this.book_code = book_code;
		this.user_id = user_id;
	}
	public int getRental_id() {
		return rental_id;
	}
	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}
	public int getBook_code() {
		return book_code;
	}
	public void setBook_code(int book_code) {
		this.book_code = book_code;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "RentalDTO [rental_id=" + rental_id + ", book_code=" + book_code + ", user_id=" + user_id + "]";
	}
}
