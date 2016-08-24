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
import report.Report;
import users.User;
import users.UsersDatabase;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class CompleteOrderWindow extends JFrame{
	private JTextField txtEditCustomerID;
	private JTextField txtEditEngineerID;
	private JTextField txtEditDate;
	private JTextField txtEditTime;
	private JTextField txtEditOrderType;
	private JTextField txtEditOrderID;
	private JTextArea reportMessageArea;
	
	public CompleteOrderWindow(Order order){
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
		
		JLabel lblCompleteOrder = new JLabel("COMPLETE ORDER");
		lblCompleteOrder.setBounds(90, 39, 117, 16);
		getContentPane().add(lblCompleteOrder);
		
		JLabel lblOrderType = new JLabel("Order Type:");
		lblOrderType.setBounds(31, 324, 85, 16);
		getContentPane().add(lblOrderType);
		
		JLabel lblOrderId = new JLabel("Order ID:");
		lblOrderId.setBounds(31, 99, 61, 16);
		getContentPane().add(lblOrderId);
		
		JLabel lblReportMessage = new JLabel("Report Message: ");
		lblReportMessage.setBounds(31, 364, 117, 16);
		getContentPane().add(lblReportMessage);
		
		reportMessageArea = new JTextArea();
		reportMessageArea.setBounds(144, 364, 129, 35);
		reportMessageArea.setLineWrap(true);
		getContentPane().add(reportMessageArea);
		
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
			txtEditDate.setEditable(false);
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
			txtEditTime.setEditable(false);
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		
		txtEditOrderType = new JTextField();
		txtEditOrderType.setBounds(143, 319, 130, 26);
		getContentPane().add(txtEditOrderType);
		txtEditOrderType.setText(order.getOrderType());
		txtEditOrderType.setEditable(false);
		
		
		JButton btnCompleteNewOrder = new JButton("Complete");
		btnCompleteNewOrder.setBounds(31, 411, 117, 44);
		btnCompleteNewOrder.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try{
					OrdersDatabase ordersDB = new OrdersDatabase();
					
					
					
					order.setOrderStatus(true);
					ordersDB.updateOrder(order);
					new Report(order, reportMessageArea.getText());
					JOptionPane.showMessageDialog(null, "New Order has been updated!");
					setVisible(false);
					dispose();
						
				}catch(Exception x){
					x.printStackTrace();
				}
			}
			
		});
		getContentPane().add(btnCompleteNewOrder);
		
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
