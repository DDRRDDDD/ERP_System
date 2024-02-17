package product.contoroller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import product.Product;
import product.ProductDTO;
import util.DBManager;

public class ProductDAO {
	private static final ProductDAO instance = new ProductDAO();

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ProductDAO() {}

	public static ProductDAO getInstance() {
		return instance;
	}
	//Create
	public void createProduct(ProductDTO productDto) {
		this.conn = DBManager.getConnection();

		if(this.conn == null)
			return;

		String sql = "insert into product "+
				"(product_id, product_name, product_memo, product_handle_date, product_stock, product_pipe_line, product_price, product_category_id)"+
				"values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, 	productDto.getId());
			this.pstmt.setString(2, productDto.getName());
			this.pstmt.setString(3, productDto.getMemo());
			this.pstmt.setString(4, productDto.getHandleDate());
			this.pstmt.setInt(5, 	productDto.getStock());
			this.pstmt.setString(6, productDto.getPipeLine());
			this.pstmt.setInt(7, 	productDto.getPrice());
			this.pstmt.setInt(8, 	productDto.getCategoryId());
			this.pstmt.setString(8, productDto.getImageUrl());

			this.pstmt.execute();
		} catch (SQLException e) {
			System.out.println("error by CREATE");
			e.printStackTrace();
		}

		DBManager.closeConnection(this.conn, this.pstmt);
	}

	//Read
	public Product getProductById(int id) {
		Product product = null;
		this.conn = DBManager.getConnection();

		if(this.conn == null)
			return null;

		String sql = "SELECT * FROM product WHERE product_id=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			this.rs = this.pstmt.executeQuery();

			while(this.rs.next()) {
				int productId 	 =this.rs.getInt(1);
				String name		 =this.rs.getString(2);
				String memo		 =this.rs.getString(3);
				String handleDate=this.rs.getString(4);
				int stock	 	 =this.rs.getInt(5);
				String pipeLine	 =this.rs.getString(6);
				int price		 =this.rs.getInt(7);
				int categoryId	 =this.rs.getInt(8);
				String imageUrl	 =this.rs.getString(9);
				product = new Product(productId, name, memo, handleDate, stock, pipeLine, price, categoryId, imageUrl);
			}

		} catch (SQLException e) {
			System.out.println("error by READ(byId)");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt, this.rs);

		return product;
	}
	
	//Read
	public ArrayList<Product> getProductList() {
		ArrayList<Product> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if(this.conn == null)
			return null;

		String sql = "SELECT * FROM product";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			while(this.rs.next()) {
				int productId 	 =this.rs.getInt(1);
				String name		 =this.rs.getString(2);
				String memo		 =this.rs.getString(3);
				String handleDate=this.rs.getString(4);
				int stock	 	 =this.rs.getInt(5);
				String pipeLine	 =this.rs.getString(6);
				int price		 =this.rs.getInt(7);
				int categoryId	 =this.rs.getInt(8);
				String imageUrl	 =this.rs.getString(9);
				list.add(new Product(productId, name, memo, handleDate, stock, pipeLine, price, categoryId, imageUrl));
			}

		} catch (SQLException e) {
			System.out.println("error by READ(list)");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt, this.rs);

		return list;
	}
	
	public ArrayList<Product> getProductsByCategory(int cateId){
		ArrayList<Product> list = new ArrayList<>();
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM product WHERE product_category_id = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, cateId);
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					int productId 	 =this.rs.getInt(1);
					String name		 =this.rs.getString(2);
					String memo		 =this.rs.getString(3);
					String handleDate=this.rs.getString(4);
					int stock		 =this.rs.getInt(5);
					String pipeLine	 =this.rs.getString(6);
					int price	 	 =this.rs.getInt(7);
					String imageUrl	 =this.rs.getString(9);
					
					Product product = new Product(productId, name, memo, handleDate, stock, pipeLine, price, cateId, imageUrl);
					list.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}

	//Update
	public void setProduct(ProductDTO productDto) {
		this.conn = DBManager.getConnection();

		if(this.conn == null)
			return;

		String sql = "UPDATE product SET "+
				"product_name=?, product_memo=?, product_handle_date=?, product_stock=?,"+
				"product_pipe_line=?, product_price=?, product_category_id=?, product_img=?"+
				"WHERE product_id=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			
			this.pstmt.setString(1, productDto.getName());
			this.pstmt.setString(2, productDto.getMemo());
			this.pstmt.setString(3, productDto.getHandleDate());
			this.pstmt.setInt(4, 	productDto.getStock());
			this.pstmt.setString(5, productDto.getPipeLine());
			this.pstmt.setInt(6, 	productDto.getPrice());
			this.pstmt.setInt(7, 	productDto.getCategoryId());
			this.pstmt.setString(8, productDto.getImageUrl());
			this.pstmt.setInt(9, 	productDto.getId());

			this.pstmt.execute();
		} catch (SQLException e) {
			System.out.println("error by UPDATE");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt);
	}
	
	
	public void setProductStock(ProductDTO productDto) {
		this.conn = DBManager.getConnection();
		
		if(this.conn == null)
			return;
		
		String sql = "UPDATE product SET product_stock=? WHERE product_id=?";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, productDto.getStock());
			this.pstmt.setInt(2, productDto.getId());
			
			this.pstmt.execute();
		} catch (SQLException e) {
			System.out.println("error by UPDATE");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt);
	}

	//Delete
	public void removeProductById(int id) {
		this.conn = DBManager.getConnection();

		if(this.conn == null)
			return;

		String sql = "DELETE FROM product WHERE product_id=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			this.pstmt.execute();
		} catch (SQLException e) {
			System.out.println("error by DELETE");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt);
	}
}
