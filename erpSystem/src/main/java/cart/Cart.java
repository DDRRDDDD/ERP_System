package cart;

public class Cart {

	private int customerId;
	private int productId;
	private int price;
	private int quantity;
	
	public Cart(int custId, int productId, int price, int quantity) {
		this.customerId = custId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Cart(CartDTO dto) {
		this.customerId = dto.getCustomerId();
		this.productId = dto.getProductId();
		this.price = dto.getPrice();
		this.quantity = dto.getQuantity();
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getPrice() {
		return price;
	}
	
	public int getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
	
	
}
