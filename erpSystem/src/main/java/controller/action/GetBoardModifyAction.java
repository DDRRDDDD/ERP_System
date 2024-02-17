package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.Board;
import board.controller.BoardDAO;
import boardCategory.controller.BoardCategoryDAO;

public class GetBoardModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO boardDao = BoardDAO.getInstance();
		BoardCategoryDAO boardCategoryDao = BoardCategoryDAO.getInstance();
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		Board board = boardDao.getBoardById(id);
		int categoryId = board.getCategoryId();
		
		String title = board.getTitle();

		String main = board.getMain();
		main = main.replace("<br>", "\r\n");

		String name = boardCategoryDao.getCategoryNameById(categoryId);
		
		request.setAttribute("id", id);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("title", title);
		request.setAttribute("main", main);
		request.setAttribute("name", name);
		
		request.setAttribute("content", "boardmodify");
		request.getRequestDispatcher("/").forward(request, response);
	}

}
