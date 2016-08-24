package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import users.User;
import users.UsersDatabase;

public class EditUserWindow extends JFrame{
	private JTextField txtEditNewUsername;
	private JPasswordField txtEditNewPassword;
	private JTextField txtEditFirstName;
	private JTextField txtEditLastName;
	private JTextField txtEditCategory;
	private JTextField txtEditUserID;
	private UsersDatabase usersDB;
	
	public EditUserWindow(User user){
		this.setSize(300,500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(31, 110, 100, 16);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(31, 172, 100, 16);
		getContentPane().add(lblPassword);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(31, 231, 100, 16);
		getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(31, 291, 100, 16);
		getContentPane().add(lblLastName);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(31, 346, 61, 16);
		getContentPane().add(lblCategory);
		
		JLabel lblCreateNewUser = new JLabel("EDIT USER");
		lblCreateNewUser.setBounds(102, 20, 69, 16);
		getContentPane().add(lblCreateNewUser);
		
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setBounds(31, 63, 61, 16);
		getContentPane().add(lblUserId);
		
		txtEditUserID = new JTextField();
		txtEditUserID.setBounds(143, 58, 130, 26);
		txtEditUserID.setText(user.getUserID()+"");
		txtEditUserID.setEditable(false);
		getContentPane().add(txtEditUserID);
		txtEditUserID.setColumns(10);
		
		txtEditNewUsername = new JTextField();
		txtEditNewUsername.setBounds(143, 105, 130, 26);
		txtEditNewUsername.setText(user.getUsername());
		getContentPane().add(txtEditNewUsername);
		txtEditNewUsername.setEditable(false);
		txtEditNewUsername.setColumns(10);
		
		txtEditNewPassword = new JPasswordField();
		txtEditNewPassword.setBounds(143, 167, 130, 26);
		txtEditNewPassword.setText(user.getPassword());
		getContentPane().add(txtEditNewPassword);
		txtEditNewPassword.setColumns(10);
		
		txtEditFirstName = new JTextField();
		txtEditFirstName.setBounds(143, 226, 130, 26);
		txtEditFirstName.setText(user.getFirstName());
		getContentPane().add(txtEditFirstName);
		txtEditFirstName.setColumns(10);
		
		txtEditLastName = new JTextField();
		txtEditLastName.setBounds(143, 286, 130, 26);
		txtEditLastName.setText(user.getLastName());
		getContentPane().add(txtEditLastName);
		txtEditLastName.setColumns(10);
		
		txtEditCategory = new JTextField();
		txtEditCategory.setEditable(false);
		txtEditCategory.setBounds(143, 341, 130, 27);
		txtEditCategory.setText(user.getCategory());
		getContentPane().add(txtEditCategory);
		
		JButton btnCreateNewUser = new JButton("Edit");
		btnCreateNewUser.setBounds(31, 411, 117, 44);
		btnCreateNewUser.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try{
				usersDB = new UsersDatabase();
				User newUser = user;
				newUser.setFirstName(txtEditFirstName.getText());
				newUser.setLastName(txtEditLastName.getText());
				newUser.setPassword(new String(txtEditNewPassword.getPassword()));
				usersDB.updateUser(newUser);
				JOptionPane.showMessageDialog(null, "User has been edited!");
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
	}
}
