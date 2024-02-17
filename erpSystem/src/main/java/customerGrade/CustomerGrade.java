package customerGrade;

public class CustomerGrade {
	
	int gradeId;
	String grade;
	
	public CustomerGrade(int gradeId, String grade) {
		this.gradeId = gradeId;
		this.grade = grade;
	}
	public CustomerGrade(CustomerGradeDTO CustomerGradeDto) {
		this.gradeId = CustomerGradeDto.getGradeId();
		this.grade = CustomerGradeDto.getGrade();
	}

	public int getGradeId() {
		return gradeId;
	}

	public String getGrade() {
		return grade;
	}
	
	

}
