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

public class EditCustomerWindow extends JFrame{
	private JTextField txtEditCustFirstName;
	private JTextField txtEditCustLastName;
	private JTextField txtEditCustEmail;
	private JTextField txtEditCustContactNo;
	private JTextField txtEditCustAddress;
	private JTextField txtEditCustCity;
	private JTextField txtEditCustState;
	
	public EditCustomerWindow(Customer customer){
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
		
		JLabel lblCreateNewUser = new JLabel("EDIT CUSTOMER");
		lblCreateNewUser.setBounds(95, 31, 110, 16);
		getContentPane().add(lblCreateNewUser);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(31, 319, 61, 16);
		getContentPane().add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(31, 359, 61, 16);
		getContentPane().add(lblState);
		
		txtEditCustFirstName = new JTextField();
		txtEditCustFirstName.setBounds(143, 92, 130, 26);
		getContentPane().add(txtEditCustFirstName);
		txtEditCustFirstName.setColumns(10);
		txtEditCustFirstName.setText(customer.getFirstName());
		txtEditCustFirstName.setEditable(false);
		
		txtEditCustLastName = new JTextField();
		txtEditCustLastName.setBounds(143, 137, 130, 26);
		getContentPane().add(txtEditCustLastName);
		txtEditCustLastName.setColumns(10);
		txtEditCustLastName.setText(customer.getLastName());
		txtEditCustLastName.setEditable(false);
		
		txtEditCustEmail = new JTextField();
		txtEditCustEmail.setBounds(143, 183, 130, 26);
		getContentPane().add(txtEditCustEmail);
		txtEditCustEmail.setColumns(10);
		txtEditCustEmail.setText(customer.getEmail());
		
		try {
			txtEditCustContactNo = new JFormattedTextField(new MaskFormatter("+6###########"));
			txtEditCustContactNo.setBounds(143, 228, 130, 26);
			getContentPane().add(txtEditCustContactNo);
			txtEditCustContactNo.setColumns(10);
			txtEditCustContactNo.setText(customer.getContactNo());
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		
		
		txtEditCustAddress = new JTextField();
		txtEditCustAddress.setBounds(143, 275, 130, 26);
		getContentPane().add(txtEditCustAddress);
		txtEditCustAddress.setColumns(10);
		txtEditCustAddress.setText(customer.getAddress());
		
		txtEditCustCity = new JTextField();
		txtEditCustCity.setBounds(143, 314, 130, 26);
		getContentPane().add(txtEditCustCity);
		txtEditCustCity.setColumns(10);
		txtEditCustCity.setText(customer.getCity());
		
		txtEditCustState = new JTextField();
		txtEditCustState.setBounds(143, 354, 130, 26);
		getContentPane().add(txtEditCustState);
		txtEditCustState.setColumns(10);
		txtEditCustState.setText(customer.getState());
		
		JButton btnCreateNewCustomer = new JButton("Edit");
		btnCreateNewCustomer.setBounds(31, 411, 117, 44);
		btnCreateNewCustomer.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try{
					customer.setEmail(txtEditCustEmail.getText());
					customer.setContactNo(txtEditCustContactNo.getText());
					customer.setAddress(txtEditCustAddress.getText());
					customer.setCity(txtEditCustCity.getText());
					customer.setState(txtEditCustState.getText());
					CustomersDatabase customersDB = new CustomersDatabase();
					customersDB.updateCustomer(customer);
					JOptionPane.showMessageDialog(null, "Customer has been edited!");
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
