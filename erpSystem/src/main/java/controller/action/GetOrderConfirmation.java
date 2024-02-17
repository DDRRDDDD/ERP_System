package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import salesOrder.SalesOrder;
import salesOrder.controller.SalesOrderDAO;

public class GetOrderConfirmation implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SalesOrderDAO salesOrderDao = SalesOrderDAO.getInstance();

		String vpage = request.getParameter("vpage");
		if (vpage == null)
			vpage = "1";

		int selPage = Integer.parseInt(vpage);
		int total = salesOrderDao.getTotalOrderCount();

		ArrayList<SalesOrder> list = salesOrderDao.getOrdersPerPage(selPage);
		int lastPage = (int) Math.ceil((double) total / 10);
		
		request.setAttribute("list", list);
		request.setAttribute("lastPage", lastPage);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("?content=orderconfirmation");		
		dispatcher.forward(request, response);

	}

}
