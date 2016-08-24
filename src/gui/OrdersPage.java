package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import customers.Customer;
import customers.CustomersDatabase;
import orders.Order;
import orders.OrdersDatabase;
import users.UsersDatabase;
import users.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public abstract class OrdersPage extends Page{
	protected JButton btnAdd, btnEdit, btnDelete, btnRefresh, btnComplete;
	
	protected void createAddButton (){
		btnAdd = new JButton("Create");
		btnAdd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				CreateOrderWindow createOrderWindow = new CreateOrderWindow();
				createOrderWindow.setVisible(true);
			}
			
		});
		buttonsPanel.add(btnAdd);
	}
	
	protected void createEditButton (){
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					int id = (int) table.getValueAt(table.getSelectedRow(), 0);
					OrdersDatabase ordersDB = new OrdersDatabase();
					Order order = ordersDB.getOrderByID(id);
					EditOrderWindow editWindow = new EditOrderWindow(order);
					editWindow.setVisible(true);
				}
			}

		});
		buttonsPanel.add(btnEdit);
	}

	protected void createDeleteButton (){
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					int id = (int) table.getValueAt(table.getSelectedRow(), 0);
					OrdersDatabase ordersDB = new OrdersDatabase();
					Order order = ordersDB.getOrderByID(id);
					
					int result = JOptionPane.showConfirmDialog(null, 
							"Do you want to delete order?", 
							null, JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION){
								ordersDB.deleteOrder(order);
								JOptionPane.showMessageDialog(null, "Order has been deleted!");
							}
				}
			}
			
		});
		buttonsPanel.add(btnDelete);
	}

	protected void createRefreshButton (){
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
			
		});
		buttonsPanel.add(btnRefresh);
	}
	
	protected void createCompleteButton (){
		btnComplete = new JButton("Complete");
		btnComplete.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					int id = (int) table.getValueAt(table.getSelectedRow(), 0);
					OrdersDatabase ordersDB = new OrdersDatabase();
					Order order = ordersDB.getOrderByID(id);
					
					CompleteOrderWindow compOrderWindow = new CompleteOrderWindow(order);
					compOrderWindow.setVisible(true);
				}
			}
			
		});
		buttonsPanel.add(btnComplete);
	}
	
	protected void setTable(){
		tableTitles = new Object[11];
		tableTitles[0] = "Order ID";
		tableTitles[1] = "Customer ID";
		tableTitles[2] = "Customer's First Name";
		tableTitles[3] = "Customer's Last Name";
		tableTitles[4] = "Engineer ID";
		tableTitles[5] = "Engineer's First Name";
		tableTitles[6] = "Engineer's Last Name";
		tableTitles[7] = "Date";
		tableTitles[8] = "Time";
		tableTitles[9] = "Order Type";
		tableTitles[10] = "Order Status";
		
		OrdersDatabase ordersDB = new OrdersDatabase();
		List<Order> orders = new ArrayList<Order>();
		orders = ordersDB.getAllOrders();
		
		CustomersDatabase customersDB = new CustomersDatabase();
		UsersDatabase usersDB = new UsersDatabase();
		
		tModel = new DefaultTableModel();
		tModel.setColumnIdentifiers(tableTitles);
		
		Object[] data = new Object[11];
		
		if(orders != null){
			for(int i=0;i<orders.size(); i++){
				Customer customer = customersDB.getCustomerByID(orders.get(i).getCustomerID());
				User engineer = usersDB.getUserByID(orders.get(i).getEngineerID());
				
				data[0] = orders.get(i).getOrderID();
				data[1] = orders.get(i).getCustomerID();
				data[2] = customer.getFirstName();
				data[3] = customer.getLastName();
				data[4] = orders.get(i).getEngineerID();
				data[5] = engineer.getFirstName();
				data[6] = engineer.getLastName();
				SimpleDateFormat dateF = new SimpleDateFormat("dd.MM.yyyy");
				data[7] = dateF.format(orders.get(i).getDate());
				dateF = new SimpleDateFormat("HH:mm");
				data[8] = dateF.format(orders.get(i).getDate());
				data[9] = orders.get(i).getOrderType();
				if (orders.get(i).isOrderStatus()){
					data[10] = "Delivered";
				} else{
					data[10] = "Not Delivered";
				}
				
				tModel.addRow(data);
			}
		}
	}
	
	private void refreshTable(){
		OrdersDatabase ordersDB = new OrdersDatabase();
		List<Order> orders = new ArrayList<Order>();
		orders = ordersDB.getAllOrders();
		
		CustomersDatabase customersDB = new CustomersDatabase();
		UsersDatabase usersDB = new UsersDatabase();
		
		tModel = new DefaultTableModel();
		tModel.setColumnIdentifiers(tableTitles);
		
		Object[] data = new Object[11];
		
		if(orders != null){
			for(int i=0;i<orders.size(); i++){
				Customer customer = customersDB.getCustomerByID(orders.get(i).getCustomerID());
				User engineer = usersDB.getUserByID(orders.get(i).getEngineerID());
				
				data[0] = orders.get(i).getOrderID();
				data[1] = orders.get(i).getCustomerID();
				data[2] = customer.getFirstName();
				data[3] = customer.getLastName();
				data[4] = orders.get(i).getEngineerID();
				data[5] = engineer.getFirstName();
				data[6] = engineer.getLastName();
				SimpleDateFormat dateF = new SimpleDateFormat("dd.MM.yyyy");
				data[7] = dateF.format(orders.get(i).getDate());
				dateF = new SimpleDateFormat("HH:mm");
				data[8] = dateF.format(orders.get(i).getDate());
				data[9] = orders.get(i).getOrderType();
				if (orders.get(i).isOrderStatus()){
					data[10] = "Delivered";
				} else{
					data[10] = "Not Delivered";
				}
				
				tModel.addRow(data);
			}
		}
		table.setModel(tModel);
	}
}
