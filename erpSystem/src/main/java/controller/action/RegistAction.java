package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.CustomerDTO;
import customer.controller.CustomerDAO;

public class RegistAction implements Action {

	private int id;
	private int gradeId;
	private String name;
	private String address;
	private String phone;
	private String gender;
	private String password;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.id = Integer.parseInt(request.getParameter("id"));
		this.gradeId = Integer.parseInt(request.getParameter("gradeId"));
		this.name = request.getParameter("name");
		this.address = request.getParameter("address");
		this.phone = request.getParameter("phone");
		this.gender = request.getParameter("gender");
		this.password = request.getParameter("password");
		
		

		CustomerDTO CustomerDto = new CustomerDTO(id, gradeId, name, address, phone, gender, password);

		inputCustomerForDataBase(request,CustomerDto);

		response.sendRedirect("/");
	}

	private void inputCustomerForDataBase(HttpServletRequest request,CustomerDTO customerDto) {
		CustomerDAO customerDao = CustomerDAO.getInstance();
		HttpSession session = request.getSession();

		if (customerDao.getCustomerId() == this.id) 
			customerDao.createCustomer(customerDto);
		else {
			customerDao.updateCustomer(customerDto);
			session.removeAttribute("log");
		}


	}
}
