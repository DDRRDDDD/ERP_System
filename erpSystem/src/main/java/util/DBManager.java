package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {

	private static DataSource contextBind() {
		DataSource source = null;
		try {
			Context context = new InitialContext();
			source = (DataSource) context.lookup("java:comp/env/erpSystem");

		} catch (NamingException e2) {
			System.out.println("Context binding failed");
			e2.printStackTrace();
		}
		return source;
	}

	public static Connection getConnection() {
		Connection conn = null;

		try {
			conn = contextBind().getConnection();
		} catch (SQLException e1) {
			System.out.println("fail to DBconnection");
			e1.printStackTrace();
		}

		return conn;
	}

	public static void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			conn.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("closing failed");
			e.printStackTrace();
		}
	}

	public static void closeConnection(Connection conn, PreparedStatement pstmt) {
		try {
			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("closing failed");
			e.printStackTrace();
		}
	}
}
