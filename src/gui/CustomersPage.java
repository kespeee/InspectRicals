package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import customers.Customer;
import customers.CustomersDatabase;
import users.User;
import users.UsersDatabase;

public class CustomersPage extends Page{
	private JButton btnAdd, btnEdit, btnDelete, btnRefresh;
	
	public CustomersPage(){
		btnAdd = new JButton("Create");
		btnAdd.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				CreateCustomerWindow createCustWindow = new CreateCustomerWindow();
				createCustWindow.setVisible(true);
			}
			
		});
		buttonsPanel.add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					int id = (int) table.getValueAt(table.getSelectedRow(), 0);
					CustomersDatabase customersDB = new CustomersDatabase();
					Customer customer = customersDB.getCustomerByID(id);
					EditCustomerWindow editWindow = new EditCustomerWindow(customer);
					editWindow.setVisible(true);
				}
			}
			
		});
		buttonsPanel.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					int id = (int) table.getValueAt(table.getSelectedRow(), 0);
					CustomersDatabase customersDB = new CustomersDatabase();
					Customer customer = customersDB.getCustomerByID(id);
					
					int result = JOptionPane.showConfirmDialog(null, 
							"Do you want to delete customer "+customer.getFirstName()+" "+customer.getLastName()+"?", 
							null, JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION){
								customersDB.deleteCustomer(customer);
								JOptionPane.showMessageDialog(null, "Customer has been deleted!");
							}
				}
			}
			
		});
		buttonsPanel.add(btnDelete);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
			
		});
		buttonsPanel.add(btnRefresh);
	}

	protected void setTable() {
		tableTitles = new Object[8];
		tableTitles[0] = "Customer ID";
		tableTitles[1] = "First Name";
		tableTitles[2] = "Last Name";
		tableTitles[3] = "Email";
		tableTitles[4] = "Contact Number";
		tableTitles[5] = "Address";
		tableTitles[6] = "City";
		tableTitles[7] = "State";
		
		CustomersDatabase customersDB = new CustomersDatabase();
		List<Customer> customers = new ArrayList<Customer>();
		customers = customersDB.getAllCustomers();
		
		tModel = new DefaultTableModel();
		tModel.setColumnIdentifiers(tableTitles);
		
		Object[] data = new Object[8];
		
		if(customers != null){
			for(int i=0;i<customers.size(); i++){
				data[0] = customers.get(i).getCustomerID();
				data[1] = customers.get(i).getFirstName();
				data[2] = customers.get(i).getLastName();
				data[3] = customers.get(i).getEmail();
				data[4] = customers.get(i).getContactNo();
				data[5] = customers.get(i).getAddress();
				data[6] = customers.get(i).getCity();
				data[7] = customers.get(i).getState();
				
				tModel.addRow(data);
			}
		}
	}
	
	private void refreshTable(){
		CustomersDatabase customersDB = new CustomersDatabase();
		List<Customer> customers = new ArrayList<Customer>();
		customers = customersDB.getAllCustomers();
		
		tModel = new DefaultTableModel();
		tModel.setColumnIdentifiers(tableTitles);
		
		Object[] data = new Object[8];
		
		if(customers != null){
			for(int i=0;i<customers.size(); i++){
				data[0] = customers.get(i).getCustomerID();
				data[1] = customers.get(i).getFirstName();
				data[2] = customers.get(i).getLastName();
				data[3] = customers.get(i).getEmail();
				data[4] = customers.get(i).getContactNo();
				data[5] = customers.get(i).getAddress();
				data[6] = customers.get(i).getCity();
				data[7] = customers.get(i).getState();
				
				tModel.addRow(data);
			}	
		}
		table.setModel(tModel);
	}
	
}
