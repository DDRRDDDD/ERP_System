package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.Board;
import board.controller.BoardDAO;
import customer.Customer;
import salesOrder.SalesOrder;
import salesOrder.controller.SalesOrderDAO;

public class GetMypageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("log");

		int userId = customer.getId();
		
		BoardDAO boarddao = BoardDAO.getInstance();
		SalesOrderDAO salesdao = SalesOrderDAO.getInstance();
		
		ArrayList<SalesOrder> list = salesdao.getSalesOrderByCustomerID(userId);
		ArrayList<Board> blist = boarddao.getBoardByCustomerId(userId);
		
		
		request.setAttribute("list", list);
		request.setAttribute("blist", blist);
		request.setAttribute("customer", customer);
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("?content=mypage" );		
		dispatcher.forward(request, response);
	}

}