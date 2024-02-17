package customerGrade.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import customerGrade.CustomerGrade;
import customerGrade.CustomerGradeDTO;
import util.DBManager;

public class CustomerGradeDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CustomerGradeDAO() {
	}

	private static CustomerGradeDAO instance = new CustomerGradeDAO();

	public static CustomerGradeDAO getinstance() {
		return instance;
	}

	// C
	public void createCustomerGrade(CustomerGradeDTO CustomerGradeDto) {
		CustomerGrade customerGrade = new CustomerGrade(CustomerGradeDto);

		this.conn = DBManager.getConnection();
		if (conn != null) {
			String str = "INSERT INTO customer_grade VALUES(?,?)";

			try {
				this.pstmt = conn.prepareStatement(str);

				this.pstmt.setInt(1, customerGrade.getGradeId());
				this.pstmt.setString(2, customerGrade.getGrade());

				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt);
			}

		}
	}

	// R
	public CustomerGrade getCustomerGradeById(int id) {
		CustomerGrade customerGrade = null;

		this.conn = DBManager.getConnection();
		if (conn != null) {
			String str = "SELECT * FROM customer_grade WHERE customer_grade_id=?";

			try {
				this.pstmt = this.conn.prepareStatement(str);

				this.pstmt.setInt(1, id);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {

					int gradeId = this.rs.getInt(1);
					String grade = this.rs.getString(2);
					customerGrade = new CustomerGrade(gradeId, grade);
				}

			} catch (Exception e) {
				System.out.println("error customer grade READ!");
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt, rs);
			}

		}

		return customerGrade;
	}

	// R
	public ArrayList<CustomerGrade> getCustomerGrade() {
		ArrayList<CustomerGrade> list = new ArrayList<>();

		this.conn = DBManager.getConnection();
		if (conn != null) {
			String str = "SELECT * FROM customer_grade";

			try {
				this.pstmt = this.conn.prepareStatement(str);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int gradeId = this.rs.getInt(1);
					String grade = this.rs.getString(2);

					list.add(new CustomerGrade(gradeId, grade));
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
	public void updateGrade(CustomerGradeDTO CustomerGradeDto) {
		CustomerGrade customerGrade = new CustomerGrade(CustomerGradeDto);

		this.conn = DBManager.getConnection();
		if (conn != null) {
			String str = "UPDATE customer_grade SET customer_grade=? WHERE customer_grade_id=?";

			try {
				this.pstmt = conn.prepareStatement(str);

				this.pstmt.setString(1, customerGrade.getGrade());
				this.pstmt.setInt(2, customerGrade.getGradeId());

				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt);
			}

		}
	}

	// D
	public void deleteGrade(CustomerGradeDTO CustomerGradeDto) {
		CustomerGrade customerGrade = new CustomerGrade(CustomerGradeDto);

		this.conn = DBManager.getConnection();
		if (conn != null) {
			String str = "DELETE FROM customer_grade WHERE customer_grade_id=?";

			try {
				this.pstmt = conn.prepareStatement(str);

				this.pstmt.setInt(1, customerGrade.getGradeId());

				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt);
			}

		}

	}

}
