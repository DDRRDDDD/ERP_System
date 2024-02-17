package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.controller.BoardDAO;
import customer.Customer;
import customer.CustomerDTO;
import customer.controller.CustomerDAO;
import orderProduct.controller.OrderProductDAO;
import salesOrder.SalesOrder;
import salesOrder.controller.SalesOrderDAO;

public class DropCustomerAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");

		
		CustomerDAO customerDao = CustomerDAO.getInstance();
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("log");
		
		if(customer != null && password.equals(customer.getPassword())) {
			int id = customer.getId();
			int gradeId = customer.getGradeId();
			String name = customer.getName();
			String address = customer.getAddress();
			String phone = customer.getPhone();
			String gender = customer.getGender();
			
			CustomerDTO customerDto = new CustomerDTO(id, gradeId, name, address, phone, gender, password);
			
			delete(session);
			customerDao.deleteCustomer(customerDto);
			session.removeAttribute("log");
			response.sendRedirect("/");
		}
		else {
			request.setAttribute("message", "회원 정보가 올바르지 않습니다.");
	        request.getRequestDispatcher("/").forward(request, response);
		}
	}
	
	private void delete(HttpSession session) {
		Customer customer = (Customer)session.getAttribute("log");
		
		
		BoardDAO boardDao = BoardDAO.getInstance();
		OrderProductDAO orderProductDao = OrderProductDAO.getInstance();
		SalesOrderDAO salesOrderDao = SalesOrderDAO.getInstance();
		ArrayList<SalesOrder> list = salesOrderDao.getSalesOrderByCustomerID(customer.getId());
		
		for(SalesOrder salesOrder : list) {
			orderProductDao.removeOrderProductByOrderId(salesOrder.getId());
		}
		salesOrderDao.deleteSalesOrderByCustomerId(customer.getId());
		boardDao.deleteBoardByWriterId(customer.getId());
	}
}