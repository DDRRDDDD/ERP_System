package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.Customer;
import customer.controller.CustomerDAO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    request.setCharacterEncoding("UTF-8");

	    int id = 0;
	    String password = "";

	    try {
	        id = Integer.parseInt(request.getParameter("id"));
	        password = request.getParameter("password");
	    } catch (NumberFormatException e) {
	        request.setAttribute("message", "ID는 숫자로 입력해주세요.");
	        request.getRequestDispatcher("login").forward(request, response);
	        return;
	    }

	    CustomerDAO dao = CustomerDAO.getinstnace();
	    Customer cus = dao.getCustomerById(id);

	    if (cus != null && password.equals(cus.getPassword())) {
	        HttpSession session = request.getSession();
	        session.setAttribute("log", cus);
//	        getCustomerBascket(request);
	        response.sendRedirect("/");
	    } else {
	        request.setAttribute("message", "회원 정보가 올바르지 않습니다.");
	        request.getRequestDispatcher("login").forward(request, response);
	    }
	}
	
	
	
}
