package orderProduct;

public class OrderProduct {
	private int detailsId;
	private int productId;
	private int orderId;
	private String orderProduct;
	private int quantity;
	
	public OrderProduct(int detailsId, int productId, int orderId, String orderProduct, int quantity) {
		this.detailsId = detailsId;
		this.productId = productId;
		this.orderId = orderId;
		this.orderProduct = orderProduct;
		this.quantity = quantity;
	}
	
	public OrderProduct(int productId, int orderId, String orderProduct, int quantity) {
		this.productId = productId;
		this.orderId = orderId;
		this.orderProduct = orderProduct;
		this.quantity = quantity;
	}
	
	public OrderProduct(OrderProduct orderProduct) {
		this.productId = orderProduct.productId;
		this.orderId = orderProduct.orderId;
		this.orderProduct = orderProduct.orderProduct;
		this.quantity = orderProduct.quantity;
	}

	public int getDetailsId() 		{return detailsId;}
	public int getProductId() 		{return productId;}
	public int getOrderId() 		{return orderId;}
	public String getOrderProduct() {return orderProduct;}
	public int getQuantity() 		{return quantity;}
	

}
