package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import salesOrder.controller.SalesOrderDAO;
import salesView.MonthlySalesView;

public class GetMonthlySalesTotal implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답 데이터의 Content-Type을 JSON으로 설정
		response.setContentType("application/json");

		// DAO 메소드를 호출하여 데이터를 가져옴
		ArrayList<MonthlySalesView> list = SalesOrderDAO.getInstance().getMonthlySalesTotal();
		System.out.println("list.size() : " + list.size());

		// JSONArray 객체 생성
		JSONArray jsonArray = new JSONArray(list);

		// JSON 데이터를 응답으로 전송
		response.getWriter().print(jsonArray.toString());
	}

}
