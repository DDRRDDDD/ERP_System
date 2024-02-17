package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.Product;
import product.ProductDTO;
import product.contoroller.ProductDAO;
import productCategory.ProductCategory;
import salesOrder.SalesOrder;

public class CartAction implements Action {

	private final int READY_TO_ORDER = 1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("count");
		int count = Integer.parseInt(num);

		HttpSession session = request.getSession();
		Product product = (Product) session.getAttribute("product");
		ProductCategory category = (ProductCategory) session.getAttribute("category");
		
		int totalStock = product.getStock() - count;
		int lack = totalStock < 0 ? -1 : READY_TO_ORDER;

		switch (lack) {
		case READY_TO_ORDER:
			updateProductStock(product, totalStock);
			SalesOrder order = (SalesOrder) session.getAttribute("cart");

			order.insertCart(product.getId(), count);

		default:
			session.removeAttribute("product");
			session.removeAttribute("category");

			request.setAttribute("targetProduct", product);
			request.setAttribute("productCategory", category);

			if (lack != READY_TO_ORDER)
				request.setAttribute("message", "재고수량 부족!");
		}

		String content = request.getParameter("choose").equals("바로구매") ? "order" : "productdetail";

		request.setAttribute("content", content);
		request.getRequestDispatcher("/").forward(request, response);
	}

	private void updateProductStock(Product product, int totalStock) {
		ProductDAO productDao = ProductDAO.getInstance();
		ProductDTO productDto = new ProductDTO(product);
		productDto.setStock(totalStock);

		productDao.setProduct(productDto);
	}
}
