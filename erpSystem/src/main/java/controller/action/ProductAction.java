package controller.action;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.Product;
import product.contoroller.ProductDAO;
import productCategory.ProductCategory;

public class ProductAction implements Action{
	
	private String keyword;
	private String code;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String code = request.getParameter("code");
		
		if(this.keyword == null && this.code == null)
			return;
		
		request.setAttribute("searchProduct", getSearchProduct());
		request.setAttribute("result", "?");
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

}
