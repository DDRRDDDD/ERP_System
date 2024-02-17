package product;

public class Product{
	private int id;
	private String name;
	private String memo;
	private String handleDate;
	private int stock;
	private String pipeLine;
	private int price;
	private int categoryId;
	private String imageUrl;
	
	public Product(int id, String name, String memo, String handleDate, int stock, String pipeLine, int price,
			int categoryId, String imageUrl) {
		this.id = id;
		this.name = name;
		this.memo = memo;
		this.handleDate = handleDate;
		this.stock = stock;
		this.pipeLine = pipeLine;
		this.price = price;
		this.categoryId = categoryId;
		this.imageUrl = imageUrl;
	}

	public int getId() {return id;}
	
	public String getName() {return name;}
	
	public String getMemo() {return memo;}
	
	public String getHandleDate() {return handleDate;}
	
	public int getStock() {return stock;}
	
	public String getPipeLine() {return pipeLine;}
	
	public int getPrice() {return price;}

	public int getCategoryId() {return categoryId;}
	
	public String getImageUrl() {return imageUrl;}
}
