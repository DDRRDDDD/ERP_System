package controller.action;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.Product;
import product.contoroller.ProductDAO;
import productCategory.controller.ProductCategoryDAO;

public class ProductAction implements Action{
	
	private String keyword;
	private String code;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.keyword = request.getParameter("keyword");
		this.code = request.getParameter("code");
		
		List<Product> list = getSearchProduct();
		
		if(this.code != null || !this.keyword.equals("")) {	
			request.setAttribute("list",list);
			request.setAttribute("result", getSearchResult());
		}
		
		request.getRequestDispatcher("/").forward(request, response);
	}
	
	
	private List<Product> getProductListByKeyword() {
		ProductDAO productDao = ProductDAO.getInstance();
		List<Product> productList = productDao.getProductList();
		
		return productList.stream()
						  .filter(product -> product.getName().contains(this.keyword))
						  .collect(Collectors.toList());
	}
	
	
	private List<Product> getProductListByCategory(){
		int categoryId = Integer.parseInt(this.code);
		ProductDAO dao = ProductDAO.getInstance();
		return dao.getProductsByCategory(categoryId);
	}
	
	
	private List<Product> getSearchProduct() {
		return this.keyword != null ?
				getProductListByKeyword() : getProductListByCategory();
	}
	
	
	private String getSearchResult() {
		return this.keyword != null ? 
				"'"+this.keyword+"'에 대한 검색 결과" : getCategoryByCode();		   
	}
	
	
	private String getCategoryByCode() {
		int tagetCode = Integer.parseInt(this.code);
		ProductCategoryDAO categoryDao = ProductCategoryDAO.getInstance();
		return categoryDao.getCategroyNameById(tagetCode);
	}

}
