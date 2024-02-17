package cartView;

public class CartView {

	private int custId;
	private int pruductId;
	private String productName;
	private int price;
	private int quantity;
	
	public CartView(int c,int pi, String p, int pr, int q) {
		this.custId = c;
		this.pruductId = pi;
		this.productName = p;
		this.price = pr;
		this.quantity = q;
	}

	public int getCustId() {
		return custId;
	}

	public int getPruductId() {
		return pruductId;
	}

	public String getProductName() {
		return productName;
	}

	public int getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}
	
	
	
}
