package Domain.DTO;

public class BookDTO {
	private String book_Code;
	private String book_Auther;
	private String book_Name;
	private String publisher;
	private String isreserve;

	public BookDTO() {
	};

	public BookDTO(String bookCode, String bookAuther, String bookName, String publisher, String isreserve) {
		super();
		this.book_Code = bookCode;
		this.book_Auther = bookAuther;
		this.book_Name = bookName;
		this.publisher = publisher;
		this.isreserve = isreserve;
	}

	public String getBookCode() {
		return book_Code;
	}

	public void setBookCode(String bookCode) {
		this.book_Code = bookCode;
	}
	public String getBookAuther() {
		return book_Auther;
	}
	
	public void setBookAuther(String bookAuther) {
		this.book_Auther = bookAuther;
	}

	public String getBookName() {
		return book_Name;
	}

	public void setBookName(String bookName) {
		this.book_Name = bookName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsreserve() {
		return isreserve;
	}

	public void setIsbn(String isreserve) {
		this.isreserve = isreserve;
	}

	@Override
	public String toString() {
		return "BookDTO [bookCode=" + book_Code + ", bookAuther" + book_Auther + ", bookName=" + book_Name + ", publisher=" + publisher + ", isreserve="
				+ isreserve + "]";
	}

}
