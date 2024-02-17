package customerLog;

public class CustomerLogDTO {
	
	private int logId, customerId, preGradeId, postGradeId;
	String changeDate;
	
	public CustomerLogDTO(int logId, int customerId, int preGradeId, int postGradeId, String changeDate) {
		this.logId = logId;
		this.customerId = customerId;
		this.preGradeId = preGradeId;
		this.postGradeId = postGradeId;
		this.changeDate = changeDate;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPreGradeId() {
		return preGradeId;
	}

	public void setPreGradeId(int preGradeId) {
		this.preGradeId = preGradeId;
	}

	public int getPostGradeId() {
		return postGradeId;
	}

	public void setPostGradeId(int postGradeId) {
		this.postGradeId = postGradeId;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

}
