package controller;

import controller.action.Action;
import controller.action.BasketAction;
import controller.action.BoardAction;
import controller.action.BoardDeleteAction;
import controller.action.BoardModifyAction;
import controller.action.DropCustomerAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.OrderConfirmAction;
import controller.action.ProductAction;
import controller.action.ProductDetailAction;
import controller.action.RegistAction;

public class ActionFactory {
	private static final ActionFactory instance = new ActionFactory();

	private ActionFactory() {
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;

		if (command.equals("logout"))
			action = new LogoutAction();
		
		else if (command.equals("login"))
			action = new LoginAction();
		
		else if (command.equals("regist"))
			action = new RegistAction();
		
		else if (command.equals("dropCustomer"))
			action = new DropCustomerAction();
		
		else if (command.equals("product"))
			action = new ProductAction();
		
		else if(command.equals("productDetail"))
			action = new ProductDetailAction();
		
		else if(command.equals("basket"))
			action = new BasketAction();
		
		else if (command.equals("board"))
			action = new BoardAction();
		
		else if (command.equals("boardModify"))
			action = new BoardModifyAction();
		
		else if (command.equals("boardDelete"))
			action = new BoardDeleteAction();
		
		else if (command.equals("orderConfirmation"))
			action = new OrderConfirmAction();
		
		
		return action;
	}
}
