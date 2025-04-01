package Domain.DTO;

public class BookDTO {
	int book_Code;
	int classification_id;
	String book_Auther;
	String book_Name;
	String publisher;
	int isreserve;

	public BookDTO() {
	};

	public BookDTO(int bookCode, int classificationId, String bookAuther, String bookName, String publisher, int isreserve) {
		super();
		this.book_Code = bookCode;
		this.classification_id = classificationId; 
		this.book_Auther = bookAuther;
		this.book_Name = bookName;
		this.publisher = publisher;
		this.isreserve = isreserve;
	}

	public int getBook_Code() {
		return book_Code;
	}

	public void setBook_Code(int bookCode) {
		this.book_Code = bookCode;
	}

	public int getClassification_id() {
		return classification_id;
	}

	public void setClassification_id(int classificationId) {
		this.classification_id = classificationId;
	}

	public String getBook_Auther() {
		return book_Auther;
	}

	public void setBook_Auther(String bookAuther) {
		this.book_Auther = bookAuther;
	}

	public String getBook_Name() {
		return book_Name;
	}

	public void setBook_Name(String bookName) {
		this.book_Name = bookName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getIsreserve() {
		return isreserve;
	}

	public void setIsreserve(int isreserve) {
		this.isreserve = isreserve;
	}

	@Override
	public String toString() {
		return "BookDTO [book_Code=" + book_Code + ", classification_id=" + classification_id + ", book_Auther="
				+ book_Auther + ", book_Name=" + book_Name + ", publisher=" + publisher + ", isreserve=" + isreserve
				+ "]";
	}

	

}
