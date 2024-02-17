package salesView;

public class MonthlySalesView {

	private int month;
	private int total_sales;
	
	public MonthlySalesView(int month, int total) {
		this.month = month;
		this.total_sales = total;
	}

	public int getMonth() {
		return month;
	}

	public int getTotal_sales() {
		return total_sales;
	}
	
	
}
