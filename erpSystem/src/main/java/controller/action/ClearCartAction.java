package controller.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.controller.CartDAO;
import cartView.CartView;
import customer.Customer;
import orderProduct.OrderProductDTO;
import orderProduct.controller.OrderProductDAO;
import product.Product;
import product.ProductDTO;
import product.contoroller.ProductDAO;
import salesOrder.SalesOrderDTO;
import salesOrder.controller.SalesOrderDAO;

public class ClearCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Customer customer = (Customer) request.getSession().getAttribute("log");

		int customerId = customer.getId();
		String date = new Timestamp(System.currentTimeMillis()).toString().split(" ")[0];
		int total = Integer.parseInt(request.getParameter("total"));
		String status = "Y";

		CartDAO cartDao = CartDAO.getInstance();
		ArrayList<CartView> list = cartDao.getCartViewTableByCustomerId(customerId);

		// 주문을 하고
		SalesOrderDAO salesOrderDao = SalesOrderDAO.getInstance();
		SalesOrderDTO salesOrderDto = new SalesOrderDTO(customerId, date, total, status);
		salesOrderDao.createSalesOrder(salesOrderDto);

		int salesOrderId = salesOrderDao.getSalesOrderId();

		OrderProductDAO opDao = OrderProductDAO.getInstance();
		ProductDAO productDao = ProductDAO.getInstance();

		for (CartView target : list) {
			int productId = target.getPruductId();
			String product = target.getProductName();
			int quantity = target.getQuantity();

			// 상품 전체 수량 변경
			Product productFlag = productDao.getProductById(productId);
			int stock = productFlag.getStock();
			int updateStock = stock - quantity;
			ProductDTO productDto = new ProductDTO(productFlag, updateStock);
			productDao.setProductStock(productDto);
			
			OrderProductDTO opDto = new OrderProductDTO(productId, salesOrderId, product, quantity);
			opDao.createOrderProduct(opDto);
		}
	
		// 카트비우기
		cartDao.deleteCartByCustomerId(customerId);
		
		request.setAttribute("list", list);
		request.setAttribute("total", total);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("?content=ordersuccess");
		dispatcher.forward(request, response);
	}

}
