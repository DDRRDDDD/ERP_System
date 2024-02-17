package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.Customer;
import customer.controller.CustomerDAO;

public class GetRegistAction implements Action{
	
	private final int BRONZE = 1;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Customer client = (Customer) request.getSession().getAttribute("log"); 
		
		String url = "?content=regist";
		int id = client == null ? getNewCustomerId() : client.getId();
		int gradeId = BRONZE;
		
		if(client != null) {
			id 		= client.getId();
			gradeId = client.getGradeId();
			
			url += "&password=" + client.getPassword();
			url += "&name="	   + client.getName();
			url += "&address="  + client.getAddress();
			url += "&phone="    + client.getPhone();
			url += "&gender="   + client.getGender();
		}
		
		request.setAttribute("id", id);
		request.setAttribute("gradeId", gradeId);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
	
	private int getNewCustomerId() {
		CustomerDAO customerDao = CustomerDAO.getInstance();
		return customerDao.getCustomerId();
	}
}