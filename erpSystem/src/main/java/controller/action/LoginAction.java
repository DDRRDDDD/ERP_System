package controller.action;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.Customer;
import customer.controller.CustomerDAO;
import salesOrder.SalesOrder;
import salesOrder.controller.SalesOrderDAO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    int id = 0;
	    String password = "";

	    try {
	        id = Integer.parseInt(request.getParameter("id"));
	        password = request.getParameter("password");
	    } catch (NumberFormatException e) {
	        request.setAttribute("message", "ID는 숫자로 입력해주세요.");
	        request.getRequestDispatcher("/").forward(request, response);
	        return;
	    }

	    CustomerDAO dao = CustomerDAO.getInstance();
	    Customer cus = dao.getCustomerById(id);

	    if (cus != null && password.equals(cus.getPassword())) {
	        HttpSession session = request.getSession();
	        session.setAttribute("log", cus);
	        getCustomerBascket(request);
	        response.sendRedirect("/");
	    } else {
	        request.setAttribute("message", "회원 정보가 올바르지 않습니다.");
	        request.getRequestDispatcher("/").forward(request, response);
	    }
	}
	
	
	private void getCustomerBascket(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("log");
		
		SalesOrderDAO orderDao = SalesOrderDAO.getInstance();
		SalesOrder order = orderDao.getOrderByNoStatusAndId(customer.getId());
		
		if(order == null) {
			request.getSession().setAttribute("new", "Y");
			int newId = orderDao.getSalesOrderId();
			String date = String.valueOf(LocalDate.now());
			order = new SalesOrder(newId, customer.getId(), date, 0, "N");
		}
		request.getSession().setAttribute("cart", order);
	}

}
