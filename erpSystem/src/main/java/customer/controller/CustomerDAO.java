package customer.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import customer.Customer;
import customer.CustomerDTO;
import util.DBManager;

public class CustomerDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CustomerDAO() {
	}

	private static CustomerDAO instance = new CustomerDAO();

	public static CustomerDAO getInstance() {
		return instance;
	}

	// C
	public void createCustomer(CustomerDTO CustomerDto) {
		Customer customer = new Customer(CustomerDto);

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String str = "INSERT INTO customer VALUES(?,?,?,?,?,?,?)";

			try {
				this.pstmt = conn.prepareStatement(str);

				this.pstmt.setInt(1, customer.getId());
				this.pstmt.setInt(2, customer.getGradeId());
				this.pstmt.setString(3, customer.getName());
				this.pstmt.setString(4, customer.getAddress());
				this.pstmt.setString(5, customer.getPhone());
				this.pstmt.setString(6, customer.getGender());
				this.pstmt.setString(7, customer.getPassword());

				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt);
			}
		}
	}
	public int getCustomerId() {
		int customerId = 100000;

		this.conn=DBManager.getConnection();
		if(this.conn != null) {
			String str = "SELECT customer_id FROM customer ORDER BY customer_id";

			try {
				this.pstmt = this.conn.prepareStatement(str);
				this.rs = this.pstmt.executeQuery();

				while(this.rs.next()) {
					int id = this.rs.getInt(1);		
					if(customerId == id) {
						customerId+=1;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt, rs);
			}
		}
		return customerId;
	}
	// R
	public Customer getCustomerById(int customerId) {
		Customer customer = null;

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String str = "SELECT * FROM customer WHERE customer_id=?";

			try {
				this.pstmt = conn.prepareStatement(str);

				this.pstmt.setInt(1, customerId);
				this.rs = this.pstmt.executeQuery();

				while(this.rs.next()) {
					int gradeId = this.rs.getInt(2);
					String name = this.rs.getString(3);
					String address = this.rs.getString(4);
					String phone = this.rs.getString(5);
					String gender = this.rs.getString(6);
					String password = this.rs.getString(7);

					customer = new Customer(customerId, gradeId, name, address, phone, gender,password);				
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt, rs);
			}
		}

		return customer;
	}

	public ArrayList<Customer> getCustomer() {
		ArrayList<Customer> list = new ArrayList<Customer>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String str = "SELECT * FROM customer";

			try {
				this.pstmt = conn.prepareStatement(str);


				this.rs = this.pstmt.executeQuery();
				while(this.rs.next()) {
					int id = this.rs.getInt(1);
					int gradeId = this.rs.getInt(2);
					String name = this.rs.getString(3);
					String address = this.rs.getString(4);
					String phone = this.rs.getString(5);
					String gender = this.rs.getString(6);
					String password = this.rs.getString(7);


					list.add(new Customer(id, gradeId, name, address, phone, gender,password));
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
	public void updateCustomer(CustomerDTO CustomerDto) {
		Customer customer = new Customer(CustomerDto);

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String str = "UPDATE customer SET customer_password=?, customer_name=?,customer_address=?,customer_phone=?,customer_gender=? WHERE customer_id=?";

			try {
				this.pstmt = conn.prepareStatement(str);

				this.pstmt.setString(1, customer.getPassword());
				this.pstmt.setString(2, customer.getName());
				this.pstmt.setString(3, customer.getAddress());
				this.pstmt.setString(4, customer.getPhone());
				this.pstmt.setString(5, customer.getGender());
				this.pstmt.setInt(6, customer.getId());

				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt);
			}
		}
	}

	//오버로드
	public void deleteCustomer(CustomerDTO CustomerDto) {
		Customer customer = new Customer(CustomerDto);

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String str = "DELETE FROM customer WHERE customer_id=?";

			try {
				this.pstmt = conn.prepareStatement(str);				
				this.pstmt.setInt(1, customer.getId());


				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt);
			}
		}
	}



	public void deleteCustomer(int customerId) {
		this.conn = DBManager.getConnection();

		if (this.conn == null)
			return;

		String str = "DELETE FROM customer WHERE customer_id=?";

		try {
			this.pstmt = conn.prepareStatement(str);				
			this.pstmt.setInt(1, customerId);

			this.pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBManager.closeConnection(this.conn, this.pstmt);
	}

}
