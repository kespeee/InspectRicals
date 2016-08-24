package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import users.User;
import users.UsersDatabase;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	public LoginWindow(){
		this.setSize(300,300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Login");
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(42, 72, 66, 16);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(44, 124, 66, 16);
		getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(123, 68, 130, 26);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(124, 120, 130, 26);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
				
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(92, 184, 120, 35);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkInput();
			}
		});
		getContentPane().add(btnLogin);
	}
	
	private void checkInput(){
		try {
			String username = txtUsername.getText();
			String password = new String(txtPassword.getPassword());
			User user = new User();
			UsersDatabase usersDB = new UsersDatabase();
			user = usersDB.getUserByUsername(username);
			
			if (user != null){
				if (user.getUsername().equals(username) && user.getPassword().equals(password)){
					this.setVisible(false);
					this.dispose();
					Window window;
					
					if (user.getCategory().equals("Admin")){
						window = new AdminWindow(user);
						window.setVisible(true);
					} else if (user.getCategory().equals("Engineer")){
						window = new EngineerWindow(user);
						window.setVisible(true);
					} else if (user.getCategory().equals("Manager")){
						window = new ManagerWindow(user);
						window.setVisible(true);
					} else if (user.getCategory().equals("Clerk")){
						window = new ClerkWindow(user);
						window.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Password");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Such user does not exist");
			}	
		
		} catch (Exception ex){
			JOptionPane.showMessageDialog(null, "Cannot connect to the Database");
			ex.printStackTrace();
		}
	}
}
