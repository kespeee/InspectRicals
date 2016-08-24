package gui;

import users.User;

public class EngineerWindow extends Window{
	
	public EngineerWindow(User user){
		super(user);
		tabbedPane.add("Orders", new EngineerOrdersPage(user));
	}
}
