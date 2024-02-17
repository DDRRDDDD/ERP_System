package customerLog.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import customerLog.CustomerLog;
import customerLog.CustomerLogDTO;
import util.DBManager;

public class CustomerLogDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CustomerLogDAO() {
	}

	private static CustomerLogDAO instance = new CustomerLogDAO();

	public static CustomerLogDAO getinstance() {
		return instance;
	}

	// C
	public void createCustomerLog(CustomerLogDTO CustomerLogDto) {
		CustomerLog customerLog = new CustomerLog(CustomerLogDto);

		this.conn = DBManager.getConnection();
		if (conn != null) {
			String str = "INSERT INTO customer_log VALUESE(?,?,?,?,?)";

			try {
				this.pstmt = this.conn.prepareStatement(str);

				this.pstmt.setString(1, customerLog.getChangeDate());
				this.pstmt.setInt(2, customerLog.getLogId());
				this.pstmt.setInt(3, customerLog.getCustomerId());
				this.pstmt.setInt(4, customerLog.getPreGradeId());
				this.pstmt.setInt(5, customerLog.getPostGradeId());

				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt);
			}

		}
	}

	// R
	public CustomerLog getCustomerLogByLogId(int id) {
		CustomerLog customerLog = null;

		this.conn = DBManager.getConnection();
		if (conn != null) {
			String str = "SELECT * FROM customer_log WHERE change_log_id=?";

			try {
				this.pstmt = this.conn.prepareStatement(str);

				this.pstmt.setInt(1, id);
				this.rs = this.pstmt.executeQuery();

				while(this.rs.next()) {
					String date = this.rs.getString(1);
					int logId = this.rs.getInt(2);
					int customerId = this.rs.getInt(3);
					int preGradeId = this.rs.getInt(4);
					int postGradeId = this.rs.getInt(5);
					
					customerLog = new CustomerLog(logId, customerId, preGradeId, postGradeId, date);
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt, rs);
			}
		}

		return customerLog;

	}

	// R
	public ArrayList<CustomerLog> getCustomerLog() {
		ArrayList<CustomerLog> list = new ArrayList<>();

		this.conn = DBManager.getConnection();
		if (conn != null) {
			String str = "SELECT * FROM customer_log";

			try {
				this.pstmt = this.conn.prepareStatement(str);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String date = this.rs.getString(1);
					int logId = this.rs.getInt(2);
					int customerId = this.rs.getInt(3);
					int preGradeId = this.rs.getInt(4);
					int postGradeId = this.rs.getInt(5);

					list.add(new CustomerLog(logId, customerId, preGradeId, postGradeId, date));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt, rs);
			}
		}

		return list;

	}

	// U
	public void updateCustomerLog(CustomerLogDTO CustomerLogDto) {
		CustomerLog customerLog = new CustomerLog(CustomerLogDto);

		this.conn = DBManager.getConnection();
		if (conn != null) {
			String str = "UPDATE customer_log SET change_date=?, change_log_id=? WHERE customer_id=?";

			try {
				this.pstmt = this.conn.prepareStatement(str);

				this.pstmt.setString(1, customerLog.getChangeDate());
				this.pstmt.setInt(2, customerLog.getLogId());
				this.pstmt.setInt(3, customerLog.getCustomerId());

				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt);
			}

		}
	}

	// D

	public void deleteCustomerLog(CustomerLogDTO CustomerLogDto) {
		CustomerLog customerLog = new CustomerLog(CustomerLogDto);

		this.conn = DBManager.getConnection();
		if (conn != null) {
			String str = "DELETE FROM customer_log WHERE customer_id=?";

			try {
				this.pstmt = this.conn.prepareStatement(str);

				this.pstmt.setInt(1, customerLog.getCustomerId());

				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt);
			}

		}
	}
}
