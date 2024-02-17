package cart.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cart.Cart;
import cart.CartDTO;
import cartView.CartView;
import util.DBManager;

public class CartDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CartDAO() {
	}

	private static CartDAO instance = new CartDAO();

	public static CartDAO getInstance() {
		return instance;
	}

	// C
	public void createCart(CartDTO dto) {
		Cart cart = new Cart(dto);

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "INSERT INTO cart VALUES(?,?,?,?)";

			try {
				this.pstmt = conn.prepareStatement(sql);

				this.pstmt.setInt(1, cart.getCustomerId());
				this.pstmt.setInt(2, cart.getProductId());
				this.pstmt.setInt(3, cart.getPrice());
				this.pstmt.setInt(4, cart.getQuantity());

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}
	}

	// R
	public ArrayList<Cart> readCartListByCustomerId(int customerId) {
		ArrayList<Cart> list = new ArrayList<Cart>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM cart WHERE  cart_customer_id = ?";

			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, customerId);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int productId = this.rs.getInt(2);
					int price = this.rs.getInt(3);
					int quantity = this.rs.getInt(4);

					Cart cart = new Cart(customerId, productId, price, quantity);
					list.add(cart);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}

	// Read
	public ArrayList<CartView> getCartViewTableByCustomerId(int custId) {
		ArrayList<CartView> list = new ArrayList<>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM cart_product_view WHERE cart_customer_id = ?";

			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, custId);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int productId = this.rs.getInt(2);
					String productName = this.rs.getString(3);
					int price = this.rs.getInt(4);
					int quantity = this.rs.getInt(5);

					CartView cart = new CartView(custId, productId, productName, price, quantity);

					list.add(cart);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}

	// Read
	public int getTotalPriceByCustomerId(int custId) {
		int total = 0;

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT total_price FROM cart_total_price WHERE cart_customer_id = ?";

			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, custId);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next())
					total = this.rs.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}
		return total;
	}

	// Update
	public void updateCartQuantity(CartDTO dto) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "UPDATE cart SET cart_quantity = ? WHERE cart_customer_id = ? AND cart_product_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, dto.getQuantity());
				this.pstmt.setInt(2, dto.getCustomerId());
				this.pstmt.setInt(3, dto.getProductId());
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}
	}

	// Delete
	public void deleteCartByCustomerId(int customerId) {
		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "DELETE FROM cart WHERE cart_customer_id = ?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, customerId);
				this.pstmt.execute(); 
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}
	}


	public void deleteCartByProductId(int productId) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "DELETE FROM cart WHERE cart_product_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, productId);
				this.pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}
	}

}
