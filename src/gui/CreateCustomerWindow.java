package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import customers.Customer;
import customers.CustomersDatabase;

public class CreateCustomerWindow extends JFrame{
	private JTextField txtCustFirstName;
	private JTextField txtCustLastName;
	private JTextField txtCustEmail;
	private JTextField txtCustContactNo;
	private JTextField txtCustAddress;
	private JTextField txtCustCity;
	private JTextField txtCustState;
	private Customer customer;
	
	public CreateCustomerWindow(){
		this.setSize(300,500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNewUsername = new JLabel("First Name:");
		lblNewUsername.setBounds(31, 97, 100, 16);
		getContentPane().add(lblNewUsername);
		
		JLabel lblNewPassword = new JLabel("Last Name:");
		lblNewPassword.setBounds(31, 142, 100, 16);
		getContentPane().add(lblNewPassword);
		
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setBounds(31, 188, 100, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Address:");
		lblLastName.setBounds(31, 280, 100, 16);
		getContentPane().add(lblLastName);
		
		JLabel lblCategory = new JLabel("Contact Number:");
		lblCategory.setBounds(31, 233, 117, 16);
		getContentPane().add(lblCategory);
		
		JLabel lblCreateNewUser = new JLabel("CREATE NEW CUSTOMER");
		lblCreateNewUser.setBounds(71, 30, 153, 16);
		getContentPane().add(lblCreateNewUser);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(31, 319, 61, 16);
		getContentPane().add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(31, 359, 61, 16);
		getContentPane().add(lblState);
		
		txtCustFirstName = new JTextField();
		txtCustFirstName.setBounds(143, 92, 130, 26);
		getContentPane().add(txtCustFirstName);
		txtCustFirstName.setColumns(10);
		
		txtCustLastName = new JTextField();
		txtCustLastName.setBounds(143, 137, 130, 26);
		getContentPane().add(txtCustLastName);
		txtCustLastName.setColumns(10);
		
		txtCustEmail = new JTextField();
		txtCustEmail.setBounds(143, 183, 130, 26);
		getContentPane().add(txtCustEmail);
		txtCustEmail.setColumns(10);
		
		try {
			txtCustContactNo = new JFormattedTextField(new MaskFormatter("+6###########"));
			txtCustContactNo.setBounds(143, 228, 130, 26);
			getContentPane().add(txtCustContactNo);
			txtCustContactNo.setColumns(10);
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		
		
		txtCustAddress = new JTextField();
		txtCustAddress.setBounds(143, 275, 130, 26);
		getContentPane().add(txtCustAddress);
		txtCustAddress.setColumns(10);
		
		txtCustCity = new JTextField();
		txtCustCity.setBounds(143, 314, 130, 26);
		getContentPane().add(txtCustCity);
		txtCustCity.setColumns(10);
		
		txtCustState = new JTextField();
		txtCustState.setBounds(143, 354, 130, 26);
		getContentPane().add(txtCustState);
		txtCustState.setColumns(10);
		
		JButton btnCreateNewCustomer = new JButton("Create");
		btnCreateNewCustomer.setBounds(31, 411, 117, 44);
		btnCreateNewCustomer.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try{
					customer = new Customer();
					customer.setFirstName(txtCustFirstName.getText());
					customer.setLastName(txtCustLastName.getText());
					customer.setEmail(txtCustEmail.getText());
					customer.setContactNo(txtCustContactNo.getText());
					customer.setAddress(txtCustAddress.getText());
					customer.setCity(txtCustCity.getText());
					customer.setState(txtCustState.getText());
					
					CustomersDatabase customersDB = new CustomersDatabase();
					customersDB.addCustomer(customer);
					JOptionPane.showMessageDialog(null, "New Customer has been created!");
					setVisible(false);
					dispose();
				}catch(Exception x){
					x.printStackTrace();
				}
			}
			
		});
		getContentPane().add(btnCreateNewCustomer);
		
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
