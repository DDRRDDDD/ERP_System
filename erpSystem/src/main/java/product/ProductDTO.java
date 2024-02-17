package product;

public class ProductDTO {
	private int id;
	private String name;
	private String memo;
	private String handleDate;
	private int stock;
	private String pipeLine;
	private int price;
	private int categoryId;
	private String imageUrl;
	
	public ProductDTO() {}
	
	public ProductDTO(int id, String name, String memo, String handleDate, int stock, String pipeLine, int price,
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
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.memo = product.getMemo();
		this.handleDate = product.getHandleDate();
		this.stock = product.getStock();
		this.pipeLine = product.getPipeLine();
		this.price = product.getPrice();
		this.categoryId = product.getCategoryId();
		this.imageUrl = product.getImageUrl();
	}
	
	public ProductDTO(Product product, int updatedStock) {
	    this.id = product.getId();
	    this.name = product.getName();
	    this.memo = product.getMemo();
	    this.handleDate = product.getHandleDate();
	    this.stock = updatedStock;  // 변경된 수량
	    this.pipeLine = product.getPipeLine();
	    this.price = product.getPrice();
	    this.categoryId = product.getCategoryId();
	    this.imageUrl = product.getImageUrl();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getPipeLine() {
		return pipeLine;
	}

	public void setPipeLine(String pipeLine) {
		this.pipeLine = pipeLine;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
