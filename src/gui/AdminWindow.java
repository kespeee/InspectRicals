package gui;

import users.User;

public class AdminWindow extends Window{
	public AdminWindow(User user){
		super(user);
		tabbedPane.add("Users", new UsersPage());
		tabbedPane.add("Customers", new CustomersPage());
		tabbedPane.add("Orders", new ClerkOrdersPage());
		tabbedPane.add("Statistics", new StatisticsPage());
		
		
	}
}
