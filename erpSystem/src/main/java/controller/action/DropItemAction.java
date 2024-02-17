package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import salesOrder.SalesOrder;

public class DropItemAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SalesOrder order = (SalesOrder) session.getAttribute("cart");
		
		if(order == null)
			return;
		
		String target = request.getParameter("target");
		int productId = Integer.parseInt(target);
		order.getCart().remove(productId);
		response.sendRedirect("../");
	}

}
