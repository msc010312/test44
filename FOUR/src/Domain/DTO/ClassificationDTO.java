package Domain.DTO;

public class ClassificationDTO {
	private int classification_id;
	private String classification_name;
	
	public void ClassificationDTO() {
		
	}

	public ClassificationDTO(int classificationId, String classificationName) {
		super();
		this.classification_id = classificationId;
		this.classification_name = classificationName;
	}

	public int getClassificationid() {
		return classification_id;
	}

	public void setClassificationid(int classificationId) {
		this.classification_id = classificationId;
	}

	public String getClassificationname() {
		return classification_name;
	}

	public void setClassificationname(String classificationName) {
		this.classification_name = classificationName;
	}

	@Override
	public String toString() {
		return "ClassificationDTO [classificationId=" + classification_id + ", classificationName=" + classification_name
				+ "]";
	};
	
	

}
