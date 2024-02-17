package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.Product;
import product.contoroller.ProductDAO;
import productCategory.ProductCategory;
import productCategory.controller.ProductCategoryDAO;

public class ProductDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tmp = request.getParameter("productId");
		
		if(tmp == null)
			return;
		
		int productId = Integer.parseInt(tmp);
		Product product = getTargetProductById(productId);
		ProductCategory category = getCategoryOfProduct(product);
		
		if(product == null || category == null)
			return;
		
		request.setAttribute("targetProduct", product);
		request.setAttribute("productCategory", category);
		request.getRequestDispatcher("/").forward(request, response);
		response.getWriter().close();
	}
	
	
	private Product getTargetProductById(int id) {
		ProductDAO productDao = ProductDAO.getInstance();
		return productDao.getProductById(id);
	}
	
	
	private ProductCategory getCategoryOfProduct(Product product) {
		ProductCategoryDAO categoryDao = ProductCategoryDAO.getInstance();
		int categoryId = product.getCategoryId();
		return categoryDao.getProductCategoryById(categoryId);
	}
	
}
