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

public class EditOrderWindow extends JFrame{
	private JTextField txtEditCustomerID;
	private JTextField txtEditEngineerID;
	private JTextField txtEditDate;
	private JTextField txtEditTime;
	private JTextField txtEditOrderType;
	private JTextField txtEditOrderID;
	private JComboBox cmbEditOrderStatus;
	
	public EditOrderWindow(Order order){
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
		
		JLabel lblEditNewOrder = new JLabel("EDIT NEW ORDER");
		lblEditNewOrder.setBounds(90, 39, 117, 16);
		getContentPane().add(lblEditNewOrder);
		
		JLabel lblOrderType = new JLabel("Order Type:");
		lblOrderType.setBounds(31, 324, 85, 16);
		getContentPane().add(lblOrderType);
		
		JLabel lblOrderId = new JLabel("Order ID:");
		lblOrderId.setBounds(31, 99, 61, 16);
		getContentPane().add(lblOrderId);
		
		JLabel lblOrderStatus = new JLabel("Order Status:");
		lblOrderStatus.setBounds(31, 364, 85, 16);
		getContentPane().add(lblOrderStatus);
		
		String[] orderStatus = {"Delivered","Not Delivered"};
		
		cmbEditOrderStatus = new JComboBox(orderStatus);
		cmbEditOrderStatus.setBounds(143, 360, 130, 27);
		getContentPane().add(cmbEditOrderStatus);
		if(order.isOrderStatus()){
			cmbEditOrderStatus.setSelectedIndex(0);
		} else {
			cmbEditOrderStatus.setSelectedIndex(1);
		}
		
		txtEditOrderID = new JTextField();
		txtEditOrderID.setBounds(143, 94, 130, 26);
		getContentPane().add(txtEditOrderID);
		txtEditOrderID.setColumns(10);
		txtEditOrderID.setText(order.getOrderID()+"");
		txtEditOrderID.setEditable(false);
		
		txtEditCustomerID = new JTextField();
		txtEditCustomerID.setBounds(143, 137, 130, 26);
		getContentPane().add(txtEditCustomerID);
		txtEditCustomerID.setColumns(10);
		txtEditCustomerID.setText(order.getCustomerID()+"");
		txtEditCustomerID.setEditable(false);
		
		txtEditEngineerID = new JTextField();
		txtEditEngineerID.setBounds(143, 183, 130, 26);
		getContentPane().add(txtEditEngineerID);
		txtEditEngineerID.setColumns(10);
		txtEditEngineerID.setText(order.getEngineerID()+"");
		txtEditEngineerID.setEditable(false);
		
		try {
			txtEditDate = new JFormattedTextField(new MaskFormatter("##.##.####"));
			txtEditDate.setBounds(143, 228, 130, 26);
			getContentPane().add(txtEditDate);
			txtEditDate.setColumns(10);
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			txtEditDate.setText(sdf.format(order.getDate()));
		} catch (ParseException e1) {
			
			e1.printStackTrace();
			
		}
		
		try {
			txtEditTime = new JFormattedTextField(new MaskFormatter("##:##"));
			txtEditTime.setBounds(143, 275, 130, 26);
			getContentPane().add(txtEditTime);
			txtEditTime.setColumns(10);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			txtEditTime.setText(sdf.format(order.getDate()));
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		
		txtEditOrderType = new JTextField();
		txtEditOrderType.setBounds(143, 319, 130, 26);
		getContentPane().add(txtEditOrderType);
		txtEditOrderType.setText(order.getOrderType());
		txtEditOrderType.setEditable(false);
		
		
		JButton btnEditNewOrder = new JButton("Edit");
		btnEditNewOrder.setBounds(31, 411, 117, 44);
		btnEditNewOrder.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try{
					CustomersDatabase customersDB = new CustomersDatabase();
					OrdersDatabase ordersDB = new OrdersDatabase();
					UsersDatabase usersDB = new UsersDatabase();
					
					Customer customer = customersDB.getCustomerByID(Integer.parseInt(txtEditCustomerID.getText()));
					User engineer = usersDB.getUserByID(Integer.parseInt(txtEditEngineerID.getText()));
					
					if (customer != null){
						if (engineer != null && engineer.getCategory().equals("Engineer")){
							
							order.setCustomerID(customer.getCustomerID());
							order.setEngineerID(engineer.getUserID());
							
							SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
							String dateStr = txtEditDate.getText()+" "+txtEditTime.getText();
							Date newDate = sdf.parse(dateStr);
							order.setDate(newDate);
							
							if (cmbEditOrderStatus.getSelectedItem().toString().equals("Delivered")){
								order.setOrderStatus(true);
							} else {
								order.setOrderStatus(false);
							}
							
							order.setOrderType(txtEditOrderType.getText());
							
							ordersDB.updateOrder(order);
							JOptionPane.showMessageDialog(null, "New Order has been updated!");
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
		getContentPane().add(btnEditNewOrder);
		
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
