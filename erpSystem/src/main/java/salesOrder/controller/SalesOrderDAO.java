package salesOrder.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import salesOrder.SalesOrder;
import salesOrder.SalesOrderDTO;
import salesView.MonthlySalesView;
import salesView.SalesView;
import util.DBManager;

public class SalesOrderDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private SalesOrderDAO() {
	}

	private static SalesOrderDAO instance = new SalesOrderDAO();

	public static SalesOrderDAO getInstance() {
		return instance;
	}

	// C
	public void createSalesOrder(SalesOrderDTO dto) {

		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "INSERT INTO sales_order (customer_id, order_date, order_total_price, order_status) VALUES (?,?,?,?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);

				this.pstmt.setInt(1, dto.getCustomerId());
				this.pstmt.setString(2, dto.getDate());
				this.pstmt.setInt(3, dto.getTotal());
				this.pstmt.setString(4, dto.getStatus());

				this.pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}
	}

	public int getSalesOrderId() {
		int salesOrderId = -1;

		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String str = "SELECT MAX(customer_id) FROM sales_order";

			try {
				this.pstmt = this.conn.prepareStatement(str);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next())
					salesOrderId = this.rs.getInt(1);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt, rs);
			}
		}
		return salesOrderId + 1;
	}

	// R
	public SalesOrder getSalesOrderById(int id) {
		SalesOrder order = null;
		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "SELECT * FROM sales_order WHERE order_id=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, id);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int custId = this.rs.getInt(2);
					String date = this.rs.getString(3);
					int total = this.rs.getInt(4);
					String status = this.rs.getString(5);

					order = new SalesOrder(id, custId, date, total, status);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}
		return order;
	}

	public ArrayList<SalesOrder> getSalesOrderByCustomerID(int custId) {
		ArrayList<SalesOrder> list = new ArrayList<SalesOrder>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM sales_order WHERE customer_id=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, custId);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int orderId = this.rs.getInt(1);
					String date = this.rs.getString(3);
					int total = this.rs.getInt(4);
					String status = this.rs.getString(5);

					SalesOrder order = new SalesOrder(orderId, custId, date, total, status);
					list.add(order);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}

	public ArrayList<SalesOrder> getSalesOrderByCustomerID() {
		ArrayList<SalesOrder> list = new ArrayList<SalesOrder>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM sales_order";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int orderId = this.rs.getInt(1);
					int custId = this.rs.getInt(2);
					String date = this.rs.getString(3);
					int total = this.rs.getInt(4);
					String status = this.rs.getString(5);

					SalesOrder order = new SalesOrder(orderId, custId, date, total, status);
					list.add(order);
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
	public SalesOrder getOrderByNoStatusAndId(int custId) {
		SalesOrder order = null;

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM sales_order WHERE order_status='N' AND customer_id=? ";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, custId);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int orderId = this.rs.getInt(1);
					String date = this.rs.getString(3);
					int total = this.rs.getInt(4);
					String status = this.rs.getString(5);

					order = new SalesOrder(orderId, custId, date, total, status);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}
		return order;
	}

	// salesTotal
	public int getTotalPriceByCustomerGrade(int grade) {
		int total = 0;

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT total_sales FROM customer_total_sales WHERE customer_grade = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, grade);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					total += this.rs.getInt("total_sales");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}

		return total;
	}

	// use in draw graph
	public ArrayList<SalesView> getSalesTotal() {
		ArrayList<SalesView> list = new ArrayList<SalesView>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM total_by_grade";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String grade = this.rs.getString(1);
					int total = this.rs.getInt(2);

					SalesView view = new SalesView(grade, total);
					list.add(view);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}

		return list;
	}

	public ArrayList<MonthlySalesView> getMonthlySalesTotal() {
		ArrayList<MonthlySalesView> list = new ArrayList<MonthlySalesView>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM monthly_sales_total";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int month = this.rs.getInt(1);
					int total = this.rs.getInt(2);

					MonthlySalesView temp = new MonthlySalesView(month, total);
					list.add(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}

		return list;
	}

	//////////// up to use in draw graph///////////////////

	public int getTotalOrderCount() {
		int max = 0;

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT COUNT(*) count FROM sales_order";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					max = this.rs.getInt("count");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}

		return max;
	}

	public ArrayList<SalesOrder> getOrdersPerPage(int selpage) {
		ArrayList<SalesOrder> list = new ArrayList<SalesOrder>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM sales_order ORDER BY (CASE WHEN order_status = 'N' THEN 0 WHEN order_status = 'Y' THEN 1 ELSE 2 END), order_date DESC LIMIT ?, 10;";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, (selpage - 1) * 10);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int orderId = this.rs.getInt(1);
					int custId = this.rs.getInt(2);
					String date = this.rs.getString(3);
					int total = this.rs.getInt(4);
					String status = this.rs.getString(5);

					SalesOrder salesorder = new SalesOrder(orderId, custId, date, total, status);
					list.add(salesorder);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}

	public void updateOrderSatatus(int id, String status) {

		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "UPDATE sales_order SET order_status=? WHERE order_id=? ";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, status);
				this.pstmt.setInt(2, id);

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}

	}

	// UP
	public void updateSalesOrder(SalesOrderDTO orderDto) {

		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "UPDATE sales_order SET " + "customer_id=?, order_date=?, order_total_price=?, order_status=?"
					+ " WHERE order_id=? ";

			try {
				this.pstmt = this.conn.prepareStatement(sql);

				this.pstmt.setInt(1, orderDto.getCustomerId());
				this.pstmt.setString(2, orderDto.getDate());
				this.pstmt.setInt(3, orderDto.getTotal());
				this.pstmt.setString(4, orderDto.getStatus());
				this.pstmt.setInt(5, orderDto.getId());
				this.pstmt.setInt(6, orderDto.getId());

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}
	}

	// Update
	public void updateOrderStatusToComplete(int orderId) {

		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			try {
				String sql = "UPDATE sales_order SET order_status = ? WHERE order_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "Y");
				pstmt.setInt(2, orderId);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}
	}

	// DELETE
	public void deleteSalesOrderByCustomerId(int id) {
		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "DELETE FROM sales_order WHERE customer_id =?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);

				this.pstmt.setInt(1, id);

				this.pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}

	}

}
