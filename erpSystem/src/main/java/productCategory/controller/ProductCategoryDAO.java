package productCategory.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import productCategory.ProductCategory;
import productCategory.ProductCategoryDTO;
import util.DBManager;

public class ProductCategoryDAO {
	private static final ProductCategoryDAO instance = new ProductCategoryDAO();

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ProductCategoryDAO() {
	}

	public static ProductCategoryDAO getInstance() {
		return instance;
	}

	// Create
	public void createProductCategory(ProductCategoryDTO productCategoryDto) {
		this.conn = DBManager.getConnection();

		if (this.conn == null)
			return;

		String sql = "INSERT INTO product_category VALUES (?, ?)";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, productCategoryDto.getId());
			this.pstmt.setString(2, productCategoryDto.getName());
			this.pstmt.execute();
		} catch (SQLException e) {
			System.out.println("error by CREATE");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt);
	}

	// Read
	public ProductCategory getProductCategoryById(int id) {
		ProductCategory productCategory = null;
		this.conn = DBManager.getConnection();

		if (this.conn == null)
			return null;

		String sql = "SELECT * FROM product_category WHERE product_category_id=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			this.rs = this.pstmt.executeQuery();

			while (this.rs.next()) {
				int categoryId = this.rs.getInt(1);
				String name = this.rs.getString(2);
				productCategory = new ProductCategory(categoryId, name);
			}

		} catch (SQLException e) {
			System.out.println("error by READ");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt, this.rs);

		return productCategory;
	}
	
	public String getCategroyNameById(int code) {
		String name = "";
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT product_category_name FROM product_category WHERE product_category_id = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, code);
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					name = this.rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}
		
		return name;
	}

	public ArrayList<ProductCategory> getProductCategoryList() {
		ArrayList<ProductCategory> list = new ArrayList<>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM product_category";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int id = this.rs.getInt(1);
					String name = this.rs.getString(2);

					ProductCategory cate = new ProductCategory(id, name);
					list.add(cate);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}

		return list;
	}

	// Update
	public void setProductCategory(ProductCategoryDTO productCategoryDto) {
		this.conn = DBManager.getConnection();

		if (this.conn == null)
			return;

		String sql = "UPDATE product_category SET" + "product_category_name=? WHERE product_category_id=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, productCategoryDto.getName());
			this.pstmt.setInt(2, productCategoryDto.getId());

			this.pstmt.execute();
		} catch (SQLException e) {
			System.out.println("error by UPDATE");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt);
	}

	// Delete
	public void removeProductCategoryById(int id) {
		this.conn = DBManager.getConnection();

		if (this.conn == null)
			return;

		String sql = "DELETE FROM product_category WHERE product_category_id=?";

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
