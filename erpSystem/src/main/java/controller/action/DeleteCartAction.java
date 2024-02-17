package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.Cart;
import cart.controller.CartDAO;
import cartView.CartView;
import customer.Customer;
import product.Product;
import product.contoroller.ProductDAO;

public class DeleteCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("product"));
		int count = Integer.parseInt(request.getParameter("count"));

		Customer customer = (Customer) request.getSession().getAttribute("log");
		int customerId = customer.getId();

		CartDAO dao = CartDAO.getInstance();
		ProductDAO pdao = ProductDAO.getInstance();
		Product product = pdao.getProductById(productId);

		dao.deleteCartByProductId(productId);
		int total = Integer.parseInt(request.getParameter("total"));
		
		ArrayList<Cart> list = dao.readCartListByCustomerId(customerId);
		ArrayList<CartView> list2 = dao.getCartViewTableByCustomerId(customerId);

		total -= (product.getPrice() * count);
		
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("total", total);

		RequestDispatcher dispatcher = request.getRequestDispatcher("?content=cartlist");
		dispatcher.forward(request, response);
	}

}
