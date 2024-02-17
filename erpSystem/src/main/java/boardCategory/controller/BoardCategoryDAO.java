package boardCategory.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boardCategory.BoardCategory;
import util.DBManager;

public class BoardCategoryDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private BoardCategoryDAO() {
	}

	private static final BoardCategoryDAO instance = new BoardCategoryDAO();

	public static BoardCategoryDAO getInstance() {
		return instance;
	}

	public String getCategoryNameById(int id) {
		String name = "";

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT board_category_name FROM board_category WHERE board_category_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, id);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
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
	
	public ArrayList<BoardCategory> getBoardCategoryAll(){
		ArrayList<BoardCategory> list = new ArrayList<BoardCategory>();
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM board_category";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					int id = this.rs.getInt(1);
					String name = this.rs.getString(2);
					
					BoardCategory cate = new BoardCategory(id,name);
					list.add(cate);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}
		
		return list;
	}
}
