package controller.action;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDTO;
import board.controller.BoardDAO;
import customer.Customer;

public class BoardModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO boardDao = BoardDAO.getInstance();

		HttpSession session = request.getSession();

		Customer cus = (Customer) session.getAttribute("log");

		LocalDate now = LocalDate.now();

		String date = String.valueOf(now);

		int id = Integer.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		String main = request.getParameter("main");
		String modifiedDate = date;
		String registDate = date;
		int writer = cus.getId();
		int categoryId = Integer.valueOf(request.getParameter("categoryId"));
		
		main = main.replace("\r\n","<br>");

		BoardDTO boardDto = new BoardDTO(id, title, main, modifiedDate, registDate, writer, categoryId);

		boardDao.updateBoard(boardDto);

		request.getRequestDispatcher("/service?command=boardlist").forward(request, response);
	}

}
