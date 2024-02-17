package model;

import java.util.ArrayList;

import boardCategory.BoardCategory;
import boardCategory.controller.BoardCategoryDAO;
import productCategory.ProductCategory;
import productCategory.controller.ProductCategoryDAO;

public class MainVariables {
	ArrayList<ProductCategory> cateList;
	ArrayList<BoardCategory> boardList;
	
	public MainVariables() {
		this.cateList = getCategoryList();
		this.boardList = getBoardListAll();
	}
	
	private ArrayList<ProductCategory> getCategoryList(){
		ProductCategoryDAO dao = ProductCategoryDAO.getInstance();
		return dao.getProductCategoryList();
	}
	

	private ArrayList<BoardCategory> getBoardListAll(){
		BoardCategoryDAO boardcateDao = BoardCategoryDAO.getInstance();
		return boardcateDao.getBoardCategoryAll();
	}
	
	public ArrayList<ProductCategory> getCateList() {
		return cateList;
	}
	
	public ArrayList<BoardCategory> getBoardList() {
		return boardList;
	}
	
	public void setCateList(ArrayList<ProductCategory> cateList) {
		this.cateList = cateList;
	}
	
	public void setBoardList(ArrayList<BoardCategory> boardList) {
		this.boardList = boardList;
	}
	
}
