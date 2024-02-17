package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import salesOrder.controller.SalesOrderDAO;

public class OrderConfirmAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int salesOrderId = Integer.parseInt(request.getParameter("salesOrderId"));
		String salesOrderStatus = request.getParameter("salesOrderStatus");
		
		
		SalesOrderDAO salesOrderDao = SalesOrderDAO.getInstance();
		
		salesOrderStatus = salesOrderStatus.equals("N") ? "Y" : "D";
		
		salesOrderDao.updateOrderSatatus(salesOrderId, salesOrderStatus);
		request.getRequestDispatcher("/service?command=getorderconfirmation").forward(request, response);
	}

}
