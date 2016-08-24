package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import users.User;

abstract class Window extends JFrame {
	protected JTabbedPane tabbedPane;
	private String applicationTitle = "INSPECT-RICALS";
	public Window(User user){
		this.setSize(850,700);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle(applicationTitle+" - "+user.getFirstName()+" "+user.getLastName()+" ("+user.getCategory()+")");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(upperPanel, BorderLayout.NORTH);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				logOut();
			}
			
		});
		upperPanel.add(btnLogOut);
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		centerPanel.add(tabbedPane);
		getContentPane().setVisible(true);
	}
	
	private void logOut(){
		this.setVisible(false);
		LoginWindow login = new LoginWindow();
		login.setVisible(true);
		this.dispose();
	}
}
