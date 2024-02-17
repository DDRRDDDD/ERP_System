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

public class BoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO boardDao = BoardDAO.getInstance();

		HttpSession session = request.getSession();

		Customer cus = (Customer) session.getAttribute("log");

		LocalDate now = LocalDate.now();

		String date = String.valueOf(now);

		int id = boardDao.getBoardId();
		String title = request.getParameter("title");
		String main = request.getParameter("main");
		
		main = main.replace("\r\n","<br>");
		
		String modifiedDate = date;
		String registDate = date;
		int writer = cus.getId();
		int categoryId = Integer.valueOf(request.getParameter("categoryId"));

		BoardDTO boardDto = new BoardDTO(id, title, main, modifiedDate, registDate, writer, categoryId);

		boardDao.createBoard(boardDto);
		
		
		request.getRequestDispatcher("/service?command=boardlist").forward(request, response);

	}

}
