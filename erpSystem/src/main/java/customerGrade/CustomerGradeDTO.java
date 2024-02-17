package customerGrade;

public class CustomerGradeDTO {

	
	int gradeId;
	String grade;
	
	public CustomerGradeDTO(int gradeId, String grade) {
		this.gradeId = gradeId;
		this.grade = grade;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
