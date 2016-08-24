package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import users.User;
import users.UsersDatabase;


public class UsersPage extends Page{
	private JButton btnAdd, btnEdit, btnDelete, btnRefresh;
	
	public UsersPage(){
		btnAdd = new JButton("Create");
		btnAdd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateUserWindow createWindow = new CreateUserWindow();
				createWindow.setVisible(true);
			}
			
		});
		buttonsPanel.add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1){
					int id = (int) table.getValueAt(table.getSelectedRow(), 0);
					UsersDatabase usersDB = new UsersDatabase();
					User user = usersDB.getUserByID(id);
					EditUserWindow editWindow = new EditUserWindow(user);
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
					UsersDatabase usersDB = new UsersDatabase();
					User user = usersDB.getUserByID(id);
					
					int result = JOptionPane.showConfirmDialog(null, 
							"Do you want to delete user "+user.getFirstName()+" "+user.getLastName()+"?", 
							null, JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION){
								usersDB.deleteUser(user);
								JOptionPane.showMessageDialog(null, "User has been deleted!");
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
		tableTitles = new Object[6];
		tableTitles[0] = "User ID";
		tableTitles[1] = "Username";
		tableTitles[2] = "Password";
		tableTitles[3] = "First Name";
		tableTitles[4] = "Last Name";
		tableTitles[5] = "Category";
		
		UsersDatabase usersDB = new UsersDatabase();
		List<User> users = new ArrayList<User>();
		users = usersDB.getAllUsers();
		
		tModel = new DefaultTableModel();
		tModel.setColumnIdentifiers(tableTitles);
		
		Object[] data = new Object[6];
		
		if(users != null){
			for(int i=0;i<users.size(); i++){
				data[0] = users.get(i).getUserID();
				data[1] = users.get(i).getUsername();
				data[2] = users.get(i).getPassword();
				data[3] = users.get(i).getFirstName();
				data[4] = users.get(i).getLastName();
				data[5] = users.get(i).getCategory();
				
				tModel.addRow(data);
			}
		}
	}
	
	private void refreshTable(){
		UsersDatabase usersDB = new UsersDatabase();
		List<User> users = new ArrayList<User>();
		users = usersDB.getAllUsers();
		
		tModel = new DefaultTableModel();
		tModel.setColumnIdentifiers(tableTitles);
		
		Object[] data = new Object[6];
		
		if(users != null){
			for(int i=0;i<users.size(); i++){
				data[0] = users.get(i).getUserID();
				data[1] = users.get(i).getUsername();
				data[2] = users.get(i).getPassword();
				data[3] = users.get(i).getFirstName();
				data[4] = users.get(i).getLastName();
				data[5] = users.get(i).getCategory();
				
				tModel.addRow(data);
			}
		}
		table.setModel(tModel);
	}
}
