package salesOrder;

public class SalesOrderDTO {

	private int id;
	private int customerId;
	private String date;
	private int total;
	private String status;
	
	public SalesOrderDTO(int id, int custId, String date, int total, String status) {
		this.id = id;
		this.customerId = custId;
		this.date = date;
		this.total = total;
		this.status = status;
	}
	
	public SalesOrderDTO(SalesOrder order) {
		this.id = order.getId();
		this.customerId = order.getCustomerId();
		this.date = order.getDate();
		this.total = order.getTotal();
		this.status = order.getStatus();
	}
	
	public SalesOrderDTO(int custId, String date, int total, String status) {
		this.customerId = custId;
		this.date = date;
		this.total = total;
		this.status = status;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	
}
