package gui;

import users.User;

public class EngineerOrdersPage extends OrdersPage{
	private User engineer;
	
	public EngineerOrdersPage(User engineer){
		this.engineer = engineer;
		
		createRefreshButton();
		createCompleteButton();
	}
}
