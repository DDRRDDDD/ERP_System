package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.Board;
import board.controller.BoardDAO;
import boardCategory.controller.BoardCategoryDAO;

public class GetBoardListAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        BoardDAO boardDao = BoardDAO.getInstance();
        int total = boardDao.getTotalCountByCategory(categoryId);
        int lastPage = (int) Math.ceil((double) total / 10);
        
        String vpage = request.getParameter("vpage");

        if (vpage == null)
            vpage = "1";

        int selPage = Integer.parseInt(vpage);

        ArrayList<Board> boardList = boardDao.getPostsPerPage(categoryId, selPage);
        
        BoardCategoryDAO boardcateDao = BoardCategoryDAO.getInstance();
        
        String categroryName = boardcateDao.getCategoryNameById(categoryId);

        request.setAttribute("boards", boardList);
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("vpage", vpage);
        request.setAttribute("category", categroryName);
        request.setAttribute("categoryId", categoryId);
        
        request.setAttribute("content", "board");
        request.getRequestDispatcher("/").forward(request, response);
        
    }
}


