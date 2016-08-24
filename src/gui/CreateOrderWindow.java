package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import customers.Customer;
import customers.CustomersDatabase;
import orders.Order;
import orders.OrdersDatabase;
import users.User;
import users.UsersDatabase;

public class CreateOrderWindow extends JFrame{
	private JTextField txtCustomerID;
	private JTextField txtEngineerID;
	private JTextField txtDate;
	private JTextField txtTime;
	private JComboBox cmbOrderType;
	
	public CreateOrderWindow(){
		this.setSize(300,500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblCustomerID = new JLabel("Customer ID:");
		lblCustomerID.setBounds(31, 142, 100, 16);
		getContentPane().add(lblCustomerID);
		
		JLabel lblEngineerID = new JLabel("Engineer ID:");
		lblEngineerID.setBounds(31, 188, 100, 16);
		getContentPane().add(lblEngineerID);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(31, 233, 100, 16);
		getContentPane().add(lblDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(31, 280, 117, 16);
		getContentPane().add(lblTime);
		
		JLabel lblCreateNewOrder = new JLabel("CREATE NEW ORDER");
		lblCreateNewOrder.setBounds(85, 42, 153, 16);
		getContentPane().add(lblCreateNewOrder);
		
		JLabel lblOrderType = new JLabel("Order Type:");
		lblOrderType.setBounds(31, 324, 85, 16);
		getContentPane().add(lblOrderType);
		
		txtCustomerID = new JTextField();
		txtCustomerID.setBounds(143, 137, 130, 26);
		getContentPane().add(txtCustomerID);
		txtCustomerID.setColumns(10);
		
		txtEngineerID = new JTextField();
		txtEngineerID.setBounds(143, 183, 130, 26);
		getContentPane().add(txtEngineerID);
		txtEngineerID.setColumns(10);
		
		try {
			txtDate = new JFormattedTextField(new MaskFormatter("##.##.####"));
			txtDate.setBounds(143, 228, 130, 26);
			getContentPane().add(txtDate);
			txtDate.setColumns(10);
		} catch (ParseException e1) {
			
			e1.printStackTrace();
			
		}
		
		try {
			txtTime = new JFormattedTextField(new MaskFormatter("##:##"));
			txtTime.setBounds(143, 275, 130, 26);
			getContentPane().add(txtTime);
			txtTime.setColumns(10);
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		
		String[] orderTypes = {"Inspection","Installation"};
		
		cmbOrderType = new JComboBox(orderTypes);
		cmbOrderType.setSelectedIndex(0);
		cmbOrderType.setBounds(143, 319, 130, 26);
		getContentPane().add(cmbOrderType);
		
		JButton btnCreateNewOrder = new JButton("Create");
		btnCreateNewOrder.setBounds(31, 411, 117, 44);
		btnCreateNewOrder.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try{
					CustomersDatabase customersDB = new CustomersDatabase();
					OrdersDatabase ordersDB = new OrdersDatabase();
					UsersDatabase usersDB = new UsersDatabase();
					
					Customer customer = customersDB.getCustomerByID(Integer.parseInt(txtCustomerID.getText()));
					User engineer = usersDB.getUserByID(Integer.parseInt(txtEngineerID.getText()));
					
					if (customer != null){
						if (engineer != null && engineer.getCategory().equals("Engineer")){
							Order order = new Order();
							order.setCustomerID(customer.getCustomerID());
							order.setEngineerID(engineer.getUserID());
							
							SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
							String dateStr = txtDate.getText()+" "+txtTime.getText();
							Date newDate = sdf.parse(dateStr);
							order.setDate(newDate);
							order.setOrderType(cmbOrderType.getSelectedItem().toString());
							order.setOrderStatus(false);
							
							ordersDB.addOrder(order);
							JOptionPane.showMessageDialog(null, "New Order has been created!");
							setVisible(false);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Such Engineer does not exist");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Such Customer does not exist");
					}
					
					
				}catch(Exception x){
					x.printStackTrace();
				}
			}
			
		});
		getContentPane().add(btnCreateNewOrder);
		
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
