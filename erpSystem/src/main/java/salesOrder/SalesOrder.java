package salesOrder;

import java.util.HashMap;
import java.util.List;

import product.Product;

public class SalesOrder{

	private int id;
	private int customerId;
	private String date;
	private int total;
	private String status;
	
	private HashMap<Integer, Integer> cart = new HashMap<>();
	
	public SalesOrder(int id, int custId, String date, int total, String status) {
		this.id = id;
		this.customerId = custId;
		this.date = date;
		this.total = total;
		this.status = status;
	}
	
	public SalesOrder(int custId, String date, int total, String status) {
		this.customerId = custId;
		this.date = date;
		this.total = total;
		this.status = status;
	}
	
	public SalesOrder(SalesOrder salesOrder) {
		this.id = salesOrder.getId();
		this.customerId = salesOrder.getCustomerId();
		this.date = salesOrder.getDate();
		this.total = salesOrder.getTotal();
		this.status = salesOrder.getStatus();
	}

	public int getId() {return id;}
	public int getCustomerId() {return customerId;}
	public String getDate() {return date;}
	public int getTotal() {return total;}
	public String getStatus() {return status;}

	public void insertCart(int id, int quantity) {
		Integer count = cart.get(id);
		if(count == null)
			count = 0;
		
		cart.put(id, quantity+count);
	}
	
	public HashMap<Integer, Integer> getCart(){
		return this.cart;
	}
	
	public int getTotalPrice(List<Product> list) {
		this.total = 0;
		if(list == null)
			return 0;
		
		for(Product product : list) {
			int quantity = cart.get(product.getId());
			System.out.println("수량 : " + quantity);
			this.total += product.getPrice() * quantity;
			System.out.println("토탈 : " + this.total);
		}
		return this.total;
	}
	
}
