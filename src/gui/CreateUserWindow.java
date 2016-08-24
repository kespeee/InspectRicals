package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import users.User;
import users.UsersDatabase;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class CreateUserWindow extends JFrame{
	private JTextField txtNewUsername;
	private JPasswordField txtNewPassword;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private UsersDatabase usersDB;
	private User user;
	
	public CreateUserWindow(){
		this.setSize(300,500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		String[] categories = {"Clerk","Engineer","Manager","Admin"};
		
		JLabel lblNewUsername = new JLabel("New Username:");
		lblNewUsername.setBounds(31, 80, 100, 16);
		getContentPane().add(lblNewUsername);
		
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setBounds(31, 142, 100, 16);
		getContentPane().add(lblNewPassword);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setBounds(31, 205, 100, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(31, 265, 100, 16);
		getContentPane().add(lblLastName);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(31, 322, 61, 16);
		getContentPane().add(lblCategory);
		
		txtNewUsername = new JTextField();
		txtNewUsername.setBounds(143, 75, 130, 26);
		getContentPane().add(txtNewUsername);
		txtNewUsername.setColumns(10);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setBounds(143, 137, 130, 26);
		getContentPane().add(txtNewPassword);
		txtNewPassword.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(143, 200, 130, 26);
		getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(143, 260, 130, 26);
		getContentPane().add(txtLastName);
		txtLastName.setColumns(10);
		
		JComboBox cmbCategory = new JComboBox(categories);
		cmbCategory.setSelectedIndex(0);
		cmbCategory.setBounds(143, 318, 130, 27);
		getContentPane().add(cmbCategory);
		
		JButton btnCreateNewUser = new JButton("Create");
		btnCreateNewUser.setBounds(31, 411, 117, 44);
		btnCreateNewUser.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try{
				usersDB = new UsersDatabase();
				user = new User();
				user.setUsername(txtNewUsername.getText());
				user.setFirstName(txtFirstName.getText());
				user.setLastName(txtLastName.getText());
				user.setPassword(new String(txtNewPassword.getPassword()));
				user.setCategory(cmbCategory.getSelectedItem().toString());
				usersDB.addUser(user);
				JOptionPane.showMessageDialog(null, "New User has been created!");
				setVisible(false);
				dispose();
				}catch(Exception x){
					x.printStackTrace();
				}
			}
			
		});
		getContentPane().add(btnCreateNewUser);
		
		JButton btnCancelButton = new JButton("Cancel");
		btnCancelButton.setBounds(156, 411, 117, 44);
		btnCancelButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
			
		});
		getContentPane().add(btnCancelButton);
		
		JLabel lblCreateNewUser = new JLabel("CREATE NEW USER");
		lblCreateNewUser.setBounds(96, 30, 117, 16);
		getContentPane().add(lblCreateNewUser);
	}
}
