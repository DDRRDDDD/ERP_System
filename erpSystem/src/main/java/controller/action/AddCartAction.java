package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.Cart;
import cart.CartDTO;
import cart.controller.CartDAO;
import customer.Customer;
import product.Product;
import product.contoroller.ProductDAO;

public class AddCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Customer customer = (Customer) request.getSession().getAttribute("log");
		ProductDAO productDao = ProductDAO.getInstance();
		
		int customerId = customer.getId();
		int productId = Integer.parseInt(request.getParameter("product"));
		int count = Integer.parseInt(request.getParameter("count"));
		
		Product product = productDao.getProductById(productId);
		int price = product.getPrice();

		CartDAO cartDao = CartDAO.getInstance();
		ArrayList<Cart> list = cartDao.readCartListByCustomerId(customerId);

		boolean checkDupl = false; // 기존 고객이 같은 물품을 담은 전적이 있을 시, true

		if (list != null) {
			for (Cart target : list) {
				if (target.getProductId() == productId) {
					checkDupl = true;
					System.out.println(target.getQuantity());
					count += target.getQuantity();
				}
			}
		}
		
		CartDTO cartDto = new CartDTO(customerId, productId, price, count);

		if (checkDupl) {
		// 	Update 수량
			cartDao.updateCartQuantity(cartDto);
		} else {
		// Create 장바구니	
			cartDao.createCart(cartDto);
		}
		
		response.sendRedirect("/");
	}
}
