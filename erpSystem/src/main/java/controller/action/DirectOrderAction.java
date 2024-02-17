package controller.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.Customer;
import orderProduct.OrderProductDTO;
import orderProduct.controller.OrderProductDAO;
import product.Product;
import product.ProductDTO;
import product.contoroller.ProductDAO;
import salesOrder.SalesOrderDTO;
import salesOrder.controller.SalesOrderDAO;

public class DirectOrderAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("log");

		int customerId = customer.getId();
		String date = new Timestamp(System.currentTimeMillis()).toString().split(" ")[0];
		int total = Integer.parseInt(request.getParameter("total"));
		String status = "Y";
		
		SalesOrderDAO salesOrderDao = SalesOrderDAO.getInstance();
		SalesOrderDTO salesOrderDto = new SalesOrderDTO(customerId, date, total, status);
		salesOrderDao.createSalesOrder(salesOrderDto);
		
		int salesOrderId = salesOrderDao.getSalesOrderId();

		OrderProductDAO opDao = OrderProductDAO.getInstance();
		ProductDAO productDao = ProductDAO.getInstance();
		
		int productId = Integer.parseInt(request.getParameter("product"));
		Product product = productDao.getProductById(productId);
		
		int count = Integer.parseInt(request.getParameter("count"));
		int updateStock = product.getStock() - count;
		String productName = product.getName();
		
		ProductDTO productDto = new ProductDTO(product, updateStock);
		productDao.setProductStock(productDto);
		
		OrderProductDTO opDto = new OrderProductDTO(productId, salesOrderId, productName, count);
		opDao.createOrderProduct(opDto);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);
	}

}
