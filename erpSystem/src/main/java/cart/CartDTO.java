package cart;

public class CartDTO {

	private int customerId;
	private int productId;
	private int price;
	private int quantity;
	
	public CartDTO(int custId, int productId, int price, int quantity) {
		this.customerId = custId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
