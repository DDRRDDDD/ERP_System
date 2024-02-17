package orderProduct.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import orderProduct.OrderProduct;
import orderProduct.OrderProductDTO;
import util.DBManager;

public class OrderProductDAO {
	private static final OrderProductDAO instance = new OrderProductDAO();

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private OrderProductDAO() {}

	public static OrderProductDAO getInstance() {
		return instance;
	}
	
	//Create
	public void createOrderProduct(OrderProductDTO orderProductDto) {
	    this.conn = DBManager.getConnection();

	    if (this.conn == null) {
	        return;
	    }

	    String sql = "INSERT INTO order_product (product_id, order_id, order_product, order_quantity) VALUES (?, ?, ?, ?)";

	    try {
	        this.pstmt = this.conn.prepareStatement(sql);
	        this.pstmt.setInt(1, orderProductDto.getProductId());
	        this.pstmt.setInt(2, orderProductDto.getOrderId());
	        this.pstmt.setString(3, orderProductDto.getOrderProduct());
	        this.pstmt.setInt(4, orderProductDto.getQuantity());

	        this.pstmt.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Error by CREATE");
	        e.printStackTrace();
	    } finally {
	        DBManager.closeConnection(this.conn, this.pstmt);
	    }
	}

	//Read
	public OrderProduct getOrderProductByDetailsId(int id) {
		OrderProduct orderProduct = null;
		this.conn = DBManager.getConnection();

		if(this.conn == null)
			return null;

		String sql = "SELECT * FROM order_product WHERE order_details_id=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			this.rs = this.pstmt.executeQuery();

			while(this.rs.next()) {
				int detailsId		 = this.rs.getInt(1);
				int productId		 = this.rs.getInt(2);
				int orderId			 = this.rs.getInt(3);
				String product	  	 = this.rs.getString(4);
				int quantity		 = this.rs.getInt(5);
				orderProduct = new OrderProduct(detailsId, productId, orderId, product, quantity);
			}

		} catch (SQLException e) {
			System.out.println("error by READ");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt, this.rs);

		return orderProduct;
	}
	
	//Read
	public OrderProduct getOrderProductByProductId(int id) {
		OrderProduct orderProduct = null;
		this.conn = DBManager.getConnection();

		if(this.conn == null)
			return null;

		String sql = "SELECT * FROM order_product WHERE product_id=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);
			this.rs = this.pstmt.executeQuery();

			while(this.rs.next()) {
				int detailsId		 = this.rs.getInt(1);
				int productId		 = this.rs.getInt(2);
				int orderId			 = this.rs.getInt(3);
				String product	  	 = this.rs.getString(4);
				int quantity		 = this.rs.getInt(5);
				orderProduct = new OrderProduct(detailsId, productId, orderId, product, quantity);
			}

		} catch (SQLException e) {
			System.out.println("error by READ");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt, this.rs);

		return orderProduct;
	}


	//Update
	public void setOrderProduct(OrderProductDTO orderProductDto) {
		this.conn = DBManager.getConnection();

		if(this.conn == null)
			return;

		String sql = "UPDATE order_product SET"+
					 "product_id=?, order_id=?, order_product=?, order_quantity=?"+
					 "WHERE order_details_id=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, 	orderProductDto.getProductId());
			this.pstmt.setInt(2, 	orderProductDto.getOrderId());
			this.pstmt.setString(3, orderProductDto.getOrderProduct());
			this.pstmt.setInt(4, 	orderProductDto.getQuantity());
			this.pstmt.setInt(5, 	orderProductDto.getDetailsId());

			this.pstmt.execute();
		} catch (SQLException e) {
			System.out.println("error by UPDATE");
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt);
	}

	
	//Delete
	public void removeOrderProductById(int id) {
		this.conn = DBManager.getConnection();

		if(this.conn == null)
			return;

		String sql = "DELETE FROM order_product WHERE order_details_id=?";

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
	

	// Under method use in shopping cart

	public void removeOrderProductByOrderId(int id) {
		this.conn = DBManager.getConnection();

		if(this.conn == null)
			return;

		String sql = "DELETE FROM order_product WHERE order_id=?";

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

