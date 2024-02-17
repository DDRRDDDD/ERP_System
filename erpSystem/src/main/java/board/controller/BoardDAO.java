package board.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.Board;
import board.BoardDTO;
import util.DBManager;

public class BoardDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private BoardDAO() {
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	// C
	public void createBoard(BoardDTO dto) {
		Board board = new Board(dto);

		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "INSERT INTO board VALUES (?,?,?,?,?,?,?)";

			try {

				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, board.getId());
				this.pstmt.setString(2, board.getTitle());
				this.pstmt.setString(3, board.getMain());
				this.pstmt.setString(4, board.getModifiedDate());
				this.pstmt.setString(5, board.getRegisteredDate());
				this.pstmt.setInt(6, board.getWriter());
				this.pstmt.setInt(7, board.getCategoryId());

				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}
	}
	public int getBoardId() {
		int boardId = 1;
		
		this.conn=DBManager.getConnection();
		if(this.conn != null) {
			String str = "SELECT board_id FROM board ORDER BY board_id";
			
			try {
				this.pstmt = this.conn.prepareStatement(str);
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					int id = this.rs.getInt(1);		
					if(boardId == id) {
						boardId+=1;
					}				
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(conn, pstmt, rs);
			}
			
			
		}
		
		return boardId;
	}

	// R

	public Board getBoardById(int id) {
		Board board = null;
		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "SELECT * FROM board WHERE board_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, id);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String title = this.rs.getString(2);
					String main = this.rs.getString(3);
					String modiDate = this.rs.getString(4);
					String regiDate = this.rs.getString(5);
					int writer = this.rs.getInt(6);
					int category = this.rs.getInt(7);

					board = new Board(id, title, main, modiDate, regiDate, writer, category);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}

		return board;
	}
	//R
	public ArrayList<Board> getBoard(int categoryId) {
		ArrayList<Board> list = new ArrayList<Board>();
		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "SELECT * FROM board where board_category_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, categoryId);
			
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int id = this.rs.getInt(1);
					String title = this.rs.getString(2);
					String main = this.rs.getString(3);
					String modiDate = this.rs.getString(4);
					String regiDate = this.rs.getString(5);
					int writer = this.rs.getInt(6);
					int category = this.rs.getInt(7);

					list.add(new Board(id, title, main, modiDate, regiDate, writer, category));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}

		return list;
	}
	
	// U
	public void updateBoard(BoardDTO dto) {
		Board board = new Board(dto);
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "UPDATE board SET board_title =?, board_main=?, board_modified_date=?, board_category_id=? WHERE board_id=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);

				this.pstmt.setString(1, board.getTitle());
				this.pstmt.setString(2, board.getMain());
				this.pstmt.setString(3, board.getModifiedDate());
				this.pstmt.setInt(4, board.getCategoryId());
				this.pstmt.setInt(5, board.getId());

				this.pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt);
			}
		}
	}

	// D
	public void deleteBoardByboardId(int id) {
		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "DELETE FROM board WHERE board_id =?";

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

	public void deleteBoardByWriterId(int id) {
		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "DELETE FROM board WHERE board_writer_id =?";

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

	public ArrayList<Board> getBoardByCustomerId(int custId) {
		ArrayList<Board> list = new ArrayList<Board>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM board WHERE board_writer_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, custId);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int id = this.rs.getInt(1);
					String title = this.rs.getString(2);
					String main = this.rs.getString(3);
					String modi = this.rs.getString(4);
					String regi = this.rs.getString(5);
					int cate = this.rs.getInt(7);

					Board board = new Board(id, title, main, modi, regi, custId, cate);
					list.add(board);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}
	
	public int getTotalCountByCategory(int cateCode) {
		int max = 0;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT COUNT(*) count FROM board WHERE board_category_id =?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, cateCode);
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
	
	public ArrayList<Board> getPostsPerPage(int cateCode, int page){
		ArrayList<Board> list = new ArrayList<Board>();
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM board WHERE board_category_id = ? ORDER BY board_id DESC LIMIT ?,10";
			
			try {
	            this.pstmt = this.conn.prepareStatement(sql);
	            this.pstmt.setInt(1, cateCode);
	            this.pstmt.setInt(2, (page - 1) * 10); 
	            this.rs = this.pstmt.executeQuery();
	            
	            while(this.rs.next()) {
	            	int id = this.rs.getInt(1);
					String title = this.rs.getString(2);
					String main = this.rs.getString(3);
					String modi = this.rs.getString(4);
					String regi = this.rs.getString(5);
					int writer = this.rs.getInt(6);
					
					Board board = new Board(id, title, main, modi, regi, writer, cateCode);
					list.add(board);

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
