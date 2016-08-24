package gui;

import users.User;

public class ClerkWindow extends Window {
	public ClerkWindow(User user){
		super(user);
		tabbedPane.add("Orders", new ClerkOrdersPage());
		tabbedPane.add("Customers", new CustomersPage());
	}
}
