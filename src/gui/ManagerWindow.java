package gui;

import users.User;

public class ManagerWindow extends Window{
	public ManagerWindow(User user){
		super(user);
		tabbedPane.add("Customers", new CustomersPage());
		tabbedPane.add("Orders", new ManagerOrdersPage());
		tabbedPane.add("Statistics", new StatisticsPage());
	}
}
