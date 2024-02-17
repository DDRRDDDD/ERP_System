package customerLog;

public class CustomerLog {
	private int logId, customerId, preGradeId, postGradeId;
	String changeDate;
	
	public CustomerLog(int logId, int customerId, int preGradeId, int postGradeId, String changeDate) {
		this.logId = logId;
		this.customerId = customerId;
		this.preGradeId = preGradeId;
		this.postGradeId = postGradeId;
		this.changeDate = changeDate;
	}
	public CustomerLog(CustomerLogDTO CustomerLogDto) {
		this.logId = CustomerLogDto.getLogId();
		this.customerId = CustomerLogDto.getCustomerId();
		this.preGradeId = CustomerLogDto.getPreGradeId();
		this.postGradeId = CustomerLogDto.getPostGradeId();
		this.changeDate = CustomerLogDto.getChangeDate();
	}

	public int getLogId() {
		return logId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getPreGradeId() {
		return preGradeId;
	}

	public int getPostGradeId() {
		return postGradeId;
	}

	public String getChangeDate() {
		return changeDate;
	}

}
