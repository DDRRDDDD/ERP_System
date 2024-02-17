package controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import orderProduct.OrderProductDTO;
import orderProduct.controller.OrderProductDAO;
import product.Product;
import product.contoroller.ProductDAO;
import salesOrder.SalesOrder;
import salesOrder.SalesOrderDTO;
import salesOrder.controller.SalesOrderDAO;

public class OrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		SalesOrder order = (SalesOrder) session.getAttribute("cart");
		
		ProductDAO productDao = ProductDAO.getInstance();
		SalesOrderDAO orderDao = SalesOrderDAO.getInstance();
		OrderProductDAO orderProductDao = OrderProductDAO.getInstance();
		
		HashMap<Integer, Integer> basket = order.getCart();
		ArrayList<Product> list = new ArrayList<>(basket.size());
		
		SalesOrderDTO orderDto = new SalesOrderDTO(order);
		orderDto.setTotal(order.getTotalPrice(list));
		System.out.println(order.getTotalPrice(list));
		orderDto.setStatus("Y");
		
		if(request.getSession().getAttribute("new") == null)
			orderDao.updateSalesOrder(orderDto);
		else
			orderDao.createSalesOrder(orderDto);
		
		OrderProductDTO orderProductDto = null;
		for(Integer id : basket.keySet()) {
			Product product = productDao.getProductById(id);
			list.add(product);
			
			orderProductDto = new OrderProductDTO();
			orderProductDto.setProductId(product.getId());
			orderProductDto.setOrderId(order.getId());
			orderProductDto.setOrderProduct(product.getName());
			orderProductDto.setQuantity(basket.get(id));
			
			orderProductDao.createOrderProduct(orderProductDto);
		}
		basket = new HashMap<Integer, Integer>();
		
		response.sendRedirect("/");
	}
}
