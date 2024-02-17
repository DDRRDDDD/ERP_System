package controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.Product;
import product.contoroller.ProductDAO;
import salesOrder.SalesOrder;

public class GetOrderAction implements Action{

	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		SalesOrder order = (SalesOrder) session.getAttribute("cart");
		HashMap<Integer, Integer> map = order.getCart();
		ArrayList<Product> list = new ArrayList<>(map.size());
		ProductDAO productDao = ProductDAO.getInstance();

		for (Integer id : map.keySet())
			list.add(productDao.getProductById(id));

		int total = order.getTotalPrice(list);
		
		request.setAttribute("list", list);
		request.setAttribute("map", map);
		request.setAttribute("total", total);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("?content=order");		
		dispatcher.forward(request, response);
		
		
		
	}

}
