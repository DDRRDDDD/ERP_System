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

public class GetCartListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Customer customer = (Customer) request.getSession().getAttribute("log");

		int customerId = customer.getId();

		CartDAO cartDao = CartDAO.getInstance();

		ArrayList<Cart> list = cartDao.readCartListByCustomerId(customerId);
		ArrayList<CartView> list2 = cartDao.getCartViewTableByCustomerId(customerId);

		int total = cartDao.getTotalPriceByCustomerId(customerId);

		request.setAttribute("total", total);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);

		RequestDispatcher dispatcher = request.getRequestDispatcher("?content=cartlist");
		dispatcher.forward(request, response);
	}

}
