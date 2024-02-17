package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.controller.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO boardDao = BoardDAO.getInstance();

		int id = Integer.parseInt(request.getParameter("id"));
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

		boardDao.deleteBoardByboardId(id);
		response.sendRedirect("../service?command=boardlist&categoryId="+categoryId);
	}

}
